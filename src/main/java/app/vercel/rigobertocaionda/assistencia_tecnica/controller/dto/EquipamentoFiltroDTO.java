package app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.TipoEquipamento;

public record EquipamentoFiltroDTO(
        String marca,
        String modelo,
        TipoEquipamento tipoEquipamento,
        String nomeCliente
) {
}
