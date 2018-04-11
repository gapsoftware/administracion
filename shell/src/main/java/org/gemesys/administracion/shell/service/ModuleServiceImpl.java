package org.gemesys.administracion.shell.service;

import lombok.Builder;
import org.gemesys.administracion.shell.controller.ModuleAndMenuController;
import org.gemesys.administracion.shell.dto.ModuleDTO;
import org.gemesys.administracion.shell.model.Module;
import org.gemesys.administracion.shell.repository.ModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gperezv on 10-04-18.
 */

@Service
public class ModuleServiceImpl implements ModuleService {

    private final static Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Module findById(int id) {
        return moduleRepository.findOne(id);
    }

    @Override
    public void save(Module module) {
        moduleRepository.save(module);
    }

    @Override
    public void update(Module module) {
        moduleRepository.save(module);
    }

    @Override
    public void delete(int id) {
        moduleRepository.delete(id);
    }

    @Override
    @Builder
    public Module buildModule(ModuleDTO moduleDTO) {
        return new Module(moduleDTO.getNombre(), moduleDTO.getActivo(), moduleDTO.getOrden());
    }

    @Override
    public Module findModule(int id, ModuleDTO moduleDTO) {

        Module moduloEncontrado = moduleRepository.findOne(id);

        if (moduloEncontrado == null){
            logger.info("GMSYSADMIN-module- ERROR: módulo con id = " +id+ " no existe.");
            return null;
        }

        logger.info ("GMSYSADMIN-module - Módulo encontrado: id = "+moduloEncontrado.getId()+
                " nombre = "+moduloEncontrado.getName());

        moduloEncontrado.setName(moduleDTO.getNombre());
        moduloEncontrado.setActive(moduleDTO.getActivo());
        moduloEncontrado.setOrder(moduleDTO.getOrden());

        return moduloEncontrado;
    }

    @Override
    public boolean ModuloExists(ModuleDTO moduleDTO) {

        int cantModulosMismoNombre = moduleRepository.conteoModulosMismoNombre(moduleDTO.getNombre());
        if (cantModulosMismoNombre > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean dataIsOK(ModuleDTO moduleDTO) {

        if (moduleDTO.getNombre() == null || moduleDTO.getNombre().length() == 0
                || moduleDTO.getActivo() <= 0 || moduleDTO.getOrden() <= 0) {
            return false;
        }
        return true;
    }
}
