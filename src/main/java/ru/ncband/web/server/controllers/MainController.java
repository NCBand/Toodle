package ru.ncband.web.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.ncband.web.server.Id;

@Controller
public class MainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("index", "user", new Id(-1));
        modelAndView.setViewName("index");
        return modelAndView;
    }
    }
