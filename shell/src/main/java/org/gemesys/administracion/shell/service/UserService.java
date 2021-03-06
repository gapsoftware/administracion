package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gperezv on 07-02-18.
 */

public interface UserService {
    public User findUserByEmail(String email);
    public User findUserById(Long id);
    public Page<User> findUsersByEmail(String email, Pageable pageable);
    public Page<User> findAllPaginated(int page, int size);
    public void saveUser(User user);
    public void deleteRolUsuario(Long userid, Long rolid);
    public void addRolUsuario(Long userid, Long rolid);
}