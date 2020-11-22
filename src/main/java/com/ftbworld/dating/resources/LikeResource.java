package com.ftbworld.dating.resources;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.services.LikeService;
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

    @PutMapping("/create")
    public ResponseEntity<Map<String, Object>> createLike(HttpServletRequest request, @RequestBody Map<String, Object> body) {
        User user = (User) request.getAttribute("user");
        String username = (String) body.get("username");

        Like like = likeService.createLikeByUsernames(user.getUsername(), username);

        Map<String, Object> response = new HashMap<>();
        response.put("message", String.format("Like between '%s' and '%s' created!", user.getUsername(), username));
        response.put("like", like);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my_likes")
    public ResponseEntity<Map<String, Object>> getMyLikes(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        List<Like> likes = likeService.getLikesByUsername(user.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("likes", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/likes_me")
    public ResponseEntity<Map<String, Object>> getLikesOfMe(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        List<Like> likes = likeService.getLikesOfUsername(user.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("likes", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/matches")
    public ResponseEntity<Map<String, Object>> getMyMatches(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        List<Like> likes = likeService.getMatchesOfUsername(user.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("likes", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
