package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrcamentoDTO(
        @NotNull(message = "Valor mão de obra é obrigatório.")
        @DecimalMin("0.00")
        BigDecimal valorMaoObras
) {
}
