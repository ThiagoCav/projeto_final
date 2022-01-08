CREATE TABLE funcionario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(14) NOT NULL,
    salario DECIMAL(8,2),
    codigo_grupo BIGINT(20) NOT NULL,
    codigo_setor BIGINT(20) NOT NULL,
	ativo BOOLEAN,
	FOREIGN KEY (codigo_grupo) REFERENCES grupo (codigo),
	FOREIGN KEY (codigo_setor) REFERENCES setor (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO funcionario (nome, cpf, salario, codigo_grupo, codigo_setor, ativo) values 
('Thiago Cavallari de Almeida', '397.870.508-71', '1000.00', 1, 1, true);

INSERT INTO funcionario (nome, cpf, salario, codigo_grupo, codigo_setor, ativo) values 
('Joao', '464.018.900-10', '2000.00', 1, 2, true);

INSERT INTO funcionario (nome, cpf, salario, codigo_grupo, codigo_setor, ativo) values 
('Maria', '842.229.820-16', '2000.00', 2, 2, true);




