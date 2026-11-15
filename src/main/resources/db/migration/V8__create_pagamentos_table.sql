CREATE TABLE pagamentos (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 valor DECIMAL(12, 2) NOT NULL,
                                 metodo_pagamento ENUM(
                                        'DINHEIRO',
                                        'TRANSFERENCIA',
                                        'REFERENCIA',
                                        'MULTICAIXA'
                                    ) NOT NULL,
                                 data_pagamento DATETIME NOT NULL,
                                 status ENUM(
                                        'PENDENTE',
                                        'APROVADO',
                                        'REJEITADO'
                                    ) NOT NULL,
                                 orcamento_id BIGINT NOT NULL,

                                 CONSTRAINT fk_pagamentos_orcamentos
                                     FOREIGN KEY (orcamento_id)
                                         REFERENCES orcamentos (id)
);