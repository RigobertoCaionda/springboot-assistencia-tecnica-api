package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.Prioridade;
import app.vercel.rigobertocaionda.assistencia_tecnica.enums.Status;

import java.time.LocalDateTime;

public record OrdemServicoResponseDTO(
        Long id,
        String descricao,
        LocalDateTime dataAbertura,
        LocalDateTime dataConclusao,
        Status status,
        Prioridade prioridade,
        EquipamentoResponseResumoDTO equipamento,
        TecnicoResponseDTO tecnico
) {
}
