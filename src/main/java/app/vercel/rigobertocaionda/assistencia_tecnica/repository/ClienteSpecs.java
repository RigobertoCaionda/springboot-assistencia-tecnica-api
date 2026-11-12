package app.vercel.rigobertocaionda.assistencia_tecnica.repository;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecs {
    public static Specification<Cliente> isEmailEqual(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<Cliente> nomeLike(String name) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + name.toUpperCase() + "%");
    }
}
