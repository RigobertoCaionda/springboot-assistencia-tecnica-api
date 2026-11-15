package app.vercel.rigobertocaionda.assistencia_tecnica.repository;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    boolean existsByOrdemServicoIdAndIdNot(Long ordemServicoId, Long id);
    boolean existsByOrdemServicoId(Long ordemServicoId);
}
