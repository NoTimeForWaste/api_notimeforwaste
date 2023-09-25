-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema notimeforwaste
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema notimeforwaste
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS notimeforwaste DEFAULT CHARACTER SET utf8 ;
USE notimeforwaste;

-- -----------------------------------------------------
-- Table Cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Cliente (
  idCliente INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nmCliente VARCHAR(100) NOT NULL,
  email VARCHAR(70) NOT NULL,
  senha VARCHAR(15) NOT NULL,
  PRIMARY KEY (idCliente),
  UNIQUE INDEX idCliente_UNIQUE (idCliente ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Foto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Foto (
  idFotos INT NOT NULL AUTO_INCREMENT,
  foto BLOB NOT NULL,
  PRIMARY KEY (idFotos),
  UNIQUE INDEX idFotos_UNIQUE (idFotos ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Endereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Endereco (
  idEndereco INT UNSIGNED NOT NULL AUTO_INCREMENT,
  rua VARCHAR(100) NOT NULL,
  bairro VARCHAR(100) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(100) NOT NULL,
  cep VARCHAR(8) NOT NULL,
  numero INT NOT NULL,
  complemento VARCHAR(100) NOT NULL,
  apelido VARCHAR(45),
  PRIMARY KEY (idEndereco),
  UNIQUE INDEX idEndereco_UNIQUE (idEndereco ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Empresa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Empresa (
  idEmpresa INT ZEROFILL NOT NULL AUTO_INCREMENT,
  nmEmpresa VARCHAR(100) NOT NULL,
  CNPJ VARCHAR(14) NOT NULL,
  senha VARCHAR(15) NOT NULL,
  idFoto INT NOT NULL,
  idEndereco INT UNSIGNED NOT NULL,
  PRIMARY KEY (idEmpresa),
  UNIQUE INDEX idEmpresa_UNIQUE (idEmpresa ASC) ,
  UNIQUE INDEX CNP_UNIQUE (CNPJ ASC),
  INDEX fk_Empresa_Foto1_idx (idFoto ASC),
  INDEX fk_Empresa_Endereco1_idx (idEndereco ASC) ,
  CONSTRAINT fk_Empresa_Foto1
    FOREIGN KEY (idFoto)
    REFERENCES Foto (idFotos)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Empresa_Endereco1
    FOREIGN KEY (idEndereco)
    REFERENCES Endereco (idEndereco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table FormaPagamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS FormaPagamento (
  idFormaPagamento INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(60) NULL,
  PRIMARY KEY (idFormaPagamento))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table FormaEntrega
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS FormaEntrega (
  idFormaEntrega INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  PRIMARY KEY (idFormaEntrega))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Pedido
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Pedido (
  idPedido INT UNSIGNED NOT NULL AUTO_INCREMENT,
  idCliente INT UNSIGNED NOT NULL,
  status VARCHAR(15) NOT NULL,
  idFormaPagamento INT UNSIGNED NOT NULL,
  idFormaEntrega INT UNSIGNED NOT NULL,
  frete DOUBLE NOT NULL,
  cancelado BIT NULL,
  PRIMARY KEY (idPedido),
  UNIQUE INDEX idPedido_UNIQUE (idPedido ASC),
  INDEX fk_Pedido_Cliente1_idx (idCliente ASC),
  INDEX fk_Pedido_FormasPagamento1_idx (idFormaPagamento ASC),
  INDEX fk_Pedido_FormasEntrega1_idx (idFormaEntrega ASC),
  CONSTRAINT fk_Pedido_Cliente1
    FOREIGN KEY (idCliente)
    REFERENCES Cliente (idCliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pedido_FormasPagamento1
    FOREIGN KEY (idFormaPagamento)
    REFERENCES FormaPagamento (idFormaPagamento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pedido_FormasEntrega1
    FOREIGN KEY (idFormaEntrega)
    REFERENCES FormaEntrega (idFormaEntrega)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Pacote
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Pacote (
  idPacote INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  preco FLOAT NOT NULL,
  idEmpresa INT ZEROFILL NOT NULL,
  idFotos INT NOT NULL,
  idPedido INT UNSIGNED NOT NULL,
  PRIMARY KEY (idPacote),
  UNIQUE INDEX idPacote_UNIQUE (idPacote ASC),
  INDEX fk_Pacote_Empresa1_idx (idEmpresa ASC),
  INDEX fk_Pacote_Foto1_idx (idFotos ASC),
  INDEX fk_Pacote_Pedido1_idx (idPedido ASC),
  CONSTRAINT fk_Pacote_Empresa1
    FOREIGN KEY (idEmpresa)
    REFERENCES Empresa (idEmpresa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pacote_Foto1
    FOREIGN KEY (idFotos)
    REFERENCES Foto (idFotos)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pacote_Pedido1
    FOREIGN KEY (idPedido)
    REFERENCES Pedido (idPedido)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Produto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Produto (
  idProduto INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  descricao VARCHAR(300) NOT NULL,
  idPacote INT NOT NULL,
  dtValidade DATE NOT NULL,
  PRIMARY KEY (idProduto),
  UNIQUE INDEX idProduto_UNIQUE (idProduto ASC),
  INDEX fk_Produto_Pacote_idx (idPacote ASC),
  CONSTRAINT fk_Produto_Pacote
    FOREIGN KEY (idPacote)
    REFERENCES Pacote (idPacote)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Cliente_Endereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Cliente_Endereco (
  iidCliente INT UNSIGNED NOT NULL,
  idEndereco INT UNSIGNED NOT NULL,
  PRIMARY KEY (iidCliente, idEndereco),
  INDEX fk_Cliente_has_Endereco_Endereco1_idx (idEndereco ASC),
  INDEX fk_Cliente_has_Endereco_Cliente1_idx (iidCliente ASC),
  CONSTRAINT fk_Cliente_has_Endereco_Cliente1
    FOREIGN KEY (iidCliente)
    REFERENCES Cliente (idCliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Cliente_has_Endereco_Endereco1
    FOREIGN KEY (idEndereco)
    REFERENCES Endereco (idEndereco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table HorarioFuncionamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HorarioFuncionamento (
  idHorario INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  horarioInicial TIME NOT NULL,
  horarioFinal TIME NOT NULL,
  idEmpresa INT ZEROFILL NOT NULL,
  PRIMARY KEY (idHorario),
  INDEX fk_HorarioFuncionamento_Empresa1_idx (idEmpresa ASC),
  CONSTRAINT fk_HorarioFuncionamento_Empresa1
    FOREIGN KEY (idEmpresa)
    REFERENCES Empresa (idEmpresa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Avaliacao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Avaliacao (
  idAvaliacao INT NOT NULL AUTO_INCREMENT,
  comentario VARCHAR(500) NULL,
  nota INT NOT NULL,
  idEmpresa INT ZEROFILL NOT NULL,
  idCliente INT UNSIGNED NOT NULL,
  dtAvaliacao DATETIME NOT NULL,
  PRIMARY KEY (idAvaliacao),
  UNIQUE INDEX idAvaliacao_UNIQUE (idAvaliacao ASC),
  INDEX fk_Avaliacao_Empresa1_idx (idEmpresa ASC),
  INDEX fk_Avaliacao_Cliente1_idx (idCliente ASC),
  CONSTRAINT fk_Avaliacao_Empresa1
    FOREIGN KEY (idEmpresa)
    REFERENCES Empresa (idEmpresa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Avaliacao_Cliente1
    FOREIGN KEY (idCliente)
    REFERENCES Cliente (idCliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Pacote_FormaPagamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Pacote_FormaPagamento (
  iidPacote INT NOT NULL,
  idFormaPagamento INT UNSIGNED NOT NULL,
  PRIMARY KEY (iidPacote, idFormaPagamento),
  INDEX fk_Pacote_FormaPagamento_FormaPagamento1_idx (idFormaPagamento ASC),
  INDEX fk_Pacote_FormaPagamento_Pacote1_idx (iidPacote ASC),
  CONSTRAINT fk_Pacote_FormaPagamento_Pacote1
    FOREIGN KEY (iidPacote)
    REFERENCES Pacote (idPacote)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pacote_FormaPagamento_FormaPagamento1
    FOREIGN KEY (idFormaPagamento)
    REFERENCES FormaPagamento (idFormaPagamento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Pacote_FormaEntrega
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Pacote_FormaEntrega (
  Pacote_idPacote INT NOT NULL,
  FormaEntrega_idFormaEntrega INT UNSIGNED NOT NULL,
  PRIMARY KEY (Pacote_idPacote, FormaEntrega_idFormaEntrega),
  INDEX fk_Pacote_FormaEntrega_FormaEntrega1_idx (FormaEntrega_idFormaEntrega ASC),
  INDEX fk_Pacote_FormaEntrega_Pacote1_idx (Pacote_idPacote ASC),
  CONSTRAINT fk_Pacote_FormaEntrega_Pacote1
    FOREIGN KEY (Pacote_idPacote)
    REFERENCES Pacote (idPacote)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pacote_FormaEntrega_FormaEntrega1
    FOREIGN KEY (FormaEntrega_idFormaEntrega)
    REFERENCES FormaEntrega (idFormaEntrega)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
