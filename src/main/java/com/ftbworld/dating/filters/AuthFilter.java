package com.ftbworld.dating.filters;

import com.ftbworld.dating.Constants;
import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.repositories.UserRepository;
import com.ftbworld.dating.services.UserService;
import com.ftbworld.dating.services.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // Must be declared as a component for autowire to work (so Spring loads the file).
public class AuthFilter extends GenericFilterBean {

    @Autowired
    UserRepository userRepository;
    // TODO: should we use the repository here directly? Or should we communicate through the service?

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String tokenHeader = httpServletRequest.getHeader("Authorization");
        if (tokenHeader != null) {
            try {
                String[] authHeaderArr = tokenHeader.split("Bearer ");
                String token = authHeaderArr[1];

                // Read the JWT data.
                Claims claims = Jwts.parser().setSigningKey(Constants.JWT_SECRET)
                        .parseClaimsJws(token)
                        .getBody();
                int user_id = Integer.parseInt(claims.get("user_id").toString());
                String username = claims.get("username").toString();

                // Check if that user exists.
                User user = userRepository.findById(user_id);
                if (user != null) {
                    // Attach data to the request.
                    httpServletRequest.setAttribute("user_id", user_id);
                    httpServletRequest.setAttribute("username", username);

                    // TODO: I wonder - since we can attach an object here, why not just attach the User?
                } else {
                    httpServletResponse.sendError(HttpStatus.NOT_FOUND.value(), String.format("A user named '%s' (%s) does not exist?", username, user_id));
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();

                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid or expired token.");
                return; // Stop processing request.
            }
        } else {
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authentication: Bearer <token> required.");
            return;
        }

        // Complete. Move onto the next filter, if any.
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
