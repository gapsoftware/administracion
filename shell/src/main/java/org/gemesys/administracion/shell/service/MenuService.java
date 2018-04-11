package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.dto.MenuDTO;
import org.gemesys.administracion.shell.dto.ModuleDTO;
import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Module;

import java.util.List;

/**
 * Created by gperezv on 10-04-18.
 */
public interface MenuService {

    List<Menu> findAll();
    Menu findById(int id);
    void save(Menu  menu);
    void update(Menu menu);
    void delete(int id);
    Menu buildMenu(MenuDTO menuDTO);

}
