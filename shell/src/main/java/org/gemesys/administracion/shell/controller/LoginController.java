package org.gemesys.administracion.shell.controller;

import org.gemesys.administracion.shell.model.Module;
import org.gemesys.administracion.shell.model.Role;
import org.gemesys.administracion.shell.model.User;
import org.gemesys.administracion.shell.service.ModuleService;
import org.gemesys.administracion.shell.service.RoleService;
import org.gemesys.administracion.shell.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by gperezv on 07-02-18.
 */


@Controller
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){

        String nombreUsuario=obtenerNombreUsuario();
        List<Module> allmodulos=obtenerModulosAutorizados();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        modelAndView.addObject("nombreUsuario",nombreUsuario);
        modelAndView.addObject("modulos",allmodulos);
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/access-denied", method = RequestMethod.GET)
    public ModelAndView noaccess(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/access-denied");
        return modelAndView;
    }

    private String obtenerNombreUsuario(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User usuario = userService.findUserByEmail(currentPrincipalName);
        String nombreUsuario = usuario.getName()+" "+usuario.getLastName1();
        logger.info("GMSYSADMIN- Usuario logueado: ["+currentPrincipalName+"] ["+nombreUsuario+"]");
        return nombreUsuario;
    }

    private  List<Module>  obtenerModulosAutorizados(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection roleslogueados = userDetails.getAuthorities();
        List<Module> allmodulos = moduleService.findAllActiveNoEmptyAndAuth(roleslogueados);
        logger.info("módulos: "+allmodulos.size());
        return allmodulos;
    }


}
