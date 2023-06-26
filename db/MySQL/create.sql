drop database if exists VagasEmprego;

create database VagasEmprego;

USE VagasEmprego;

CREATE TABLE Usuario(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(256) NOT NULL,
  email VARCHAR(128) NOT NULL,
  senha VARCHAR(128) NOT NULL,
  papel VARCHAR(18) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Empresa(
  id BIGINT NOT NULL,
  cnpj VARCHAR(18),
  cidade VARCHAR(256),
  FOREIGN KEY (id) REFERENCES Usuario(id),
  PRIMARY KEY (id)
);

CREATE TABLE Profissional(
  id BIGINT NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  data_nascimento DATE,
  FOREIGN KEY (id) REFERENCES Usuario(id),
  PRIMARY KEY (id)
);

CREATE TABLE Vaga(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(256) NOT NULL,
  descricao TEXT,
  cnpj VARCHAR(18),
  dataLimite DATE,
  FOREIGN KEY (cnpj) REFERENCES Empresa(cnpj),
  PRIMARY KEY (id)
);

CREATE TABLE Inscricao(
  id BIGINT NOT NULL AUTO_INCREMENT,
  data DATE,
  cpf VARCHAR(14),
  curriculo TEXT,
  vaga_id BIGINT,
  FOREIGN KEY (cpf) REFERENCES Profissional(cpf),
  FOREIGN KEY (vaga_id) REFERENCES Vaga(id),
  PRIMARY KEY (id)
);