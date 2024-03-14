package com.portfolioproj.capstonepostgresql.controllers;

import com.portfolioproj.capstonepostgresql.entities.Consultations;
import com.portfolioproj.capstonepostgresql.entities.Pay;
import com.portfolioproj.capstonepostgresql.entities.SupplyList;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.ConsultationRepository;
import com.portfolioproj.capstonepostgresql.repository.PayRepository;
import com.portfolioproj.capstonepostgresql.repository.SupplyListRepository;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import com.portfolioproj.capstonepostgresql.service.SupplyListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class SupplyListController {


    private final UserRepository userRepository;


    private final SupplyListRepository supplyListRepository;

    private final SupplyListService supplyListService;

    private final UserDetailsService userDetailsService;
    private final PayRepository payRepository;

    private final ConsultationRepository consultationRepository;


    public SupplyListController(UserRepository userRepository, SupplyListRepository supplyListRepository,
                                SupplyListService supplyListService, UserDetailsService userDetailsService,
                                PayRepository payRepository, ConsultationRepository consultationRepository) {
        this.userRepository = userRepository;
        this.supplyListRepository = supplyListRepository;
        this.supplyListService = supplyListService;
        this.userDetailsService = userDetailsService;
        this.payRepository = payRepository;
        this.consultationRepository = consultationRepository;
    }

    @PostMapping("/supplyLists")
    public String addSupply(@ModelAttribute("nSupply") @Valid SupplyList nSupply, BindingResult result,
                            Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        nSupply.setUser(user);
        supplyListRepository.save(nSupply);

        return "redirect:/dashboard";
    }

    @GetMapping("/supplyList/{id}")
    public String viewSupply(@PathVariable("id") Long id, Model model) {
        SupplyList supply = supplyListRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid supply ID: " + id));

        model.addAttribute("supply", supply);

        return "viewSupply";
    }

    @PostMapping("/deleteSupply")
    public String deleteSupply(@RequestParam("supplyId") Long supplyId) {
        supplyListRepository.deleteById(supplyId);
        return "redirect:/dashboard";
    }

    @GetMapping("/supplyList/update/{id}")
    public String showUpdateSupplyForm(@PathVariable Long id, Model model) {
        SupplyList supplyList = supplyListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation ID: " + id));

        model.addAttribute("supply", supplyList);
        return "update-supply";
    }

    @PostMapping("/supplyList/update/{id}")
    public String updateSupply(@PathVariable Long id, @ModelAttribute SupplyList updatedSupply) {
        SupplyList supplyList = supplyListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation ID: " + id));

        supplyList.setSupplyName(updatedSupply.getSupplyName());
        supplyList.setQuantity(updatedSupply.getQuantity());
        supplyListRepository.save(supplyList);

        return "redirect:/dashboard";
    }

    //This controller method demonstrates a search functionality with multiple rows and displays.
    @GetMapping("/supplyList/search")
    public String searchSupplyList(Model model, @RequestParam("supplyKeyword") String supplyKeyword, Principal principal) {
        List<SupplyList> searchResults = supplyListService.listAll(supplyKeyword);
        model.addAttribute("supplies", searchResults);

        UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetails", userdetails);

        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        List<Pay> pay = payRepository.findByUserId(user.getId());
        model.addAttribute("payments", pay);

        List<Consultations> consultations = consultationRepository.findByUserId(user.getId());
        model.addAttribute("consultations", consultations);
        return "dashboard";
    }
}
