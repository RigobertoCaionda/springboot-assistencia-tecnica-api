package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EstadoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrdemServicoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.OrdemServicoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.enums.Status;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.BusinessException;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.OrdemServico;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Tecnico;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class OrdemServicoService {
    private final OrdemServicoRepository repository;
    private final EquipamentoService equipamentoService;
    private final TecnicoService tecnicoService;
    private final OrdemServicoMapper mapper;

    @Transactional
    public OrdemServico execute(OrdemServicoDTO dto) {
        OrdemServico ordemServico = mapper.toEntity(dto);
        ordemServico.setStatus(Status.ABERTA);
        Equipamento equipamento = equipamentoService.findOne(dto.equipamentoId());
        ordemServico.setEquipamento(equipamento);

        return repository.save(ordemServico);
    }

    public Page<OrdemServico> query(
            Pageable pageable,
            boolean withPagination
    ) {
        Pageable pageableRequest = withPagination ? pageable : Pageable.unpaged(pageable.getSort());

        return repository.findAll(pageableRequest);
    }

    public OrdemServico findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Ordem não encontrada."));
    }

    @Transactional
    public String atribuirTecnico(Long tecnicoId, Long ordemId) {
        Tecnico tecnico = tecnicoService.findOne(tecnicoId);

        if (!tecnico.getAtivo()) {
            throw new BusinessException("Não pode atribuir um técnico inativo à uma ordem.");
        }

        OrdemServico ordemServico = findOne(ordemId);

        if (ordemServico.getStatus().equals(Status.CONCLUIDA) || ordemServico.getStatus().equals(Status.CANCELADA)) {
            throw new BusinessException("Não pode atribuir um técnico à uma ordem concluída ou cancelada.");
        }

        ordemServico.setTecnico(tecnico);

        repository.save(ordemServico);

        return "Técnico atribuído com sucesso.";
    }

    @Transactional
    public String atualizarEstado(Long ordemId, EstadoDTO estadoDTO) {
        OrdemServico ordemServico = this.findOne(ordemId);

        if (ordemServico.getStatus().equals(Status.CONCLUIDA)  || ordemServico.getStatus().equals(Status.CANCELADA)) {
            throw new BusinessException("Não pode mudar o estado de uma ordem cancelada ou concluída.");
        }

        ordemServico.setStatus(estadoDTO.status());
        repository.save(ordemServico);

        return "Estado atualizado com sucesso.";
    }

    public Page<OrdemServico> tecnicoOrdensServico(Long tecnicoId, Pageable pageable) {
        return repository.findByTecnicoId(tecnicoId, pageable);
    }

    public Page<OrdemServico> clienteOrdensServico(Long clienteId, Pageable pageable) {
        return repository.findByEquipamentoClienteId(clienteId, pageable);
    }
}
