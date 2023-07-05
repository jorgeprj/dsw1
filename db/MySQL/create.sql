DROP DATABASE IF EXISTS VagasEmprego;

CREATE DATABASE VagasEmprego;

USE VagasEmprego;

CREATE TABLE Usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(256) NOT NULL,
    email VARCHAR(128) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    papel VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE Empresa (
    id BIGINT NOT NULL PRIMARY KEY,
    cnpj VARCHAR(32) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

CREATE TABLE Profissional (
    id BIGINT NOT NULL PRIMARY KEY,
    cpf VARCHAR(20) NOT NULL,
    telefone VARCHAR(20),
    sexo VARCHAR(10),
    data_nascimento DATE,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

CREATE TABLE Entrevista (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cpf_profissional VARCHAR(11) NOT NULL,
    cnpj_empresa VARCHAR(15) NOT NULL,
    data_hora DATETIME NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO Usuario(nome, email, senha, papel) VALUES
    ('Jorge Pires', 'jorge@email.com', 'jorge', 'PROFISSIONAL');

INSERT INTO Profissional(id, cpf, telefone, sexo, data_nascimento) VALUES
    (LAST_INSERT_ID(), '99988877702', '912345678', 'M', '2002-02-11');

INSERT INTO Usuario(nome, email, senha, papel) VALUES
    ('UFscar', 'ufscar@email.com', 'ufscar', 'EMPRESA');

INSERT INTO Empresa(id, cnpj, cidade) VALUES
    (LAST_INSERT_ID(), '51168577000148', 'São Carlos');

INSERT INTO Usuario(nome, email, senha, papel) VALUES
    ('Lucas', 'admin@email.com', 'admin', 'ADMIN')