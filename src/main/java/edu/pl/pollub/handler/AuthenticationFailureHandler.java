package edu.pl.pollub.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(exception));
        //writer.println("{ \"error\": \"" + exception.getMessage() + "\" }");
        writer.flush();
    }
}