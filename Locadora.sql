CREATE DATABASE LOCADORA;

USE LOCADORA;

CREATE TABLE CLIENTE(
CODIGO_CLIENTE INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
NOME VARCHAR(50) NOT NULL,
TELEFONE VARCHAR(20) NOT NULL
);

CREATE TABLE FILME(
CODIGO_FILME INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
TITULO VARCHAR(50) NOT NULL,
TIPO INT NOT NULL,
QNT INT NOT NULL
);

CREATE TABLE ALUGA(
CODIGO_CLIENTE INT,
CODIGO_FILME INT,
DT_EMPRESTIMO DATE NOT NULL,
DT_DEVOLUCAO DATE NOT NULL,

CONSTRAINT FK_ALUGA_CLIENTE FOREIGN KEY (CODIGO_CLIENTE) REFERENCES CLIENTE(CODIGO_CLIENTE),
CONSTRAINT FK_ALUGA_FILME FOREIGN KEY (CODIGO_FILME) REFERENCES FILME(CODIGO_FILME)

);