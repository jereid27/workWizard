package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.entities.SupplyList;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.SupplyListRepository;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SupplyListServiceImpl implements SupplyListService {

    private final SupplyListRepository supplyListRepository;
    private final UserRepository userRepository;

    @Autowired
    public SupplyListServiceImpl(SupplyListRepository supplyListRepository, UserRepository userRepository) {
        this.supplyListRepository = supplyListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SupplyList> getAll() {
        return supplyListRepository.findAll();
    }

    @Override
    public Optional<SupplyList> getById(Long id) {
        return supplyListRepository.findById(id);
    }

    @Override
    public SupplyList save(SupplyList supplyList) {
        return supplyListRepository.save(supplyList);
    }

    @Override
    public void delete(Long id) {
        supplyListRepository.deleteById(id);
    }

    @Override
    public List<SupplyList> listAll(String keyword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userRepository.findByUsername(currentPrincipalName);

        if (currentUser != null) {

            if (keyword != null) {
                return supplyListRepository.search(currentUser.getId(), keyword.toLowerCase());
            }

            return supplyListRepository.findByUserId(currentUser.getId());
        } else {
            return Collections.emptyList();
        }
    }
}
