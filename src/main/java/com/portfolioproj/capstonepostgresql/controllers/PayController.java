package com.portfolioproj.capstonepostgresql.controllers;

import com.portfolioproj.capstonepostgresql.entities.Consultations;
import com.portfolioproj.capstonepostgresql.entities.Pay;
import com.portfolioproj.capstonepostgresql.entities.SupplyList;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.ConsultationRepository;
import com.portfolioproj.capstonepostgresql.repository.PayRepository;
import com.portfolioproj.capstonepostgresql.repository.SupplyListRepository;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import com.portfolioproj.capstonepostgresql.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PayController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private PayService payService;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private SupplyListRepository supplyListRepository;

    public PayController(UserRepository userRepository, UserDetailsService userDetailsService,
                         PayRepository payRepository, PayService payService,
                         ConsultationRepository consultationRepository,
                         SupplyListRepository supplyListRepository) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.payRepository = payRepository;
        this.payService = payService;
        this.consultationRepository = consultationRepository;
        this.supplyListRepository = supplyListRepository;
    }

    @PostMapping("/addPayment")
    public String addPayment(@ModelAttribute("payment") Pay newPayment,
                            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        newPayment.setUser(user);
        payRepository.save(newPayment);

        return "redirect:/dashboard";
    }

    @GetMapping("/pay/delete/{id}")
    public String deletePayment(@PathVariable("id") Long id) {
        payRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/pay/update/{id}")
    public String updatePay(@PathVariable Long id, @ModelAttribute Pay updatedPay) {
        Pay pay = payRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation ID: " + id));

        pay.setDate(updatedPay.getDate());
        pay.setAmount(updatedPay.getAmount());
        pay.setArtistPercentage(updatedPay.getArtistPercentage());
        payRepository.save(pay);

        return "redirect:/dashboard";
    }

    //This controller method demonstrates a payment report with multiple rows, columns, date-time stamp, and title.
    @GetMapping("/paymentReport")
    public String paymentReport(Model model, Principal principal) {
        UserDetails userdetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetails", userdetails);


        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        model.addAttribute("username", username);

        List<Pay> pay = payRepository.findByUserId(user.getId());
        model.addAttribute("payments", pay);

        List<Pay> payments = payService.getAll();

        double totalAmount = payments.stream().mapToDouble(Pay::getAmount).sum();
        double totalArtistEarnings = payments.stream().mapToDouble(payment -> (double) (payment.getAmount() * payment.getArtistPercentage()) / 100).sum();
        double totalShopEarnings = payments.stream().mapToDouble(payment -> payment.getAmount() - ((double) (payment.getAmount() * payment.getArtistPercentage()) / 100)).sum();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        model.addAttribute("currentDateTime", currentDateTime);

        model.addAttribute("payments", payments);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalArtistEarnings", totalArtistEarnings);
        model.addAttribute("totalShopEarnings", totalShopEarnings);
        return "paymentReport";
    }
}
