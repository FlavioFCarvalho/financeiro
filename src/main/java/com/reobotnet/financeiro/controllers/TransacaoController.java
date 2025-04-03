package com.reobotnet.financeiro.controllers;

import com.reobotnet.financeiro.dto.TransacaoDTO;
import com.reobotnet.financeiro.entities.Transacao;
import com.reobotnet.financeiro.mappers.TransacaoMapper;
import com.reobotnet.financeiro.services.CategoriaService;
import com.reobotnet.financeiro.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TransacaoMapper transacaoMapper;

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> listarTodas() {
        List<Transacao> transacoes = transacaoService.listarTodas();
        List<TransacaoDTO> transacoesDTO = transacoes.stream()
                .map(transacaoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transacoesDTO);
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> salvar(@Valid @RequestBody TransacaoDTO transacaoDTO) {
        Transacao transacao = transacaoMapper.toEntity(transacaoDTO);
        Transacao transacaoSalva = transacaoService.salvar(transacao);
        return ResponseEntity.ok(transacaoMapper.toDTO(transacaoSalva));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<TransacaoDTO>> buscarPorCategoria(@PathVariable Long categoriaId) {
        List<Transacao> transacoes = transacaoService.buscarPorCategoria(categoriaId);
        List<TransacaoDTO> transacoesDTO = transacoes.stream()
                .map(transacaoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transacoesDTO);
    }
}