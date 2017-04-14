package edu.pl.pollub.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dell on 2017-04-14.
 */
@Aspect
@Component
public class RememberMeAspect {

    public boolean beforeSampleCreation(HttpServletRequest request) {
        if (isRememberMeAuthenticated()) {
            //send login for update
            setRememberMeTargetUrlToSession(request);
            return true;

        } else {
            return false;
        }
    }

    private boolean isRememberMeAuthenticated() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    /**
     * save targetURL in session
     */
    private void setRememberMeTargetUrlToSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.setAttribute("targetUrl", "/admin/update");
        }
    }

    /**
     * get targetURL from session
     */
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if(session!=null){
            targetUrl = session.getAttribute("targetUrl")==null?""
                    :session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }
}
