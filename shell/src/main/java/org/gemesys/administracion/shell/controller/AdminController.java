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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

/**
 * Created by gperezv on 20-07-18.
 */

@Controller
public class AdminController {

    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/admin/main", method = RequestMethod.GET)
    public ModelAndView admin_main(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.setViewName("admin/main");
        return modelAndView;
    }

    @RequestMapping(value="/admin/usuarios", method = RequestMethod.GET)
    public ModelAndView adminUsuarios(){

        String nombreUsuario=obtenerNombreUsuario();
        List<Module> allmodulos=obtenerModulosAutorizados();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adm-usuarios");
        modelAndView.addObject("nombreUsuario",nombreUsuario);
        modelAndView.addObject("modulos",allmodulos);

        return modelAndView;
    }

    @RequestMapping(value="/admin/form-usuario", method = RequestMethod.GET)
    public ModelAndView editUsuario(@RequestParam("id") Long id){

        List<Module> allmodulos=obtenerModulosAutorizados();
        User user = userService.findUserById(id);
        List<Role> allroles = roleService.findAllAvailable(user.getRoles());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/form-usuario");
        modelAndView.addObject("usuario", user);
        modelAndView.addObject("nombreUsuario", user.getName()+" "+user.getLastName1());
        modelAndView.addObject("modulos",allmodulos);
        modelAndView.addObject("allroles",allroles);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/usuario/{userid}/rol/{rolid}", method = RequestMethod.DELETE)
    public ModelAndView deleteRolUsuario(@PathVariable("userid") Long userid,
                                         @PathVariable("rolid") Long rolid) {

        userService.deleteRolUsuario(userid, rolid);

        return null;
    }

    @RequestMapping(value = "/admin/usuario/{userid}/rol/{rolid}", method = RequestMethod.PUT)
    public ModelAndView addRolUsuario(@PathVariable("userid") Long userid,
                                      @PathVariable("rolid") Long rolid) {


        userService.addRolUsuario(userid, rolid);
        List<Module> allmodulos=obtenerModulosAutorizados();
        User user = userService.findUserById(userid);
        List<Role> allroles = roleService.findAllAvailable(user.getRoles());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/form-usuario");
        modelAndView.addObject("usuario", user);
        modelAndView.addObject("nombreUsuario", user.getName()+" "+user.getLastName1());
        modelAndView.addObject("modulos",allmodulos);
        modelAndView.addObject("allroles",allroles);
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
