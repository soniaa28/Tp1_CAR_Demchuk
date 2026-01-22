package com.example.demo.controller;

import com.example.demo.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/store/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }
    @GetMapping("/store/register")
    public ModelAndView registerPage(@RequestParam(required=false) String error) {
        return new ModelAndView("register", Map.of("error", error == null ? "" : error ));
    }
    @PostMapping("/store/register")
    public RedirectView register(@RequestParam String email,@RequestParam String password, @RequestParam String nom, @RequestParam String prenom )
    {
        if (clientService.existsByEmail(email)){
            return new RedirectView("/store/register?error=email_exists");
        }
        clientService.register(email,password,nom,prenom);
        return new RedirectView("/store/home");

    }
    @GetMapping("/")
    public RedirectView root(){
        return new RedirectView("/store/home");
    }
}
