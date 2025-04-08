package com.reobotnet.financeiro.repositories;


import com.reobotnet.financeiro.entities.Transacao;
import com.reobotnet.financeiro.enuns.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.tipo = :tipo")
    BigDecimal somarPorTipo(@Param("tipo") TipoTransacao tipo);
}
