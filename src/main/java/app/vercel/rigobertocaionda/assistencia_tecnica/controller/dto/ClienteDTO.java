package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteDTO(
        Long id,
        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 2, max = 150, message = "O tamanho do nome deve estar entre 2 a 150 caracteres.")
        String nome,
        @Email(message = "Email inválido.")
        @NotBlank(message = "Email é obrigatório.")
        @Size(max = 150, message = "O email deve ter no máximo 150 caracteres.")
        String email,
        @Size(min = 9, max = 15, message = "Telefone deve ter no mínimo 9 caracteres e 15 no máximo.")
        String telefone
) {
}
