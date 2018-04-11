package org.gemesys.administracion.shell.repository;

import org.gemesys.administracion.shell.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gperezv on 10-04-18.
 */

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
