package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.entities.Pay;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.PayRepository;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PayServiceImpl implements PayService {

    private final PayRepository payRepository;
    private final UserRepository userRepository;

    @Autowired
    public PayServiceImpl(PayRepository payRepository, UserRepository userRepository) {
        this.payRepository = payRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Pay> getAll() {
        return payRepository.findAll();
    }

    @Override
    public Optional<Pay> getById(Long id) {
        return payRepository.findById(id);
    }

    @Override
    public Pay save(Pay pay) {
        return payRepository.save(pay);
    }

    @Override
    public void delete(Long id) {
        payRepository.deleteById(id);

    }

    @Override
    public List<Pay> listAll(String keyword) {
        return null;
    }
}