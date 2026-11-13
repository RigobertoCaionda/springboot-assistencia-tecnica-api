package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.TipoEquipamento;

import java.time.LocalDateTime;

public record EquipamentoResponseDTO(
        Long id,
        String marca,
        String modelo,
        TipoEquipamento tipoEquipamento,
        String numeroSerie,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao,
        ClienteResponseResumoDTO cliente // Para evitar stackoverflow por chamadas recursivas: cliente chama equipamento e equipamento volta a chamar cliente
) {
}
