package app.vercel.rigobertocaionda.assistencia_tecnica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pecas")
@Getter
@Setter

public class Peca {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 20)
    private String codigo;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @Column(name = "quantidade_Stock", nullable = false)
    private Integer quantidadeStock;

    @Column(nullable = false)
    private Boolean ativo;
}
