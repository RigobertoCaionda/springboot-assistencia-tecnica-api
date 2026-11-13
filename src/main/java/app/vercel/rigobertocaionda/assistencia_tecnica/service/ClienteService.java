package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteFiltroDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.ClienteRepository;
import static app.vercel.rigobertocaionda.assistencia_tecnica.repository.ClienteSpecs.*;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.ClienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteValidator validator;

    public Cliente execute(Cliente cliente) {
        validator.validar(cliente);
        return repository.save(cliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Cliente update(Cliente cliente) {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("Para atualizar um registo, deve existir id.");
        }

        Cliente clienteEncontrado = this.findOne(cliente.getId());
        validator.validar(cliente);
        clienteEncontrado.setNome(cliente.getNome());
        clienteEncontrado.setEmail(cliente.getEmail());
        clienteEncontrado.setTelefone(cliente.getTelefone());

        return repository.save(clienteEncontrado);
    }

    public Cliente findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
    }

    public Page<Cliente> query(
            ClienteFiltroDTO filtros,
            Pageable pageable,
            boolean withPagination
    ) {
        Specification<Cliente> specs = buildSpecification(filtros);

        Pageable pageableRequest = withPagination ? pageable : Pageable.unpaged(pageable.getSort());

        return repository.findAll(specs, pageableRequest);
    }

    private Specification<Cliente> buildSpecification(ClienteFiltroDTO filtros) {
        Specification<Cliente> specs =
                Specification.where((root, query, cb) -> cb.conjunction());

        if (filtros.name() != null) {
            specs = specs.and(nomeLike(filtros.name()));
        }

        if (filtros.email() != null) {
            specs = specs.and(isEmailEqual(filtros.email()));
        }

        return specs;
    }
}
