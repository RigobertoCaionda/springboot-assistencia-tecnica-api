package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TecnicoDTO(
        Long id,
        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 2, max = 150, message = "O tamanho do nome deve estar entre 2 a 150 caracteres.")
        String nome,
        @Email(message = "Email inválido.")
        @NotBlank(message = "Email é obrigatório.")
        @Size(max = 150, message = "O email deve ter no máximo 150 caracteres.")
        String email,
        @NotBlank(message = "Especialidade é obrigatório.")
        @Size(max = 100, message = "Especialidade deve ter no máximo 100 caracteres.")
        String especialidade,
        Boolean ativo
) {
}