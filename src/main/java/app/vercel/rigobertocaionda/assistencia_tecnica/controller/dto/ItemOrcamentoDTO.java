package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemOrcamentoDTO(
        @NotNull(message = "Quantidade é obrigatório.")
        @Min(value = 1, message = "A quantidade mínima deve ser 1")
        Integer quantidade,
        @NotNull(message = "A peça é obrigatória.")
        Long pecaId
) {
}
