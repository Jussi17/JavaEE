package org.o7planning.springbootjpa.controller;

import java.util.List;
import org.o7planning.springbootjpa.exception.BankTransactionException;
import org.o7planning.springbootjpa.model.BankAccountInfo;
import org.o7planning.springbootjpa.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping("/")
    public String index(Model model) {
        List<BankAccountInfo> list = bankAccountService.listBankAccountInfo();
        model.addAttribute("accounts", list);
        return "index";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String sendMoneyPage(Model model) {
        List<BankAccountInfo> list = bankAccountService.listBankAccountInfo();
        model.addAttribute("accounts", list);
        return "sendMoney";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String sendMoney(
            @RequestParam("fromAccountId") Long fromAccountId,
            @RequestParam("toAccountId") Long toAccountId,
            @RequestParam("amount") double amount,
            Model model) {
        String message = null;
        try {
            bankAccountService.sendMoney(fromAccountId, toAccountId, amount);
            message = "Transfer successful!";
        } catch (BankTransactionException e) {
            message = "Error: " + e.getMessage();
        }
        List<BankAccountInfo> list = bankAccountService.listBankAccountInfo();
        model.addAttribute("accounts", list);
        model.addAttribute("message", message);
        return "index";
    }
}