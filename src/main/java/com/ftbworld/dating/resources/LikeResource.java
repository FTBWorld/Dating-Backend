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

    @PutMapping("/")
    public ResponseEntity<Map<String, Object>> createLike(HttpServletRequest request, @RequestBody Map<String, Object> body) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");
        String userLikedID = (String) body.get("userLikedID");

        Like like = likeService.createLikeByUserIDs(user.getId().toHexString(), userLikedID);

        response.put("message", String.format("Like between '%s' and '%s' created!", user.getId().toHexString(), userLikedID));
        response.put("object", like);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Map<String, Object>> deleteLike(HttpServletRequest request, @RequestBody Map<String, Object> body) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");
        String username = (String) body.get("username");

        // Since we're using PUT, not POST, return 200 even if the like doesn't exist, since it should have the same effect.
        // TODO
        boolean actuallyDeleted = true;

        if (actuallyDeleted) {
            response.put("message", String.format("Like between '%s' and '%s' deleted.", user.getUsername(), username));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/my_likes")
    public ResponseEntity<Map<String, Object>> getMyLikes(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        List<Like> likes = likeService.findLikesByUserID(user.getId().toHexString());

        response.put("objects", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/likes_me")
    public ResponseEntity<Map<String, Object>> getLikesOfMe(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        List<Like> likes = likeService.findLikesOfUserID(user.getId().toHexString());

        response.put("objects", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/matches")
    public ResponseEntity<Map<String, Object>> getMyMatches(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) request.getAttribute("user");

        // TODO
        List<Like> likes = null;

        response.put("objects", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
