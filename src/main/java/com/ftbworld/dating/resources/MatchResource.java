package com.ftbworld.dating.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/dating/matches")
public class MatchResource {

    @GetMapping("")
    public String getMyMatches(HttpServletRequest request) {
        int user_id = (int) request.getAttribute("user_id");
        String username = (String) request.getAttribute("username");

        return String.format("Hello, '%s' (%s).", username, user_id);
    }

}
