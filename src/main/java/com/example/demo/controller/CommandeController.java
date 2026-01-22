package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.CommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class CommandeController {
    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    @GetMapping("/store/commandes")
    public ModelAndView commandes(HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        if (client == null) {
            return new ModelAndView("redirect:/store/login");
        }

        return new ModelAndView("commandes", Map.of(
                "client", client,
                "commandes", commandeService.getCommandesForClient(client)
        ));
    }
    @PostMapping("/store/commandes/create")
    public RedirectView createCommande(HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        if (client == null) {
            return new RedirectView("/store/login");
        }

        commandeService.createCommande(client);
        return new RedirectView("/store/commandes");
    }
}
