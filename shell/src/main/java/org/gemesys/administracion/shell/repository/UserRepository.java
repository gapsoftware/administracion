package org.gemesys.administracion.shell.repository;

import org.gemesys.administracion.shell.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gperezv on 07-02-18.
 */

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    User findByEmail(String email);
}
