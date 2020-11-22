package com.ftbworld.dating.resources;

import com.ftbworld.dating.domain.Like;
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
        int user_id = (int) request.getAttribute("user_id");
        int liked_user = (int) body.get("liked_user");

        Like like = likeService.createLike(user_id, liked_user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", String.format("Like between (%s) and (%s) created!", user_id, liked_user));
        response.put("like", like);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my_likes")
    public ResponseEntity<Map<String, Object>> getMyLikes(HttpServletRequest request) {
        int user_id = (int) request.getAttribute("user_id");

        List<Like> likes = likeService.getLikesByUser(user_id);

        Map<String, Object> response = new HashMap<>();
        response.put("likes", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/likes_me")
    public ResponseEntity<Map<String, Object>> getLikesOfMe(HttpServletRequest request) {
        int user_id = (int) request.getAttribute("user_id");

        List<Like> likes = likeService.getLikesOfUser(user_id);

        Map<String, Object> response = new HashMap<>();
        response.put("likes", likes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
