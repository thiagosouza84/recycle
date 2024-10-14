CREATE TABLE endereco (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(20) NOT NULL

);

CREATE TABLE agendamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    endereco_id BIGINT,
    data_inicio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_finalizado TIMESTAMP NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);