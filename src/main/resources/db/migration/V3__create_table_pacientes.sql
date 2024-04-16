CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    cpf VARCHAR(14) NOT NULL,
    logradouro VARCHAR(255),
    bairro VARCHAR(100),
    cep VARCHAR(9),
    complemento VARCHAR(255),
    numero VARCHAR(20),
    uf CHAR(2),
    cidade VARCHAR(100),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);
