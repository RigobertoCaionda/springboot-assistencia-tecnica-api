package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDTO dto);
    ClienteResponseDTO toDTO(Cliente cliente);

    EquipamentoResponseDTO toDTO(Equipamento equipamento); // Como estamos retornando equipamento na listagem de clientes, então, precisamos disso também.
}
