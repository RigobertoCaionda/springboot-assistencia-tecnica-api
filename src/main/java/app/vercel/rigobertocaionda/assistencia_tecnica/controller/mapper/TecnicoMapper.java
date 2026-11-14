package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.TecnicoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.TecnicoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Tecnico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TecnicoMapper {
    @Mapping(target = "ativo", defaultValue = "true")
    Tecnico toEntity(TecnicoDTO dto);
    TecnicoResponseDTO toDTO(Tecnico tecnico);

    @Mapping(target = "id", ignore = true)
    void updateEntity(TecnicoDTO dto, @MappingTarget Tecnico tecnico);
}
