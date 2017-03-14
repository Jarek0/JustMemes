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
    @RequestMapping(method = RequestMethod.GET,value ={"/waiting","/"} )
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/waiting/{pageNumber}")
    public ModelAndView reloadWaitingPage(@PathVariable int pageNumber, HttpServletRequest request) {
        System.out.print(pageNumber);
        request.getSession().setAttribute("path","/waiting/"+pageNumber);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{pageNumber}")
    public ModelAndView reloadMainPage(@PathVariable int pageNumber,HttpServletRequest request) {
        request.getSession().setAttribute("path","/"+pageNumber);
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
