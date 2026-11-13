package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.TipoEquipamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EquipamentoDTO(
        Long id,
        @NotBlank(message = "marca é obrgatória.")
        @Size(max = 150, message = "marca deve ter no máximo  150 caracteres.")
        String marca,
        @NotBlank(message = "modelo é obrgatório.")
        @Size(max = 150, message = "modelo deve ter no máximo  150 caracteres.")
        String modelo,
        @NotBlank(message = "número de série é obrgatório.")
        @Size(max = 20, message = "número de série deve ter no máximo  20 caracteres.")
        String numeroSerie,
        @NotNull(message = "tipo de equipamento não pode ser nulo.") // Nas Enums usa-se Not Null
        TipoEquipamento tipoEquipamento,
        @NotNull(message = "clienteId é obrigatório.")
        Long clienteId
) {
}
