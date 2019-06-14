package org.gemesys.administracion.shell.repository;

import org.gemesys.administracion.shell.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gperezv on 07-02-18.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

    @Query("select R from Role R where R.role <> 'SUPER'")
    List<Role> findAll();
}
