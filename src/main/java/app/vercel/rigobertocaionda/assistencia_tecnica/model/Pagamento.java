package app.vercel.rigobertocaionda.assistencia_tecnica.model;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.MetodoPagamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.enums.PagamentoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

public class Pagamento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime dataPagamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PagamentoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orcamento_id", nullable = false)
    private Orcamento orcamento;
}
