package org.gemesys.administracion.shell.service;

import lombok.Builder;
import org.gemesys.administracion.shell.dto.ModuleDTO;
import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Module;
import org.gemesys.administracion.shell.model.Role;
import org.gemesys.administracion.shell.repository.ModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by gperezv on 10-04-18.
 */

@Service
public class ModuleServiceImpl implements ModuleService {

    private final static Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public ArrayList<Module> findAllActiveNoEmpty() {

        //Obtiene los módulos que están en status activo
        ArrayList<Module> modulosactivos = moduleRepository.findAllActive();

        logger.debug("modulos activos "+modulosactivos.size());
        ArrayList<Module> modulosactivosNoVacios = new ArrayList<>();

        //Obtiene los módulos que no están vacíos de menús
        Iterator<Module> iter = modulosactivos.iterator();
        while (iter.hasNext()) {
            Module item = iter.next();
            Set<Menu> menus = item.getMenus();
            if (!menus.isEmpty()) {
                modulosactivosNoVacios.add(item);
            }
        }
        logger.debug("modulos activos no vacíos "+modulosactivosNoVacios.size());
        return modulosactivosNoVacios;
    }

    @Override
    public ArrayList<Module> findAllActiveNoEmptyAndAuth(Collection roleslogueados) {

        logger.info("GMSYSADMIN- roles autorizados: " + roleslogueados + "");

        //Obtiene los módulos que están en status activo y que contienen menús
        ArrayList<Module> modulosactivosNoVacios = findAllActiveNoEmpty();
        ArrayList<Module> modulosautorizados = new ArrayList<>();


        Iterator<Module> iterModule = modulosactivosNoVacios.iterator();
        while (iterModule.hasNext()) {
            //Obtiene los menús que coinciden con los roles del usuario conectado y los vacía en Set menusautorizados
            Module itemModule = iterModule.next();
            Set<Menu> menus = itemModule.getMenus();
            Set<Menu> menusautorizados = new HashSet<>();
            Iterator<Menu> iterMenu = menus.iterator();
            while (iterMenu.hasNext()) {
                Menu itemMenu = iterMenu.next();
                Set<Role> roles = itemMenu.getRoles();
                Iterator<Role> iterRole = roles.iterator();
                while (iterRole.hasNext()) {
                    Role itemRole = iterRole.next();
                    Iterator<SimpleGrantedAuthority> iterRolLogueado = roleslogueados.iterator();
                    while (iterRolLogueado.hasNext()) {
                        SimpleGrantedAuthority itemRolLogueado = iterRolLogueado.next();
                        if (itemRolLogueado.toString().equals(itemRole.getRole())) {
                            menusautorizados.add(itemMenu);
                        }
                    }
                }
            }

            logger.info("menús autorizados "+menusautorizados.size());

            if (menusautorizados != null) {
                if (!menusautorizados.isEmpty()) {
                    itemModule.setMenus(menusautorizados);
                    modulosautorizados.add(itemModule);
                }
            }
        }

        Iterator<Module> iterModulosAutorizados = modulosautorizados.iterator();
        while (iterModulosAutorizados.hasNext()) {
            Module itemModuloAutorizado = iterModulosAutorizados.next();
            logger.info("MODULO " + itemModuloAutorizado.getName() + "--AUTORIZADO--");
            Iterator<Menu> iterMenuAutorizado = itemModuloAutorizado.getMenus().iterator();
            while (iterMenuAutorizado.hasNext()) {
                Menu itemMenuAutorizado = iterMenuAutorizado.next();
                logger.info(itemMenuAutorizado.getName());
            }
        }
        return modulosautorizados;
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

        if (moduloEncontrado == null) {
            logger.info("GMSYSADMIN-module- ERROR: módulo con id = " + id + " no existe.");
            return null;
        }

        logger.info("GMSYSADMIN-module - Módulo encontrado: id = " + moduloEncontrado.getId() +
                " nombre = " + moduloEncontrado.getName());

        moduloEncontrado.setName(moduleDTO.getNombre());
        moduloEncontrado.setActive(moduleDTO.getActivo());
        moduloEncontrado.setSortOrder(moduleDTO.getOrden());

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
