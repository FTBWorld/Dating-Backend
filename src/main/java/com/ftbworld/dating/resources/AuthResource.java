package com.ftbworld.dating.resources;

import com.ftbworld.dating.Constants;
import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.services.UserService;
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
@RequestMapping("/api/auth")
public class AuthResource {

    @Autowired
    UserService userService;

    private String generateJWTForUser(User user) {
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET) // Use HS since it's a symmetric algorithm.
                .setIssuedAt(new Date()) // Read about HS vs ES: https://stackoverflow.com/a/39239395/11606132
                // No expiration date.
                .claim("username", user.getUsername()) // Only attach data that doesn't change.
                .compact();

        return token;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        String password = (String) body.get("password");

        User user = userService.login(username, password);
        String token = generateJWTForUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome back, " + user.getUsername() + ".");
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        String password = (String) body.get("password");

        User user = userService.registerUser(username, password);
        String token = generateJWTForUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome, " + user.getUsername() + "!");
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
