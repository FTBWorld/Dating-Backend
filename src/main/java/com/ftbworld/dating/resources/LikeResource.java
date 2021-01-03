package com.ftbworld.dating.resources;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.services.LikeService;
import com.ftbworld.dating.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dating/likes")
public class LikeResource {

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @PutMapping(path = "/{username}")
    public ResponseEntity<Map<String, Object>> createLike(HttpServletRequest request, @PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        // TODO: move this logic to the service, re-introduce exceptions. The resource should only process client stuff.
        // User can't like themselves
        User user2 = userService.findUserByUsername(username);
        if (user.getId().equals(user2.getId())) {
            response.put("message", "Can't like yourself");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // TODO: Can't create duplicate likes - why does this not work?
        Like existing = likeService.findLikeByUserIDs(user.getId().toHexString(), user2.getId().toHexString());
        if (existing != null) {
            response.put("message", String.format("Like between %s and %s already exists", user.getUsername(), user2.getUsername()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Like like = likeService.createLikeByUserIDs(user.getId().toHexString(), user2.getId().toHexString());

        response.put("message", String.format("Like between %s and %s created!", user.getUsername(), user2.getUsername()));
        response.put("object", like);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<Map<String, Object>> deleteLike(HttpServletRequest request, @PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        // Get the like between the user and the person
        User user2 = userService.findUserByUsername(username);
        if (user2 == null) {
            response.put("message", String.format("User %s not found", username));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // TODO: also does not work
        Like common = likeService.findLikeByUserIDs(user.getId().toHexString(), user2.getId().toHexString());
        if (common == null) {
            response.put("message", String.format("Like between %s and %s not found", user.getUsername(), user2.getUsername()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean actuallyDeleted = likeService.deleteLikeByID(common.getId().toHexString());

        response.put("message", String.format("Like between %s and %s deleted.", user.getUsername(), user2.getUsername()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my_likes")
    public ResponseEntity<Map<String, Object>> getMyLikes(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        // TODO: test me
        List<Like> likes = likeService.findLikesByUserID(user.getId().toHexString());

        response.put("objects", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/likes_me")
    public ResponseEntity<Map<String, Object>> getLikesOfMe(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        // TODO: test me
        List<Like> likes = likeService.findLikesOfUserID(user.getId().toHexString());

        response.put("objects", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/matches")
    public ResponseEntity<Map<String, Object>> getMyMatches(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        // TODO: test me after implementing the query
        List<Like> likes = likeService.findMatchesOfUserByID(user.getId().toHexString());

        response.put("objects", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
