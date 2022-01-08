CREATE TABLE projeto(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	inicio_projeto DATE,
	fim_projeto DATE,
	codigo_setor BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_setor) REFERENCES setor (codigo),
	ativo BOOLEAN
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO projeto (nome, descricao, inicio_projeto, fim_projeto, codigo_setor, ativo) values 
('PROJETO B2B', 'PROJETO EMPRESAS', '2022-01-07', '2022-01-30', 1, true);

INSERT INTO projeto (nome, descricao, inicio_projeto, fim_projeto, codigo_setor, ativo) values 
('PROJETO B2C', 'PROJETO CLIENTES FISICOS', '2022-02-10', '2022-03-21', 2, true);

INSERT INTO projeto (nome, descricao, inicio_projeto, fim_projeto, codigo_setor, ativo) values 
('PROJETO DESENVOLVIMENTO', 'DESENVOLVIMENTO RECURSO X', '2022-12-01', '2022-12-03', 2, true);