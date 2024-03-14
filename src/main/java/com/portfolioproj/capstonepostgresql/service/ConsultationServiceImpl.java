package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.dto.consultationsDto;
import com.portfolioproj.capstonepostgresql.entities.Consultations;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.ConsultationRepository;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    private final UserRepository userRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, UserRepository userRepository) {
        this.consultationRepository = consultationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Consultations> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public Optional<Consultations> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public Consultations save(Consultations consultations) {
        return consultationRepository.save(consultations);
    }

    @Override
    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public List<Consultations> listAll(String keyword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userRepository.findByUsername(currentPrincipalName);

        if (currentUser != null) {
            if (keyword != null) {
                return consultationRepository.search(currentUser.getId(), keyword.toLowerCase());
            }
            return consultationRepository.findAllByUserId(currentUser.getId());
        } else {
            return Collections.emptyList();
        }
    }
}



