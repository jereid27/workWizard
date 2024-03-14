package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.dto.consultationsDto;
import com.portfolioproj.capstonepostgresql.entities.Consultations;

import java.util.List;
import java.util.Optional;

public interface ConsultationService {

    List<Consultations> getAllConsultations();
    Optional<Consultations> getConsultationById(Long id);
    Consultations save (Consultations consultations);
    void delete (Long id);
    List<Consultations> listAll(String keyword);

}
