package com.reobotnet.financeiro.services;

import com.reobotnet.financeiro.entities.Transacao;
import com.reobotnet.financeiro.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Transacao salvar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> buscarPorCategoria(Long categoriaId) {
        return transacaoRepository.findByCategoriaId(categoriaId);
    }
}