package com.ftbworld.dating.resources;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dating/profiles/")
public class UserProfileResource {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{username}")
    public ResponseEntity<Map<String, Object>> getUserProfileByUsername(HttpServletRequest request, @PathVariable String username) {
        Map<String, Object> response = new HashMap<>();

        User user = userService.findUserByUsername(username);
        if (user != null) {
            // User exists - censor sensitive profile information
            user.setPassword("<censored>");

            response.put("object", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // User DNE
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
