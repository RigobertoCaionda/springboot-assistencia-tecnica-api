package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.ClienteRepository;
import static app.vercel.rigobertocaionda.assistencia_tecnica.repository.ClienteSpecs.*;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.ClienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Cliente> query(
            Integer page,
            Integer size,
            String sortBy,
            Sort.Direction direction,
            String name,
            String email
    ) {
        Specification<Cliente> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (name != null) {
            specs = specs.and(nomeLike(name));
        }

        if (email != null) {
            specs = specs.and(isEmailEqual(email));
        }

        Pageable pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy)); // Além da paginação, adicionamos a ordenação
        return repository.findAll(specs, pageRequest);
    }
}
