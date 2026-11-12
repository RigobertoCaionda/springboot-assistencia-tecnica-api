package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDTO dto);
    ClienteResponseDTO toDTO(Cliente cliente);
}
