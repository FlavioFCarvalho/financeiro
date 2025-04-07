package com.reobotnet.financeiro.services;

import com.reobotnet.financeiro.dtos.TransacaoDTO;
import com.reobotnet.financeiro.entities.Categoria;
import com.reobotnet.financeiro.entities.Transacao;
import com.reobotnet.financeiro.enuns.MeioPagamento;
import com.reobotnet.financeiro.enuns.TipoTransacao;
import com.reobotnet.financeiro.exceptions.DataLancamentoInvalidaException;
import com.reobotnet.financeiro.repositories.CategoriaRepository;
import com.reobotnet.financeiro.repositories.TransacaoRepository;
import com.reobotnet.financeiro.utils.MessageUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MessageUtil messageUtil;

    public TransacaoDTO criar(TransacaoDTO dto) {
        Transacao transacao = fromDTO(dto);

        // Validação: data de lançamento não pode ser no futuro
        if (transacao.getDataLancamento() != null &&
                transacao.getDataLancamento().isAfter(LocalDate.now())) {
            throw new DataLancamentoInvalidaException(messageUtil.get("transacao.data.lancamento.invalida"));
        }

        // Regra para definir o vencimento baseado na data de lançamento
        if (transacao.getTipo() == TipoTransacao.DEBITO &&
                transacao.getMeioPagamento() == MeioPagamento.CARTAO_CREDITO &&
                transacao.getDataVencimento() == null) {

            LocalDate dataBase = transacao.getDataLancamento() != null
                    ? transacao.getDataLancamento()
                    : LocalDate.now();

            LocalDate vencimento = dataBase.plusMonths(1).withDayOfMonth(5);
            transacao.setDataVencimento(vencimento);
        }

        transacao = transacaoRepository.save(transacao);
        return toDTO(transacao);
    }

    public List<TransacaoDTO> listarTodas() {
        return transacaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TransacaoDTO buscarPorId(Long id) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada com ID: " + id));
        return toDTO(transacao);
    }

    public TransacaoDTO atualizar(Long id, TransacaoDTO dto) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada com ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + dto.getCategoriaId()));

        transacao.setValor(dto.getValor());
        transacao.setTipo(dto.getTipo());
        transacao.setCategoria(categoria);
        transacao.setDataLancamento(dto.getDataLancamento());
        transacao.setDataVencimento(dto.getDataVencimento());
        transacao.setDescricao(dto.getDescricao());
        transacao.setMeioPagamento(dto.getMeioPagamento());

        transacao = transacaoRepository.save(transacao);
        return toDTO(transacao);
    }

    public void deletar(Long id) {
        if (!transacaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Transação não encontrada com ID: " + id);
        }
        transacaoRepository.deleteById(id);
    }

    private TransacaoDTO toDTO(Transacao transacao) {
        return new TransacaoDTO(
                transacao.getId(),
                transacao.getValor(),
                transacao.getTipo(),
                transacao.getCategoria().getId(),
                transacao.getDataLancamento(),
                transacao.getDataVencimento(),
                transacao.getDescricao(),
                transacao.getMeioPagamento()
        );
    }

    private Transacao fromDTO(TransacaoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + dto.getCategoriaId()));

        return new Transacao(
                dto.getId(),
                dto.getValor(),
                dto.getTipo(),
                categoria,
                dto.getDataLancamento(),
                dto.getDataVencimento(),
                dto.getDescricao(),
                dto.getMeioPagamento()
        );
    }
}
