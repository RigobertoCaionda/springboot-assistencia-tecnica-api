package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoFiltroDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.EquipamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.EquipamentoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.EquipamentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static app.vercel.rigobertocaionda.assistencia_tecnica.repository.EquipamentoSpecs.*;

@Service
@RequiredArgsConstructor

public class EquipamentoService {
    private final EquipamentoRepository repository;
    private final EquipamentoValidator validator;
    private final ClienteService clienteService;
    private final EquipamentoMapper mapper;

    public Equipamento execute(EquipamentoDTO dto) {
        Equipamento equipamento = mapper.toEntity(dto);

        if (equipamento.getId() != null) {
            throw new IllegalArgumentException("Não pode enviar id ao criar um novo registo.");
        }

        if (dto.clienteId() == null) {
            throw  new IllegalArgumentException("clienteId não pode ser nulo.");
        }

        Cliente cliente = clienteService.findOne(dto.clienteId());
        equipamento.setCliente(cliente);
        validator.validar(equipamento);

        return repository.save(equipamento);
    }

    public Equipamento findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Equipamento não encontrado"));
    }

    public Page<Equipamento> query(EquipamentoFiltroDTO filtros, Pageable pageable, boolean withPagination) {
        Specification<Equipamento> specs = buildSpecification(filtros);
        Pageable pageableRequest = withPagination ? pageable : Pageable.unpaged(pageable.getSort());

        return repository.findAll(specs, pageableRequest);
    }

    private Specification<Equipamento> buildSpecification(EquipamentoFiltroDTO filtros) {
        Specification<Equipamento> specs = Specification.where((root, query, cb) -> cb.conjunction());
        if (filtros.marca() != null) {
            specs = specs.and(isMarcaEqual(filtros.marca()));
        }

        if (filtros.modelo() != null) {
            specs = specs.and(modeloLike(filtros.modelo()));
        }

        if (filtros.tipoEquipamento() != null) {
            specs = specs.and(isTipoEquipamentoEqual(filtros.tipoEquipamento()));
        }

        if (filtros.nomeCliente() != null && !filtros.nomeCliente().isBlank()) {
            specs = specs.and(nomeClienteLike(filtros.nomeCliente()));
        }

        return specs;
    }

    public Equipamento update(Long id, EquipamentoDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("Para atualizar um registo precisa enviar ID.");
        }

        Equipamento equipamentoEncontrado = findOne(id);

        mapper.updateEntity(dto, equipamentoEncontrado); // Isso evita você ter que preencher cada dado a ser atualizado individualmente

        Cliente cliente = clienteService.findOne(dto.clienteId());
        equipamentoEncontrado.setCliente(cliente);

        validator.validar(equipamentoEncontrado);

        return repository.save(equipamentoEncontrado);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
