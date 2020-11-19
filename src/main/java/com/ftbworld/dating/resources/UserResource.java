package com.ftbworld.dating.resources;

import com.ftbworld.dating.Constants;
import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.services.UserService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    private String generateJWTForUser(User user) {
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET) // Use HS since it's a symmetric algorithm.
                .setIssuedAt(new Date()) // Read about HS vs ES: https://stackoverflow.com/a/39239395/11606132
                // No expiration date.
                .claim("user_id", user.getUser_id()) // Only attach data that does not change.
                .claim("username", user.getUsername()) // Remember: client can read this data!
                .compact();

        return token;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> req) {
        String username = (String) req.get("username");
        String password = (String) req.get("password");

        User user = userService.login(username, password);
        String token = generateJWTForUser(user);

        Map<String, String> res = new HashMap<>();
        res.put("message", "Welcome back, " + user.getUsername() + ".");
        res.put("token", token);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, Object> req) {
        String username = (String) req.get("username");
        String password = (String) req.get("password");

        User user = userService.register(username, password);
        String token = generateJWTForUser(user);

        Map<String, String> res = new HashMap<>();
        res.put("message", "Welcome, " + user.getUsername() + "!");
        res.put("token", token);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
