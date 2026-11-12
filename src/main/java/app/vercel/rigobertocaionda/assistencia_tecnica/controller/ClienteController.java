package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.ClienteMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "direction", defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email

    ) {

            Page<Cliente> clientesResultado = service.query(page, size, sortBy, direction, name, email);
            Page<ClienteResponseDTO> clientes = clientesResultado.map(mapper::toDTO);
            return ResponseEntity.ok(clientes);
    }
}
