package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        List<EquipamentoResponseResumoDTO> equipamentos
) {
}
