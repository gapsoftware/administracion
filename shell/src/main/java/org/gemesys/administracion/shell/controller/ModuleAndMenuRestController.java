package org.gemesys.administracion.shell.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.gemesys.administracion.shell.dto.MenuDTO;
import org.gemesys.administracion.shell.dto.ModuleDTO;
import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Module;
import org.gemesys.administracion.shell.service.MenuService;
import org.gemesys.administracion.shell.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gperezv on 10-04-18.
 */

@RestController
@RequestMapping("/")
public class ModuleAndMenuRestController {

    private final static Logger logger = LoggerFactory.getLogger(ModuleAndMenuRestController.class);

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private MenuService menuService;

    /****************
     * AREA DE MÓDULOS
     ****************/

    @RequestMapping(value = "/rest/modulos", method = RequestMethod.GET)
    public ResponseEntity<?> fetchModulos(HttpServletRequest request) {

        logger.info("GMSYSADMIN - module - Listando todos los módulos existentes con estado activo y no vacíos de menús");

        List<Module> allmodulos = moduleService.findAllActiveNoEmpty();

        if (allmodulos.isEmpty()) {
            logger.error("GMSYSADMIN - module - ERROR: No existen módulos a listar");
            return new ResponseEntity<>("ERROR: No existen módulos a listar", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Module>>(allmodulos, HttpStatus.OK);
    }


    @RequestMapping(value = "/rest/modulos/", method = RequestMethod.POST)
    public ResponseEntity<?> createModule(@RequestBody ModuleDTO moduleDTO) {

        logger.info("GMSYSADMIN-module-Insertando módulo nuevo " + moduleDTO.getNombre());

        if (!moduleService.dataIsOK(moduleDTO)) {
            logger.error("GMSYSADMIN-module- ERROR: Los datos de entrada son erróneos o están incompletos-revise");
            return new ResponseEntity<>("ERROR: Datos de entrada erróneos o incompletos. módulo NO insertado",
                    HttpStatus.BAD_REQUEST);
        }

        if (moduleService.ModuloExists(moduleDTO)) {
            logger.error("GMSYSADMIN-module- ERROR: ya existe un módulo con ese nombre");
            return new ResponseEntity<>("ERROR: ya existe un módulo con ese nombre. módulo NO insertado",
                    HttpStatus.CONFLICT);
        }

        Module moduloConstruido = moduleService.buildModule(moduleDTO);
        moduleService.save(moduloConstruido);

        return new ResponseEntity<>("GMSYSADMIN-module- PROCESO EXITOSO - módulo creado en tabla", HttpStatus.CREATED);
    }


    @RequestMapping(value = "/rest/modulos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateModule(@PathVariable("id") Integer id,
                                          @RequestBody ModuleDTO moduleDTO) {

        Module moduloEncontrado = moduleService.findModule(id, moduleDTO);

        if (moduloEncontrado == null) {
            return new ResponseEntity<>("GMSYSADMIN-module- ERROR- módulo id = " + id +
                    " no existe.", HttpStatus.NOT_FOUND);
        }

        moduleService.save(moduloEncontrado);

        logger.info("GMSYSADMIN-module - Módulo id = " + moduloEncontrado.getId() +
                " nombre = " + moduloEncontrado.getName() + "fue modificado.");

        return new ResponseEntity<>("GMSYSADMIN-module- PROCESO EXITOSO - módulo id = " + moduloEncontrado.getId() +
                " nombre = " + moduloEncontrado.getName() + " modificado en tabla.", HttpStatus.OK);

    }


    @RequestMapping(value = "/rest/modulos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteModule(@PathVariable("id") Integer id) {

        Module moduloEncontrado = moduleService.findById(id);

        if (moduloEncontrado == null) {
            return new ResponseEntity<>("GMSYSADMIN-module- ERROR- módulo id = " + id +
                    " no existe.", HttpStatus.NOT_FOUND);
        }

        moduleService.delete(id);

        logger.info("GMSYSADMIN-module - Módulo id = " + moduloEncontrado.getId() +
                " nombre = " + moduloEncontrado.getName() + "fue eliminado.");

        return new ResponseEntity<>("GMSYSADMIN-module- PROCESO EXITOSO - módulo id = " + moduloEncontrado.getId() +
                " nombre = " + moduloEncontrado.getName() + " fue eliminado en tabla.", HttpStatus.OK);

    }

    /*************
     * AREA DE MENÚS
     *************/


    @RequestMapping(value = "/rest/menus/", method = RequestMethod.POST)
    public ResponseEntity<?> createMenu(@RequestBody MenuDTO menuDTO) {

        Menu menuConstruido = menuService.buildMenu(menuDTO);
        menuService.save(menuConstruido);

        return null;
    }

}
