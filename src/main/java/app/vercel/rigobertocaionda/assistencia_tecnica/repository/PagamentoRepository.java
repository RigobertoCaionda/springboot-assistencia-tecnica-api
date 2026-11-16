package app.vercel.rigobertocaionda.assistencia_tecnica.repository;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
