package org.gemesys.administracion.shell.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.gemesys.administracion.shell.model.Module;
import org.gemesys.administracion.shell.model.User;
import org.gemesys.administracion.shell.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/rest/usuarios", method = RequestMethod.GET)
    public ResponseEntity<?> fetchUsuarios(HttpServletRequest request,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size)
    {
        logger.info("GMSYSADMIN - user - Listando todos los usuarios existentes");
        Page<User> resultPage = userService.findAllPaginated(page, size);

        if (page > resultPage.getTotalPages()) {
            logger.error("GMSYSADMIN - users - ERROR: No existen usuarios a listar");
            return new ResponseEntity<>("ERROR: No existen usuarios a listar", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }
}
