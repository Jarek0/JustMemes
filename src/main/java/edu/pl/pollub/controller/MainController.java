package edu.pl.pollub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 2017-01-27.
 */
@Controller
public class MainController {

    @RequestMapping(method = RequestMethod.GET,value = {"/waiting","/","/waiting/{pageNumber}","/showMem/{id}","/{pageNumber}","/login","/register"})
    public ModelAndView reloadPage(HttpServletRequest request) {
        request.getSession().setAttribute("path",request.getServletPath());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getPathAfterReload")
    public String getPathAfterReload(HttpServletRequest request) {
        Object pageObject=request.getSession().getAttribute("path");
        if(pageObject!=null) {
            String page = pageObject.toString();
            request.getSession().removeAttribute("path");
            return page;
        }
        return "1";
    }

}
