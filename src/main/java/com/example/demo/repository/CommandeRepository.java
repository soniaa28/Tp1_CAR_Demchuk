package com.example.demo.repository;

import com.example.demo.entity.Commande;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandeRepository extends CrudRepository<Commande, Long> {

    // Список заказов по email клиента
    List<Commande> findByClientEmailOrderByIdDesc(String email);
}
