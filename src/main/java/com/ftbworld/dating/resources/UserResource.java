package com.ftbworld.dating.resources;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> req) {
        String username = (String) req.get("username");
        String password = (String) req.get("password");

        User user = userService.login(username, password);

        Map<String, String> res = new HashMap<>();
        res.put("message", "Welcome back, " + user.getUsername() + ".");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, Object> req) {
        String username = (String) req.get("username");
        String password = (String) req.get("password");

        User user = userService.register(username, password);

        Map<String, String> res = new HashMap<>();
        res.put("message", "Welcome, " + user.getUsername() + "!");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
