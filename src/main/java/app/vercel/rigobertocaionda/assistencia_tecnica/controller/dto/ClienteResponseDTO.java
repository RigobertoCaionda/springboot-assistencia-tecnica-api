package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone
) {
}
