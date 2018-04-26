package org.gemesys.administracion.shell.repository;

import org.gemesys.administracion.shell.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gperezv on 10-04-18.
 */

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    @Query
    ("select count (M.id) from Module M where M.name = :name")
    int conteoModulosMismoNombre(@Param("name") String name);

    @Query
    ("select M from Module M where M.active=1 order by M.order ASC")
    ArrayList<Module> findAllActive();

}
