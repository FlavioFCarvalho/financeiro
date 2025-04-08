package com.reobotnet.financeiro.controllers;

import com.reobotnet.financeiro.dtos.TransacaoDTO;
import com.reobotnet.financeiro.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> criar(@RequestBody TransacaoDTO dto) {
        TransacaoDTO criada = transacaoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> listarTodas() {
        List<TransacaoDTO> lista = transacaoService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> buscarPorId(@PathVariable Long id) {
        TransacaoDTO dto = transacaoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoDTO> atualizar(@PathVariable Long id, @RequestBody TransacaoDTO dto) {
        TransacaoDTO atualizada = transacaoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/saldo")
    public ResponseEntity<BigDecimal> obterSaldo() {
        BigDecimal saldo = transacaoService.calcularSaldo();
        return ResponseEntity.ok(saldo);
    }
}
