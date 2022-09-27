package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

//    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.role.name=:roleName")
//    List<User> findAllByRole(@Param("roleName") String roleName);
    Page<User> findAllByRole_Name(@Param("name")String name, Pageable pageable);
}
