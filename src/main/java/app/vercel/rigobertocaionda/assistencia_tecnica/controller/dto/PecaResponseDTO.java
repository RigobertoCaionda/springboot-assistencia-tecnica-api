package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import java.math.BigDecimal;

public record PecaResponseDTO(
        String nome,
        String codigo,
        BigDecimal preco,
        Integer quantidadeStock,
        Boolean ativo
) {
}
