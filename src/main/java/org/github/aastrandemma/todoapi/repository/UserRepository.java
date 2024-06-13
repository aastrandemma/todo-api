package org.github.aastrandemma.todoapi.repository;

import org.github.aastrandemma.todoapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.active = :status WHERE u.email = :email")
    void updateActiveByEmail(@Param("email") String email, @Param("status") boolean active);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    void updatePasswordByEmail(@Param("email") String email, @Param("password") String password);
}