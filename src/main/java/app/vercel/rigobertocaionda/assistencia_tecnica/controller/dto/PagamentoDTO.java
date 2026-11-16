package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.MetodoPagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PagamentoDTO(
        Long id,
        @NotNull(message = "Valor é obrigatório.")
        @DecimalMin(value = "0.01", message = "O valor deve ser superior a zero.")
        BigDecimal valor,
        @NotNull(message = "Método de pagamento é obrigatório.")
        MetodoPagamento metodoPagamento
) {
}
