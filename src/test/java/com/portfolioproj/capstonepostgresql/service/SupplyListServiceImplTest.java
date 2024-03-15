package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.entities.SupplyList;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.SupplyListRepository;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplyListServiceImplTest {



    @Test
    void testGetAll() {

        SupplyListRepository mockSupplyListRepository = Mockito.mock(SupplyListRepository.class);
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);


        User user = new User();
        user.setId(1L);


        Mockito.when(mockUserRepository.findByUsername(Mockito.anyString())).thenReturn(user);

        List<SupplyList> expectedSupplyLists = Arrays.asList(new SupplyList(), new SupplyList());
        Mockito.when(mockSupplyListRepository.findAll()).thenReturn(expectedSupplyLists);


        SupplyListServiceImpl supplyListService = new SupplyListServiceImpl(mockSupplyListRepository, mockUserRepository);
        List<SupplyList> actualSupplyLists = supplyListService.getAll();


        assertEquals(expectedSupplyLists.size(), actualSupplyLists.size());
    }
}
