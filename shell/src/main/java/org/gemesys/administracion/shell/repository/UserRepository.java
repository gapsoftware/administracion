package org.gemesys.administracion.shell.repository;

import org.gemesys.administracion.shell.model.User;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gperezv on 07-02-18.
 */

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    @Query
    ("select U from User U where U.email like LOWER(CONCAT('%',:email,'%')) OR  " +
                                "U.email like UPPER(CONCAT('%',:email,'%'))")
    Page<User> findByEmail(@Param("email") String email, Pageable pageable);

    User findByEmail(String email);
    User findById(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from user_role where user_id= :usuarioid and role_id = :rolid", nativeQuery = true)
    void deleteRolUsuario(@Param("usuarioid") Long usuarioid, @Param("rolid") Long rolid);

    @Modifying
    @Transactional
    @Query(value = "insert into user_role values (:usuarioid,:rolid)", nativeQuery = true)
    void addRolUsuario(@Param("usuarioid") Long usuarioid, @Param("rolid") Long rolid);
}
