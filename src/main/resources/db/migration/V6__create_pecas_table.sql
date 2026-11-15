CREATE TABLE pecas (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(150) NOT NULL,
                          codigo VARCHAR(20) NOT NULL UNIQUE,
                          preco DECIMAL(12, 2) NOT NULL,
                          quantidade_stock INT NOT NULL,
                          ativo BOOLEAN NOT NULL DEFAULT TRUE
);