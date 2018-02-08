package org.gemesys.administracion.shell.service;

import org.gemesys.administracion.shell.model.User;

/**
 * Created by gperezv on 07-02-18.
 */

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}