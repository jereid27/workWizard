package com.portfolioproj.capstonepostgresql.repository;

import com.portfolioproj.capstonepostgresql.entities.Consultations;
import com.portfolioproj.capstonepostgresql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultations, Long> {

    List<Consultations> findByUserId(Long userId);

    @Query("SELECT c FROM Consultations c WHERE c.user.id = :userId AND LOWER(c.name) LIKE LOWER(concat('%', :keyword, '%'))")
    List<Consultations> search(@Param("userId") Long userId, @Param("keyword") String keyword);

    List<Consultations> findAllByUserId(Long userId);

    List<Consultations> findByDate(LocalDate date);

    //This demonstrates inheritance as the interface inherits
    // the JpaRepository interface's methods and functionality.
}
