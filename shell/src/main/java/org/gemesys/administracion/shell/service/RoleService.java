package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Role;

import java.util.List;

/**
 * Created by gperezv on 18-07-18.
 */

public interface RoleService {
    List<Role> findAll();
}
