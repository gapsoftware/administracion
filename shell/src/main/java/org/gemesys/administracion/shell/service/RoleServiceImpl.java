package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.model.Menu;
import org.gemesys.administracion.shell.model.Role;
import org.gemesys.administracion.shell.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by gperezv on 18-07-18.
 */

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllAvailable(Set<Role> roleslogueados) {
        List<Role> todoslosroles = roleRepository.findAll();
        List<Role> todoslosrolesdisponibles = new ArrayList<Role> (todoslosroles);

        Iterator<Role> iter1 = todoslosroles.iterator();
        while (iter1.hasNext()){
            Role Item1 = (Role) iter1.next();
            Iterator<Role> iter2 = roleslogueados.iterator();
            while (iter2.hasNext()){
                Role Item2 = (Role) iter2.next();
                if (Item1.getRole().equals(Item2.getRole())) {
                    todoslosrolesdisponibles.remove(Item1);
                }
            }
        }
        return todoslosrolesdisponibles;
    }
}
