CREATE DATABASE testetecnico;

CREATE SCHEMA cadastro_pessoa;

CREATE TABLE cadastro_pessoa.pessoa
(
  id bigserial NOT NULL PRIMARY KEY,
  nome character varying(200),
  cpf character varying(13),
  telefone1 character varying(20),
  telefone2 character varying(20)

);

Create Table cadastro_pessoa.endereco(
  id bigserial NOT NULL PRIMARY KEY,
  cep bigint varying(8),
  rua character varying(200),
  numero bigint varying(10),
  bairro character varying(200),
  cidade character varying(200),
  estado character varying(200),
  id_pessoa bigserial references testetecnicoelotech.pessoa (id)
);