package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u INNER JOIN u.role WHERE u.role.name LIKE :roleName AND (u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% OR u.email LIKE %:keyword% OR u.city LIKE %:keyword%)")
    Page<User> findAllDoctorsFilterable(@Param("roleName") String roleName, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT u FROM User u INNER JOIN u.role WHERE u.role.name LIKE :roleName ")
    Page<User> findAllDoctors(@Param("roleName") String roleName, Pageable pageable);
}
