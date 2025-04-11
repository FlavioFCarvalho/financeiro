package com.reobotnet.financeiro.services;

import com.reobotnet.financeiro.dtos.SaldoDTO;
import com.reobotnet.financeiro.dtos.TransacaoDTO;
import com.reobotnet.financeiro.entities.Transacao;
import com.reobotnet.financeiro.entities.Categoria;
import com.reobotnet.financeiro.enuns.MeioPagamento;
import com.reobotnet.financeiro.enuns.TipoTransacao;
import com.reobotnet.financeiro.exceptions.DataLancamentoInvalidaException;
import com.reobotnet.financeiro.repositories.CategoriaRepository;
import com.reobotnet.financeiro.repositories.TransacaoRepository;
import com.reobotnet.financeiro.utils.MessageUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

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

        validarDataLancamento(transacao);
        aplicarVencimentoSemCartaoCredito(transacao);
        validarDataVencimentoObrigatoria(transacao);
        aplicarVencimentoCartao(transacao);
        validarDataVencimentoPosterior(transacao);

        transacao = transacaoRepository.save(transacao);
        return toDTO(transacao);
    }

    private void validarDataLancamento(Transacao transacao) {
        if (transacao.getDataLancamento() != null &&
                transacao.getDataLancamento().isAfter(LocalDate.now())) {
            throw new DataLancamentoInvalidaException(messageUtil.get("transacao.data.lancamento.invalida"));
        }
    }

    private void validarDataVencimentoObrigatoria(Transacao transacao) {
        if (transacao.getMeioPagamento() != MeioPagamento.CARTAO_CREDITO &&
                transacao.getDataVencimento() == null) {
            throw new DataLancamentoInvalidaException(messageUtil.get("transacao.data.vencimento.null"));
        }
    }

    private void aplicarVencimentoCartao(Transacao transacao) {
        if (transacao.getMeioPagamento() == MeioPagamento.CARTAO_CREDITO &&
                transacao.getDataVencimento() == null) {

            LocalDate dataBase = transacao.getDataLancamento() != null
                    ? transacao.getDataLancamento()
                    : LocalDate.now();

            LocalDate vencimento = dataBase.plusMonths(1).withDayOfMonth(5);
            transacao.setDataVencimento(vencimento);
        }
    }

    private void aplicarVencimentoSemCartaoCredito(Transacao transacao) {
        if ((transacao.getMeioPagamento() == MeioPagamento.TICKET ||
                transacao.getMeioPagamento() == MeioPagamento.DINHEIRO ||
                 transacao.getMeioPagamento() == MeioPagamento.CARTAO_DEBITO ) &&
                (transacao.getTipo() == TipoTransacao.DEBITO ||
                transacao.getTipo() == TipoTransacao.CREDITO) &&
                transacao.getDataLancamento() != null) {

            transacao.setDataVencimento(transacao.getDataLancamento());
        }
    }



    private void validarDataVencimentoPosterior(Transacao transacao) {
        if (transacao.getDataLancamento() != null &&
                transacao.getDataVencimento() != null &&
                transacao.getDataVencimento().isBefore(transacao.getDataLancamento())) {
            throw new DataLancamentoInvalidaException(messageUtil.get("transacao.data.vencimento.menor.lancamento"));
        }
    }


    public Page<TransacaoDTO> listarTodas(Pageable pageable) {
        return transacaoRepository.findAll(pageable)
                .map(this::toDTO); // converte cada Transacao em TransacaoDTO
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

    public SaldoDTO calcularSaldoDetalhado(LocalDate dataInicial, LocalDate dataFinal) {
        BigDecimal totalCredito = transacaoRepository.somarPorTipoComData(TipoTransacao.CREDITO, dataInicial, dataFinal);
        BigDecimal totalDebito = transacaoRepository.somarPorTipoComData(TipoTransacao.DEBITO, dataInicial, dataFinal);

        return new SaldoDTO(totalCredito, totalDebito);
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
