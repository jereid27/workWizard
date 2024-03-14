package com.portfolioproj.capstonepostgresql.controllers;


import com.portfolioproj.capstonepostgresql.dto.UserRegistrationDto;
import com.portfolioproj.capstonepostgresql.entities.Consultations;
import com.portfolioproj.capstonepostgresql.entities.Pay;
import com.portfolioproj.capstonepostgresql.entities.SupplyList;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.ConsultationRepository;
import com.portfolioproj.capstonepostgresql.repository.PayRepository;
import com.portfolioproj.capstonepostgresql.repository.SupplyListRepository;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import com.portfolioproj.capstonepostgresql.service.ConsultationService;
import com.portfolioproj.capstonepostgresql.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private SupplyListRepository supplyListRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private  UserService userService;

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PayRepository payRepository;

    public AppController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public String viewLandingPage() {
        return "landingPage";
    }

    @GetMapping("/settings")
    public String settings(Model model, Principal principal) {
            UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
            model.addAttribute("userdetails", userdetails);
            return "settings";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model, Principal principal){
        UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetails", userdetails);

        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        List<Consultations> consultations = consultationRepository.findByUserId(user.getId());
        model.addAttribute("consultations", consultations);

        List<SupplyList> supplyList = supplyListRepository.findByUserId(user.getId());
        model.addAttribute("supplies", supplyList);

        List<Pay> pay = payRepository.findByUserId(user.getId());
        model.addAttribute("payments", pay);


        return "dashboard";
    }

    @GetMapping("/consultations")
    public String viewConsultations(Model model, Consultations consultations ) {
        model.addAttribute("consultations", consultations);
        return "consultations";
    }

    @GetMapping("/supplyList")
    public String viewSupplyList(Model model, SupplyList supplyList){
        model.addAttribute("supplyList", supplyList);
        return "supplyList";
    }

    @GetMapping("/pay")
    public String viewPay(Model model, Pay pay) {
        model.addAttribute("pay", pay);
        return "pay";
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String viewLogin(Model model, UserRegistrationDto userRegistrationDto) {
        model.addAttribute("users", userRegistrationDto);
        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String viewRegister(Model model, UserRegistrationDto userRegistrationDto) {
        model.addAttribute("users",userRegistrationDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("users")UserRegistrationDto userRegistrationDto, Model model, User users,  RedirectAttributes redirectAttributes) {

        User user = userService.findByUsername(userRegistrationDto.getUsername());

        if (user != null) {
            model.addAttribute("userexist, user");
            return "register";
        }

        if (!userService.specialCharacters(userRegistrationDto.getPassword())) {
            if (!userService.specialCharacters(userRegistrationDto.getPassword())) {
                redirectAttributes.addAttribute("error", "true");
                return "redirect:/register";
            }
        }



        userService.save(userRegistrationDto);
        System.out.println("Received registration request for user: {}" + userRegistrationDto.getUsername());
        model.addAttribute("message", "Registered Successfully!");
        return "account-created";
    }

    @Transactional
    @PostMapping("/delete")
    public String deleteUserByUsername(@RequestParam String username) {
       userRepository.deleteByUsername(username);
        return "account-deleted";
    }

}
