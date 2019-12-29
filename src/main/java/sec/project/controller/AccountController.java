/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.repository.AccountRepository;
import sec.project.domain.Account;

@Controller
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getForm() {
        return "form";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewAccount(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String username, @RequestParam String password,
            @RequestParam String creditcard) {
        if (accountRepository.findByUsername(username) != null) {
            return "redirect:/login";
        }
        Account a = new Account(firstname, lastname, username, password, creditcard);
        accountRepository.save(a);
        return "redirect:/login";   
    }
    
}
