
CREATE TABLE orcamentos (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,

                            valor_mao_obras DECIMAL(12, 2) NOT NULL,
                            valor_pecas DECIMAL(12, 2) NOT NULL,
                            valor_total DECIMAL(12, 2) NOT NULL,

                            prioridade ENUM(
        'PENDENTE',
        'APROVADO',
        'REJEITADO'
    ) NOT NULL,

                            data_emissao DATE NOT NULL,

                            ordem_servico_id BIGINT NOT NULL,

                            CONSTRAINT fk_orcamentos_ordem_servicos
                                FOREIGN KEY (ordem_servico_id)
                                    REFERENCES ordem_servicos (id)
);
