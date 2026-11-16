package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import java.math.BigDecimal;

public record ItemOrcamentoResponseDTO(
        Integer quantidade,
        BigDecimal precoUnitario,
        OrcamentoResponseDTO orcamento,
        PecaResponseDTO peca
) {
}
