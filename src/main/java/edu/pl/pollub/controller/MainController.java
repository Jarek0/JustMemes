package edu.pl.pollub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 2017-01-27.
 */
@Controller
public class MainController {
    @RequestMapping(method = RequestMethod.GET,value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{pageNumber}")
    public String showMemesFromPage(@PathVariable int pageNumber,HttpServletRequest request) {
        request.getSession().setAttribute("pageNumber",pageNumber);
        return "index";
    }
}
