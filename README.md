# uploadImgJava
 
comando sql para o sistema funcionar: 


-- Criar a base de dados
CREATE DATABASE upload;

-- Selecionar a base de dados para uso
USE upload;

-- Criar a tabela alunos
CREATE TABLE alunos (
    ra INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    foto LONGBLOB
);
