package com.portfolioproj.capstonepostgresql.repository;

import com.portfolioproj.capstonepostgresql.entities.Consultations;
import com.portfolioproj.capstonepostgresql.entities.SupplyList;
import com.portfolioproj.capstonepostgresql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyListRepository extends JpaRepository<SupplyList, Long> {

    List<SupplyList> findByUserId(Long userId);
    List<SupplyList> findAllByUserId(Long userId);

    @Query("SELECT s FROM SupplyList s WHERE s.user.id = :userId AND LOWER(s.supplyName) LIKE LOWER(concat('%', :supplyKeyword, '%'))")
    List<SupplyList> search(@Param("userId") Long userId, @Param("supplyKeyword") String supplyKeyword);

}