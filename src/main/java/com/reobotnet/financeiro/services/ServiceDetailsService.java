package com.reobotnet.financeiro.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

@Service
public class ServiceDetailsService {
    private static final String API_URL = "http://localhost:8082/api/dados";

    public ServiceDetails fetchServiceDetails() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Configurando o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        // Fazendo a requisição
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Logando o JSON retornado
        System.out.println("JSON retornado: " + response.body());

        // Desserializando o JSON para o objeto ServiceDetails
        ServiceDetails serviceDetails = objectMapper.readValue(response.body(), ServiceDetails.class);

        // Logando o objeto desserializado
        System.out.println("Objeto desserializado: " + serviceDetails);

        return serviceDetails;
    }
}