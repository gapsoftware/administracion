package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.model.Role;
import org.gemesys.administracion.shell.model.User;
import org.gemesys.administracion.shell.repository.RoleRepository;
import org.gemesys.administracion.shell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by gperezv on 07-02-18.
 */

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Page<User> findUsersByEmail(String email, Pageable pageable) {
        return (Page<User>) userRepository.findByEmail(email, pageable);
    }

    @Override
    public Page<User> findAllPaginated(int page, int size) {
        return (Page<User>) userRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("GUEST");
        user.setRoles(new TreeSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void deleteRolUsuario(Long userid, Long rolid) {
        userRepository.deleteRolUsuario(userid, rolid);
    }

    @Override
    public void addRolUsuario(Long userid, Long rolid) {
        userRepository.addRolUsuario(userid, rolid);
    }
}