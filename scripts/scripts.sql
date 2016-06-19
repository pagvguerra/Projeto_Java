-- Criar um banco de dados JavaP1
create database JavaP1;

-- Abrir conexao com o banco
use JavaP1;

-- Criar tabela empregado
create table empregado(
	id int auto_increment primary key,
	nome varchar(100) not null,
	cpf varchar(20) not null unique,
	email varchar(50) not null,
	login varchar(50) not null,
	senha varchar(100) not null,
	rg varchar(20) not null,
	sexo varchar(1) not null,
	perfil varchar(3) not null
);

-- A senha é do administrador pablo é: maria123
INSERT INTO empregado (id, nome, cpf, email, login, senha, rg, sexo, perfil) values(1, 'Pablo Guimarães Vieira Guerra', '056.858.627-17', 'pagvguerra@gmail.com', 'pablo1', 'WtN1qouDpT08CNl0c775hA==', '10805289-5', 'M', 'ADM');
COMMIT;

-- Criando indices para campos de empregado
CREATE INDEX EMP_LOGIN ON EMPREGADO (login);
CREATE INDEX EMP_SENHA ON EMPREGADO (senha);

-- Criar tabela produto
create table produto(
	id int auto_increment primary key,
	nome varchar(100) not null,
	fK_marca int not null,
	quantidade int not null,
	preco int not null
);

-- Criar tabela marca
create table marca(
	id int auto_increment primary key not null,
	nome varchar(100) not null unique
);

-- Mostrar a tabela empregado
desc empregado;

-- Mostrar a tabela produto
desc produto;

-- Mostrar a tabela marca
desc marca;