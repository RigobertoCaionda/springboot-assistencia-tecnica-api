package app.vercel.rigobertocaionda.assistencia_tecnica.repository;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByTelefoneAndIdNot(String telefone, Long id);

    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
}