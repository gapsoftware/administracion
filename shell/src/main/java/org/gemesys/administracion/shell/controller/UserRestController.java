package org.gemesys.administracion.shell.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.gemesys.administracion.shell.model.Module;
import org.gemesys.administracion.shell.model.User;
import org.gemesys.administracion.shell.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gperezv on 24-05-18.
 */

@RestController
@RequestMapping("/")
public class UserRestController {

    private final static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/rest/usuario", method = RequestMethod.GET)
    public ResponseEntity<?> fetchUsuarios(HttpServletRequest request,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size)
    {
        logger.info("GMSYSADMIN - REST/USUARIOS - Listando todos los usuarios existentes");
        Page<User> resultPage = userService.findAllPaginated(page, size);

        if (page > resultPage.getTotalPages()) {
            logger.error("GMSYSADMIN - REST/USUARIOS - ERROR: No existen usuarios a listar");
            return new ResponseEntity<>("ERROR: No existen usuarios a listar", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/usuario/findUsersByEmail", method = RequestMethod.GET)
    public ResponseEntity<?> findUsersByEmail(@RequestParam("email") String email,
                                              Pageable pageable) {

        logger.info("GMSYSADMIN - REST/USUARIOS - Buscando e-mail ["+email+"]");
        Page<User> resultPage = userService.findUsersByEmail(email, pageable);

        if (resultPage.getTotalPages()<= 0) {
            logger.error("GMSYSADMIN - REST/USUARIOS - ERROR: No existen usuarios con el e-mail [ "+email+"]");
            return new ResponseEntity<>("REST/USUARIOS - ERROR: No existen usuarios con el e-mail [ "+email+"]",
                                        HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/usuario/{userid}/rol/{rolid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRolUsuario(@PathVariable("userid") Long userid,
                                              @PathVariable("rolid") Long rolid) {

        System.out.println("id usuario:"+userid+ "id rol: "+rolid);

        userService.deleteRolUsuario(userid, rolid);

        return null;
    }
}
