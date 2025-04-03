-- Inserir categorias
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Transporte');
INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Educação');

-- Inserir transações
INSERT INTO transacao (valor, tipo, categoria_id, data) VALUES (50.00, 'DEBITO', 1, CURRENT_TIMESTAMP);
INSERT INTO transacao (valor, tipo, categoria_id, data) VALUES (100.00, 'CREDITO', 2, CURRENT_TIMESTAMP);
INSERT INTO transacao (valor, tipo, categoria_id, data) VALUES (30.00, 'DEBITO', 3, CURRENT_TIMESTAMP);
INSERT INTO transacao (valor, tipo, categoria_id, data) VALUES (200.00, 'CREDITO', 4, CURRENT_TIMESTAMP);