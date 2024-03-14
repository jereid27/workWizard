package com.portfolioproj.capstonepostgresql.controllers;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ConsultationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private SupplyListRepository supplyListRepository;

    public ConsultationController(UserRepository userRepository,
                                  ConsultationRepository consultationRepository,
                                  UserDetailsService userDetailsService, UserService userService,
                                  ConsultationService consultationService, PayRepository payRepository,
                                  SupplyListRepository supplyListRepository) {
        this.userRepository = userRepository;
        this.consultationRepository = consultationRepository;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.consultationService = consultationService;
        this.payRepository = payRepository;
        this.supplyListRepository = supplyListRepository;
    }

    @PostMapping("/consultations")
    public String uploadImage(@ModelAttribute Consultations consultations,
                              Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        consultations.setUser(user);
        consultationRepository.save(consultations);

        return "redirect:/dashboard";
    }

    @GetMapping("/consultations/{id}")
    public String viewConsultationDetails(@PathVariable Long id, Model model) {
        Consultations consultations = consultationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation ID: " + id));

        model.addAttribute("consultations", consultations);

        return "viewConsultations";
    }

    @PostMapping("/consultations/{id}")
    public String deleteConsultation(@PathVariable Long id, @RequestParam("_method") String method) {
        if ("delete".equals(method.toLowerCase())) {
            consultationRepository.deleteById(id);
            return "redirect:/dashboard";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/consultations/update/{id}")
    public String showUpdateConsultationForm(@PathVariable Long id, Model model) {
        Consultations consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation ID: " + id));

        model.addAttribute("consultation", consultation);
        return "update-consultation";
    }

    @PostMapping("/consultations/update/{id}")
    public String updateConsultation(@PathVariable Long id, @ModelAttribute Consultations updatedConsultation) {
        Consultations consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation ID: " + id));

        consultation.setName(updatedConsultation.getName());
        consultation.setPhoneNumber(updatedConsultation.getPhoneNumber());
        consultation.setDate(updatedConsultation.getDate());
        consultation.setNotes(updatedConsultation.getNotes());
        consultationRepository.save(consultation);

        return "redirect:/dashboard";
    }
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model, Principal principal) {
        List<Consultations> searchResults = consultationService.listAll(keyword);
        model.addAttribute("consultations", searchResults);

        UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetails", userdetails);

        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        List<Pay> pay = payRepository.findByUserId(user.getId());
        model.addAttribute("payments", pay);

        List<SupplyList> supplyList = supplyListRepository.findByUserId(user.getId());
        model.addAttribute("supplies", supplyList);

        return "dashboard";
    }
}
