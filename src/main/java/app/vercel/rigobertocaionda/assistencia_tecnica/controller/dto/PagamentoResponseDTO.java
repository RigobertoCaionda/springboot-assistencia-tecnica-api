package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.MetodoPagamento;

import java.math.BigDecimal;

public record PagamentoResponseDTO(
        Long id,
        BigDecimal valor,
        MetodoPagamento metodoPagamento,
        OrcamentoResponseDTO orcamento
) {
}
