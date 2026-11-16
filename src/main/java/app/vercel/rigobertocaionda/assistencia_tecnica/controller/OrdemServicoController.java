package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.*;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.OrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.OrdemServicoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.PagamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.OrdemServico;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.OrcamentoService;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.OrdemServicoService;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ordem-servicos")

public class OrdemServicoController implements GenericController {
    private final OrdemServicoService service;
    private final OrdemServicoMapper mapper;
    private final OrcamentoService orcamentoService;
    private final OrcamentoMapper orcamentoMapper;
    private final PagamentoService pagamentoService;
    private final PagamentoMapper pagamentoMapper;

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDTO> store(@RequestBody @Valid OrdemServicoDTO dto) {
        OrdemServico ordemServico = service.execute(dto);
        URI location = gerarHeaderLocation(ordemServico.getId());
        return ResponseEntity.created(location).body(mapper.toDTO(ordemServico));
    }


    @GetMapping
    public ResponseEntity<Page<OrdemServicoResponseDTO>> index(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) Pageable pageable,
            @RequestParam(value = "withPagination", defaultValue = "true") boolean withPagination
    ) {

        Page<OrdemServico> ordemServicosResultado = service.query(pageable, withPagination);
        Page<OrdemServicoResponseDTO> ordens = ordemServicosResultado.map(mapper::toDTO);
        return ResponseEntity.ok(ordens);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdemServicoResponseDTO> show(@PathVariable("id") Long id) {
        OrdemServico ordemServico = service.findOne(id);
        return ResponseEntity.ok(mapper.toDTO(ordemServico));
    }

    @PatchMapping("{id}/atribuir-tecnico")
    public ResponseEntity<String> atribuirTecnico(@PathVariable("id") Long ordemId, @RequestBody Long tecnicoId) {
        String message = service.atribuirTecnico(tecnicoId, ordemId);
        return ResponseEntity.ok(message);
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<String> atualizarEstado(@PathVariable("id") Long ordemId, @RequestBody EstadoDTO estadoDTO) {
        String message = this.service.atualizarEstado(ordemId, estadoDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/tecnico/{id}")
    public ResponseEntity<Page<OrdemServicoResponseDTO>> ordemServicoTecnico(@PathVariable("id") Long tecnicoId,
                                                                             @PageableDefault(size = 10, sort = "dataAbertura",
                                                                                     direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrdemServico> ordemServicosResultado = service.tecnicoOrdensServico(tecnicoId, pageable);
        Page<OrdemServicoResponseDTO> ordemServicosDTO = ordemServicosResultado.map(mapper::toDTO);
        return ResponseEntity.ok(ordemServicosDTO);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Page<OrdemServicoResponseDTO>> ordemServicoCliente(@PathVariable("id") Long clienteId,
                                                                             @PageableDefault(size = 10, sort = "dataAbertura",
                                                                                     direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrdemServico> ordemServicosResultado = service.clienteOrdensServico(clienteId, pageable);
        Page<OrdemServicoResponseDTO> ordemServicosDTO = ordemServicosResultado.map(mapper::toDTO);
        return ResponseEntity.ok(ordemServicosDTO);
    }

    @PostMapping("{id}/orcamento")
    public ResponseEntity<OrcamentoResponseDTO> criarOrcamento(@PathVariable("id") Long ordemServicoId, @RequestBody @Valid OrcamentoDTO dto) {
        Orcamento orcamento = orcamentoService.execute(dto, ordemServicoId);
        URI location = gerarHeaderLocation(orcamento.getId());
        return ResponseEntity.created(location).body(orcamentoMapper.toDTO(orcamento));
    }

    @PostMapping("{id}/pagamentos")
    public ResponseEntity<Void> efetuarPagamento(@PathVariable("id") Long orcamentoId, @RequestBody @Valid PagamentoDTO dto) {
        pagamentoService.execute(orcamentoId, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/pagamentos")
    public ResponseEntity<List<PagamentoResponseDTO>> getOrdemServicoPagamentos(@PathVariable("id") Long id) {
        List<Pagamento> pagamentos = pagamentoService.getOrdemServicoPagamentos(id);

        List<PagamentoResponseDTO> pagamentosList = pagamentos.stream().map(pagamentoMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(pagamentosList);
    }

}
