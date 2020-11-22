package com.ftbworld.dating.resources;

import com.ftbworld.dating.domain.Like;
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

    // TODO: view a profile if logged in
    @GetMapping(path = "/{username}")
    public ResponseEntity<Map<String, Object>> createLike(HttpServletRequest request, @PathVariable String username) {
        User user = (User) request.getAttribute("user");

        User profile = userService.getUserByUsername(username).toProfile();

        Map<String, Object> response = new HashMap<>();
        response.put("profile", profile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO: update your own profile

}
