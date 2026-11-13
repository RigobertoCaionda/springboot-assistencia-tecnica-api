package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {
    Equipamento toEntity(EquipamentoDTO dto);
    EquipamentoResponseDTO toDTO(Equipamento equipamento);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true) // Precisamos ignorar aqui o cliente, caso não ignorarmos, o clienteId que passarmos no EquipamentoDTO será salvo diretamente sem passar pelo service para confirmar se é um cliente válido.
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    void updateEntity(EquipamentoDTO dto, @MappingTarget Equipamento equipamento);
}
