package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.*;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.TecnicoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Tecnico;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.TecnicoService;
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
@RequestMapping("/tecnicos")

public class TecnicoController implements GenericController {
    private final TecnicoService service;
    private final TecnicoMapper mapper;

    @PostMapping
    public ResponseEntity<TecnicoResponseDTO> store(@RequestBody @Valid TecnicoDTO dto) {
        Tecnico tecnico = service.execute(mapper.toEntity(dto));
        URI location = gerarHeaderLocation(tecnico.getId());
        return ResponseEntity.created(location).body(mapper.toDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<Page<TecnicoResponseDTO>> index(
            @ModelAttribute TecnicoFiltroDTO filtros,
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) Pageable pageable,
            @RequestParam(value = "withPagination", defaultValue = "true") boolean withPagination
    ) {

        Page<Tecnico> tecnicosResultado = service.query(filtros, pageable, withPagination);
        Page<TecnicoResponseDTO> clientes = tecnicosResultado.map(mapper::toDTO);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<TecnicoResponseDTO> show(@PathVariable("id") Long id) {
        Tecnico tecnicoResult = service.findOne(id);
        TecnicoResponseDTO tecnicoResponseDTO = mapper.toDTO(tecnicoResult);

        return ResponseEntity.ok(tecnicoResponseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<TecnicoResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid TecnicoDTO dto) {
        Tecnico tecnico = service.update(id, dto);
        return ResponseEntity.ok(mapper.toDTO(tecnico));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
