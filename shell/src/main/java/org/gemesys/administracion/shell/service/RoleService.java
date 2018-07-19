package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by gperezv on 18-07-18.
 */

public interface RoleService {
    List<Role> findAllAvailable(Set<Role> roleslogueados);
}
