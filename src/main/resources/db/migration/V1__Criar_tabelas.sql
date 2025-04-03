CREATE TABLE categoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE transacao (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           valor DECIMAL(10, 2) NOT NULL,
                           tipo VARCHAR(10) NOT NULL,
                           categoria_id BIGINT NOT NULL,
                           data TIMESTAMP NOT NULL,
                           CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);