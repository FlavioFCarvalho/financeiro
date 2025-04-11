package com.reobotnet.financeiro.controllers;

import com.reobotnet.financeiro.services.ServiceDetails;
import com.reobotnet.financeiro.services.ServiceDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Adiciona um prefixo para os endpoints
public class ServiceDetailsController {

    private final ServiceDetailsService serviceDetailsService;

    // Injeção de dependência via construtor (recomendado)
    public ServiceDetailsController(ServiceDetailsService serviceDetailsService) {
        this.serviceDetailsService = serviceDetailsService;
    }

    @GetMapping("/service-details")
    public ResponseEntity<ServiceDetails> getServiceDetails() {
        try {
            ServiceDetails serviceDetails = serviceDetailsService.fetchServiceDetails();
            return ResponseEntity.ok(serviceDetails); // Retorna 200 OK com o objeto
        } catch (Exception e) {
            // Log da exceção (opcional)
            e.printStackTrace();
            // Retorna 500 Internal Server Error com uma mensagem de erro
            return ResponseEntity.internalServerError().build();
        }
    }
}