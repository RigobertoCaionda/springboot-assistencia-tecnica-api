package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteFiltroDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.ClienteMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.ClienteService;
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
@RequestMapping("/clientes")

public class ClienteController implements GenericController {
    private final ClienteService service;
    private final ClienteMapper mapper;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> store(@RequestBody @Valid ClienteDTO dto) {
        Cliente cliente = mapper.toEntity(dto);

        Cliente clienteResultado = service.execute(cliente);
        ClienteResponseDTO clienteResponseDTO = mapper.toDTO(clienteResultado);

        URI location = gerarHeaderLocation(cliente.getId());
        return ResponseEntity.created(location).body(clienteResponseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ClienteDTO dto) {
        Cliente clienteFromDTO = mapper.toEntity(dto);
        clienteFromDTO.setId(id);
        Cliente cliente = service.update(clienteFromDTO);
        return ResponseEntity.ok(mapper.toDTO(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> index(
            @ModelAttribute ClienteFiltroDTO filtros, // O @ModelAttribute agrupa todos os campos num só lugar, para não precisar de criar @RequestParam individuais
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) Pageable pageable,
            @RequestParam(value = "withPagination", defaultValue = "true") boolean withPagination
    ) {

            Page<Cliente> clientesResultado = service.query(filtros, pageable, withPagination);
            Page<ClienteResponseDTO> clientes = clientesResultado.map(mapper::toDTO);
            return ResponseEntity.ok(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> show(@PathVariable("id") Long id) {
         Cliente clienteResult = service.findOne(id);
         ClienteResponseDTO clienteResponseDTO = mapper.toDTO(clienteResult);

         return ResponseEntity.ok(clienteResponseDTO);
    }
}
