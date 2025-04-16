package com.reobotnet.financeiro.controllers;

import com.reobotnet.financeiro.dtos.CategoriaResumoDTO;
import com.reobotnet.financeiro.dtos.SaldoDTO;
import com.reobotnet.financeiro.dtos.TransacaoDTO;
import com.reobotnet.financeiro.services.TransacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoDTO> criar(@RequestBody TransacaoDTO dto) {
        TransacaoDTO criada = transacaoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<Page<TransacaoDTO>> listarTodas(Pageable pageable) {
        Page<TransacaoDTO> pagina = transacaoService.listarTodas(pageable);
        return ResponseEntity.ok(pagina);
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
    public ResponseEntity<SaldoDTO> obterSaldo(
            @RequestParam(name = "dataInicial", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,

            @RequestParam(name = "dataFinal", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        SaldoDTO saldo = transacaoService.calcularSaldoDetalhado(dataInicial, dataFinal);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/resumo-categorias")
    public List<CategoriaResumoDTO> resumoPorCategoria(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        return transacaoService.resumoPorCategoria(dataInicial, dataFinal);
    }
}
