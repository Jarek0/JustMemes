package edu.pl.pollub.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pl.pollub.entity.User;
import edu.pl.pollub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dell on 2017-03-15.
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");

        response.setContentType("application/json");

        User user = userService.getByUsername(authentication.getName());

        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(token));
        writer.flush();
    }
}
