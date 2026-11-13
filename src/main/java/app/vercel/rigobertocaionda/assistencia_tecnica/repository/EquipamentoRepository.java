package app.vercel.rigobertocaionda.assistencia_tecnica.repository;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>, JpaSpecificationExecutor<Equipamento> {
    boolean existsByNumeroSerie(String numeroSerie);
    boolean existsByNumeroSerieAndIdNot(String numeroSerie, Long id);
}
