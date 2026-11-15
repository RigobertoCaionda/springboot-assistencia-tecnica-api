package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrdemServicoDTO(
        @NotBlank(message = "descrição é obrigatória.")
        @Size(max = 1000, message = "A descrição não pode ultrapassar 1000 caracteres.")
        String descricao,
        @NotNull(message = "prioridade não pode ser nula.")
        Prioridade prioridade,
        @NotNull(message = "equipamentoId não pode ser nulo.")
        Long equipamentoId,
        Long tecnicoId
) {
}
