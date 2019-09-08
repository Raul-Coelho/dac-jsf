CREATE TABLE dependente(
	uuid VARCHAR(50) PRIMARY KEY,
	nome VARCHAR(50),
	dataNascimento DATE
);

CREATE TABLE pessoa(
	cpf VARCHAR(11) PRIMARY KEY,
	nome VARCHAR(50),
	id_dependente VARCHAR(50),
	FOREIGN KEY (id_dependente) REFERENCES dependente (uuid)
);