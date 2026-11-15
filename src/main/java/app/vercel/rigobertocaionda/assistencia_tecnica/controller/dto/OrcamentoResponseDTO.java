package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.OrcamentoPrioridade;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrcamentoResponseDTO(
        BigDecimal valorMaoObras,
        BigDecimal valorPecas,
        BigDecimal valorTotal,
        OrcamentoPrioridade prioridade,
        LocalDate dataEmissao,
        OrdemServicoResponseDTO ordemServico
) {
}
