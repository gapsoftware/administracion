package org.gemesys.administracion.shell.controller;

import org.gemesys.administracion.shell.model.User;
import org.gemesys.administracion.shell.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gperezv on 20-07-18.
 */

@Controller
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/account/main", method = RequestMethod.GET)
    public ModelAndView account_main() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.setViewName("account/main");
        return modelAndView;
    }

}
