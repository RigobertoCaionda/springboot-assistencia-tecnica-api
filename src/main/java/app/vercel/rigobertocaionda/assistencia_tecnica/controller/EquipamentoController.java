package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoFiltroDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.EquipamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.EquipamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipamentos")

public class EquipamentoController implements GenericController {
    private final EquipamentoService service;
    private final EquipamentoMapper mapper;

    @PostMapping
    public ResponseEntity<EquipamentoResponseDTO> store(@RequestBody @Valid EquipamentoDTO dto) {

        Equipamento equipamento = service.execute(dto);

        URI location = gerarHeaderLocation(equipamento.getId());

        return ResponseEntity.created(location).body(mapper.toDTO(equipamento));
    }

    @GetMapping
    public ResponseEntity<Page<EquipamentoResponseDTO>> index(
            @ModelAttribute EquipamentoFiltroDTO filtros,
            @PageableDefault(
                page = 0,
                size = 5,
                sort = "id",
                direction = Sort.Direction.ASC
        ) Pageable pageable,
            @RequestParam(value = "withPagination", defaultValue = "true") boolean withPagination
            ) {

        Page<Equipamento> equipamentosResultado = service.query(filtros, pageable, withPagination);
        Page<EquipamentoResponseDTO> equipamentos = equipamentosResultado.map(mapper::toDTO);
        return ResponseEntity.ok(equipamentos);

    }

    @GetMapping("{id}")
    public ResponseEntity<EquipamentoResponseDTO> show(@PathVariable("id") Long id) {
        Equipamento equipamento = service.findOne(id);
        return ResponseEntity.ok(mapper.toDTO(equipamento));
    }

    @PutMapping("{id}")
    public ResponseEntity<EquipamentoResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid EquipamentoDTO dto) {
        Equipamento equipamento = service.update(id, dto);
        return ResponseEntity.ok(mapper.toDTO(equipamento));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
