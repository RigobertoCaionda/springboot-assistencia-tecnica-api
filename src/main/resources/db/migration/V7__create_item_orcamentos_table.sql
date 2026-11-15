CREATE TABLE item_orcamentos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       quantidade INT NOT NULL,
                       preco_unitario DECIMAL(12, 2) NOT NULL,
                       orcamento_id BIGINT NOT NULL,
                       peca_id BIGINT NOT NULL,

                       CONSTRAINT fk_item_orcamentos_orcamentos
                           FOREIGN KEY (orcamento_id)
                               REFERENCES orcamentos (id),
                       CONSTRAINT fk_item_orcamentos_pecas
                           FOREIGN KEY (peca_id)
                               REFERENCES pecas (id)
);