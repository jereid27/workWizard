package com.portfolioproj.capstonepostgresql.repository;

import com.portfolioproj.capstonepostgresql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);
    void deleteByUsername(String username);
}
