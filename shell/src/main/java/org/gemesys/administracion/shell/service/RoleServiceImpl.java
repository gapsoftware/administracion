package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Role;
import org.gemesys.administracion.shell.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gperezv on 18-07-18.
 */

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
