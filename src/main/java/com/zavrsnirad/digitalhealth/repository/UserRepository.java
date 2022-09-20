package com.zavrsnirad.digitalhealth.repository;

import com.zavrsnirad.digitalhealth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.role.name=:roleName")
    List<User> findAllByRole(@Param("roleName") String roleName);
}
