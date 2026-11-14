package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.TecnicoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.TecnicoFiltroDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.TecnicoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Tecnico;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.TecnicoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.TecnicoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static app.vercel.rigobertocaionda.assistencia_tecnica.repository.TecnicoSpecs.*;

@Service
@RequiredArgsConstructor

public class TecnicoService {
    private final TecnicoRepository repository;
    private final TecnicoValidator validator;
    private final TecnicoMapper mapper;

    public Tecnico findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Técnico não encontrado."));
    }

    public Tecnico execute(Tecnico tecnico) {
        if (tecnico.getId() != null) {
            throw new IllegalArgumentException("Não deve enviar ID na atualização.");
        }

        validator.validar(tecnico);

        return repository.save(tecnico);
    }

    public Page<Tecnico> query(
            TecnicoFiltroDTO filtros,
            Pageable pageable,
            boolean withPagination
    ) {
        Specification<Tecnico> specs = buildSpecification(filtros);

        Pageable pageableRequest = withPagination ? pageable : Pageable.unpaged(pageable.getSort());

        return repository.findAll(specs, pageableRequest);
    }

    private Specification<Tecnico> buildSpecification(TecnicoFiltroDTO filtros) {
        Specification<Tecnico> specs =
                Specification.where((root, query, cb) -> cb.conjunction());

        if (filtros.especialidade() != null) {
            specs = specs.and(isEspecialidadeEqual(filtros.especialidade()));
        }

        if (filtros.ativo() != null) {
            specs = specs.and(isAtivoEqual(filtros.ativo()));
        }

        return specs;
    }

    public Tecnico update(Long id, TecnicoDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("Para atualizar um registo precisa enviar ID.");
        }

        Tecnico tecnicoEncontrado = findOne(id);

        mapper.updateEntity(dto, tecnicoEncontrado);

        validator.validar(tecnicoEncontrado);

        return repository.save(tecnicoEncontrado);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
