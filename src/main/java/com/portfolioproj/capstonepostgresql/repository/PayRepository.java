package com.portfolioproj.capstonepostgresql.repository;

import com.portfolioproj.capstonepostgresql.entities.Consultations;
import com.portfolioproj.capstonepostgresql.entities.Pay;
import com.portfolioproj.capstonepostgresql.entities.SupplyList;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {
    List<Pay> findByUserId(Long userId);

}
