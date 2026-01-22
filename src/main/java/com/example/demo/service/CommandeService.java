package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.repository.CommandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository){
        this.commandeRepository = commandeRepository;
    }
    public void createCommande(Client client){
        commandeRepository.save(new Commande(client));
    }
    public List<Commande> getCommandesForClient(Client client){
        return commandeRepository.findByClientEmailOrderByIdDesc(client.getEmail());
    }
}
