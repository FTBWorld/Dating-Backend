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
public class ProfileResource {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{username}")
    public ResponseEntity<Map<String, Object>> getProfileByUsername(HttpServletRequest request, @PathVariable String username) {
        User user = (User) request.getAttribute("user");

        //User profile = userService.getUserByUsername(username).toProfile();

        Map<String, Object> response = new HashMap<>();
        //response.put("profile", profile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Map<String, Object>> updateProfileOfCurrentUser(HttpServletRequest request, @RequestBody Map<String, Object> body) {
        User user = (User) request.getAttribute("user");
        String display_name = (String) body.get("display_name");
        String bio = (String) body.get("bio");

        user.setDisplay_name(display_name);
        user.setBio(bio);
        boolean updated = userService.updateUser(user); // Seems to always be true, because of SQL.
        //User profile = userService.getUserByUsername(user.getUsername()).toProfile();

        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("message", String.format("Profile for '%s' updated.", user.getUsername()));
        } else {
            response.put("message", String.format("Profile for '%s' NOT updated.", user.getUsername()));
        }
        //response.put("profile", profile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
