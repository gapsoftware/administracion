package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.dto.ModuleDTO;
import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by gperezv on 10-04-18.
 */
public interface ModuleService {

    void save(Module module);
    void update(Module module);
    void delete(int id);
    Module findById(int id);
    Module buildModule(ModuleDTO moduleDTO);
    Module findModule(int id, ModuleDTO moduleDTO);
    ArrayList<Module> findAllActiveNoEmpty();
    ArrayList<Module> findAllActiveNoEmptyAndAuth(Collection roleslogueados);
    boolean ModuloExists(ModuleDTO moduleDTO);
    boolean dataIsOK(ModuleDTO moduleDTO);

}
