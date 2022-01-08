CREATE TABLE projeto_funcionario(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	carga_horaria BIGINT(20),
	gestor BOOLEAN,
	inicio_participacao DATE,
	fim_participacao DATE,
	codigo_funcionario BIGINT(20) NOT NULL,
	codigo_projeto BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_funcionario) REFERENCES funcionario (codigo),
	FOREIGN KEY (codigo_projeto) REFERENCES projeto (codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO projeto_funcionario (carga_horaria, gestor, inicio_participacao, fim_participacao, codigo_funcionario, codigo_projeto) values 
(40, true, '2021-12-10', '2021-12-22', 1, 2);

INSERT INTO projeto_funcionario (carga_horaria, gestor, inicio_participacao, fim_participacao, codigo_funcionario, codigo_projeto) values 
(60, false, '2022-01-10', '2022-01-22', 2, 1);

