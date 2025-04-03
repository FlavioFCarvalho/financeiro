package com.reobotnet.financeiro.controllers;

import com.reobotnet.financeiro.entities.Transacao;
import com.reobotnet.financeiro.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodas() {
        return ResponseEntity.ok(transacaoService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Transacao> salvar(@RequestBody Transacao transacao) {
        return ResponseEntity.ok(transacaoService.salvar(transacao));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Transacao>> buscarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(transacaoService.buscarPorCategoria(categoriaId));
    }
}