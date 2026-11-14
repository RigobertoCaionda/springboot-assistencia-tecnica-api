package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

public record TecnicoResponseDTO(
        Long id,
        String nome,
        String email,
        String especialidade,
        Boolean ativo
) {
}
