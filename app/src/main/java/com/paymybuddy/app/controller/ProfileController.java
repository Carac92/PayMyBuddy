package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.BankInfo;
import com.paymybuddy.app.model.Bill;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.BankInfoService;
import com.paymybuddy.app.service.BillService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping(value = "/profile")
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private BankInfoService bankInfoService;
    @Autowired
    private BillService billService;

    @GetMapping("/register")
    public String register(@ModelAttribute User user) {
        return "register";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        Boolean result = userService.addUser(user);
        if(result) return "redirect:/login";
        return "redirect:/register";
    }
    @PostMapping("/updateUser")
    public String modifyUser(Principal principal, @ModelAttribute User modifiedUser) {
        userService.updateConnectedUser(principal, modifiedUser);
        return "redirect:/profile";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id") Long id){
        userService.deleteConnectedUser(id);
        return "redirect:/login";
    }
    @GetMapping
    public String getUser(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("bankInfos", user.getBankInfos());
        model.addAttribute("user", user);
        model.addAttribute("bankInfoToAdd", new BankInfo());
        return "profile";
    }
    @PostMapping("/deleteBankInfo")
    public String deleteBankInfoByBankInfoId(@RequestParam(value = "bankInfoId") Long id){
        bankInfoService.deleteById(id);
        return "redirect:/profile";
    }
    @PostMapping("/addBankInfo")
    public String addBankInfo(Principal principal, @ModelAttribute BankInfo bankInfoToAdd){
        bankInfoService.addBankInfo(principal, bankInfoToAdd);
        return "redirect:/profile";
    }
    @GetMapping("/bills")
    public String getBillList(Principal principal, Model model) {
        List<Bill> bills =  billService.getBillList(principal);
        model.addAttribute("bills",bills);
        return "bills";
    }

}
