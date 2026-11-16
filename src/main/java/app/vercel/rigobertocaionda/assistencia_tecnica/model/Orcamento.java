package app.vercel.rigobertocaionda.assistencia_tecnica.model;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.OrcamentoPrioridade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orcamentos")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

public class Orcamento {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_mao_obras", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorMaoObras;

    @Column(name = "valor_pecas", precision = 12, scale = 2)
    private BigDecimal valorPecas;

    @Column(name = "valor_total", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "prioridade", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrcamentoPrioridade prioridade;

    @Column(name = "data_emissao", nullable = false)
    @CreatedDate
    private LocalDate dataEmissao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordem_servico_id", nullable = false, unique = true)
    private OrdemServico ordemServico;
}
