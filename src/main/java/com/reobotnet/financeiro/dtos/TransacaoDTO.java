package com.reobotnet.financeiro.dto;

import com.reobotnet.financeiro.enuns.TipoTransacao;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransacaoDTO {

    private Long id;

    @NotNull(message = "O valor não pode ser nulo.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que 0.")
    private BigDecimal valor;

    @NotNull(message = "O tipo de transação não pode ser nulo.")
    private TipoTransacao tipo;

    @NotNull(message = "O ID da categoria não pode ser nulo.")
    private Long categoriaId;

    @NotNull(message = "A data não pode ser nula.")
    private LocalDateTime data;
}