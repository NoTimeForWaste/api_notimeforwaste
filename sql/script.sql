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
CREATE SCHEMA IF NOT EXISTS `notimeforwaste` DEFAULT CHARACTER SET utf8 ;
USE `notimeforwaste` ;

-- -----------------------------------------------------
-- Table `notimeforwaste`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nmCliente` VARCHAR(100) NOT NULL,
  `email` VARCHAR(70) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `idCliente_UNIQUE` (`idCliente` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Foto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Foto` (
  `idFoto` INT NOT NULL AUTO_INCREMENT,
  `fotoUrl` VARCHAR(700) NOT NULL,
  PRIMARY KEY (`idFoto`)
 )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Endereco` (
  `idEndereco` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(100) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,
  `cidade` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(100) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  `numero` INT NOT NULL,
  `complemento` VARCHAR(100) NOT NULL,
  `apelido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEndereco`),
  UNIQUE INDEX `idEndereco_UNIQUE` (`idEndereco` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Empresa` (
  `idEmpresa` INT NOT NULL AUTO_INCREMENT,
  `nmEmpresa` VARCHAR(100) NOT NULL,
  `CNPJ` VARCHAR(14) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  `idFoto`INT NOT NULL,
  `idEndereco` INT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(25) NOT NULL,

  PRIMARY KEY (`idEmpresa`),
  UNIQUE INDEX `idEmpresa_UNIQUE` (`idEmpresa` ASC) ,
  UNIQUE INDEX `CNP_UNIQUE` (`CNPJ` ASC) ,
  INDEX `fk_Empresa_Foto1_idx` (`idFoto` ASC) ,
  INDEX `fk_Empresa_Endereco1_idx` (`idEndereco` ASC) ,
  CONSTRAINT `fk_Empresa_Foto1`
    FOREIGN KEY (`idFoto`)
    REFERENCES `notimeforwaste`.`Foto` (`idFoto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Empresa_Endereco1`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `notimeforwaste`.`Endereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Pacote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Pacote` (
  `idPacote` INT NOT NULL AUTO_INCREMENT,
  `nmPacote` VARCHAR(100) NOT NULL,
  `preco` FLOAT NOT NULL,
  `idEmpresa` INT NOT NULL,
  `idFoto` INT NOT NULL,
  PRIMARY KEY (`idPacote`),
  UNIQUE INDEX `idPacote_UNIQUE` (`idPacote` ASC) ,
  INDEX `fk_Pacote_Empresa1_idx` (`idEmpresa` ASC) ,
  INDEX `fk_Pacote_Foto1_idx` (`idFoto` ASC) ,
  CONSTRAINT `fk_Pacote_Empresa1`
    FOREIGN KEY (`idEmpresa`)
    REFERENCES `notimeforwaste`.`Empresa` (`idEmpresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pacote_Foto1`
    FOREIGN KEY (`idFoto`)
    REFERENCES `notimeforwaste`.`Foto` (`idFoto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nmProduto` VARCHAR(100) NOT NULL,
  `idPacote` INT NOT NULL,
  `dtValidade` DATE NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idProduto`),
  UNIQUE INDEX `idProduto_UNIQUE` (`idProduto` ASC) ,
  INDEX `fk_Produto_Pacote_idx` (`idPacote` ASC) ,
  CONSTRAINT `fk_Produto_Pacote`
    FOREIGN KEY (`idPacote`)
    REFERENCES `notimeforwaste`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`FormaPagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`FormaPagamento` (
  `idFormaPagamento` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NULL,
  PRIMARY KEY (`idFormaPagamento`),
  UNIQUE INDEX `idFormaPagamento_UNIQUE` (`idFormaPagamento` ASC)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`FormaEntrega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`FormaEntrega` (
  `idFormaEntrega` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idFormaEntrega`),
  UNIQUE INDEX `idFormaEntrega_UNIQUE` (`idFormaEntrega` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Pedido`
-- -----------------------------------------------------
-- DROP TABLE `notimeforwaste`.`Pedido`;
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `status` VARCHAR(15) NOT NULL,
  `idFormaPagamento` INT NOT NULL,
  `idFormaEntrega` INT NOT NULL,
  `frete` DOUBLE NOT NULL,
  `cancelado` BIT NULL,
  `idPacote` INT NOT NULL,
  `dtPedido` DATE NOT NULL,
  `idEndereco` INT,
  `observacao` VARCHAR(300),
  PRIMARY KEY (`idPedido`),
  UNIQUE INDEX `idPedido_UNIQUE` (`idPedido` ASC),
  INDEX `fk_Pedido_Cliente_idx` (`idCliente` ASC) ,
  CONSTRAINT `fk_Pedido_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `notimeforwaste`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Pedido_FormaPagamento_idx` (`idFormaPagamento` ASC) ,
  CONSTRAINT `fk_Pedido_FormaPagamento`
    FOREIGN KEY (`idFormaPagamento`)
    REFERENCES `notimeforwaste`.`FormaPagamento` (`idFormaPagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Pedido_FormaEntrega_idx` (`idFormaEntrega` ASC) ,
  CONSTRAINT `fk_Pedido_FormaEntrega`
    FOREIGN KEY (`idFormaEntrega`)
    REFERENCES `notimeforwaste`.`FormaEntrega` (`idFormaEntrega`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Pedido_Pacote_idx` (`idPacote` ASC) ,
  CONSTRAINT `fk_Pedido_Pacote`
    FOREIGN KEY (`idPacote`)
    REFERENCES `notimeforwaste`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Pedido_Endereco_idx` (`idEndereco` ASC),
  CONSTRAINT `fk_Pedido_Endereco`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `notimeforwaste`.`Endereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB;

ALTER TABLE `notimeforwaste`.`Pedido`
MODIFY COLUMN `dtPedido` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE `notimeforwaste`.`Pedido`
MODIFY COLUMN `status` INT NOT NULL;
ALTER TABLE `notimeforwaste`.`Pedido`
MODIFY COLUMN `idEndereco` INT;

-- -----------------------------------------------------
-- Table `notimeforwaste`.`HorarioFuncionamento`
-- -----------------------------------------------------
-- DROP TABLE `notimeforwaste`.`HorarioFuncionamento`;
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`HorarioFuncionamento` (
  `idHorario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `horarioInicial` TIME NOT NULL,
  `horarioFinal` TIME NOT NULL,
  `idEmpresa` INT NOT NULL,
  PRIMARY KEY (`idHorario`),
  UNIQUE INDEX `idHorario_UNIQUE` (`idHorario` ASC),
  INDEX `fk_HorarioFuncionamento_Empresa_idx` (`idEmpresa` ASC) ,
  CONSTRAINT `fk_HorarioFuncionamento_Empresa`
    FOREIGN KEY (`idEmpresa`)
    REFERENCES `notimeforwaste`.`Empresa` (`idEmpresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Avaliacao`
-- -----------------------------------------------------
-- DROP TABLE `notimeforwaste`.`Avaliacao`;
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Avaliacao` (
  `idAvaliacao` INT NOT NULL AUTO_INCREMENT,
  `comentário` VARCHAR(500) NULL,
  `nota` INT NOT NULL,
  `idEmpresa` INT NOT NULL,
  `idCliente` INT NOT NULL,
  `dtAvaliacao` DATETIME NOT NULL,
  PRIMARY KEY (`idAvaliacao`),
  UNIQUE INDEX `idAvaliação_UNIQUE` (`idAvaliacao` ASC),
  INDEX `fk_Avaliacao_Empresa_idx` (`idEmpresa` ASC) ,
  CONSTRAINT `fk_Avaliacao_Empresa`
    FOREIGN KEY (`idEmpresa`)
    REFERENCES `notimeforwaste`.`Empresa` (`idEmpresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Avaliacao_Cliente_idx` (`idCliente` ASC) ,
  CONSTRAINT `fk_Avaliacao_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `notimeforwaste`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Pacote_FormaPagamento`
-- -----------------------------------------------------
-- DROP TABLE `notimeforwaste`.`Pacote_FormaPagamento`;
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Pacote_FormaPagamento` (
  `idPacote` INT NOT NULL,
  `idFormaPagamento` INT NOT NULL,
  PRIMARY KEY (`idPacote`, `idFormaPagamento`),
  INDEX `fk_Pacote_FormaPagamento_Pacote_idx` (`idPacote` ASC) ,
  CONSTRAINT `fk_Pacote_FormaPagamento_Pacote`
    FOREIGN KEY (`idPacote`)
    REFERENCES `notimeforwaste`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Pacote_FormaPagamento_FormaPagamento_idx` (`idFormaPagamento` ASC) ,
  CONSTRAINT `fk_Pacote_FormaPagamento_FormaPagamento`
    FOREIGN KEY (`idFormaPagamento`)
    REFERENCES `notimeforwaste`.`FormaPagamento` (`idFormaPagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Pacote_FormaEntrega`
-- -----------------------------------------------------
-- DROP TABLE `notimeforwaste`.`Pacote_FormaEntrega`;
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Pacote_FormaEntrega` (
  `idPacote` INT NOT NULL,
  `idFormaEntrega` INT NOT NULL,
  PRIMARY KEY (`idPacote`, `idFormaEntrega`),
  INDEX `fk_Pacote_FormaEntrega_FormaEntrega_idx` (`idFormaEntrega` ASC) ,
  CONSTRAINT `fk_Pacote_FormaEntrega_FormaEntrega`
    FOREIGN KEY (`idFormaEntrega`)
    REFERENCES `notimeforwaste`.`FormaEntrega` (`idFormaEntrega`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Pacote_FormaEntrega_Pacote_idx` (`idPacote` ASC) ,
  CONSTRAINT `fk_Pacote_FormaEntrega_Pacote`
    FOREIGN KEY (`idPacote`)
    REFERENCES `notimeforwaste`.`Pacote` (`idPacote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notimeforwaste`.`Cliente_Endereco`
-- -----------------------------------------------------
-- DROP TABLE `notimeforwaste`.`Cliente_Endereco`;
CREATE TABLE IF NOT EXISTS `notimeforwaste`.`Cliente_Endereco` (
  `idEndereco` INT NOT NULL,
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`idEndereco`, `idCliente`),
  INDEX `fk_Cliente_Endereco_Endereco_idx` (`idEndereco` ASC) ,
  CONSTRAINT `fk_Cliente_Endereco_Endereco`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `notimeforwaste`.`Endereco` (`idEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `fk_Cliente_Endereco_Cliente_idx` (`idCliente` ASC) ,
  CONSTRAINT `fk_Cliente_Endereco_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `notimeforwaste`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION  
)
ENGINE = InnoDB;

INSERT INTO `notimeforwaste`.`cliente`
(
`nmCliente`,
`email`,
`senha`)
VALUES
('admin', 'admin@admin.com', 'admin');

INSERT INTO `notimeforwaste`.`FormaPagamento`
(
`NOME`)
VALUES
('PIX');

INSERT INTO `notimeforwaste`.`FormaPagamento`
(
`NOME`)
VALUES
('Dinheiro');

INSERT INTO `notimeforwaste`.`FormaPagamento`
(
`NOME`)
VALUES
('Cartão de Crédito');

INSERTFunctions INTO `notimeforwaste`.`FormaEntrega`
(
`NOME`)
VALUES
('Delivery');

INSERT INTO `notimeforwaste`.`FormaEntrega`
(
`NOME`)
VALUES
('Retirada');
INSERT INTO FOTO (fotoUrl) value("C:\\fotos\ae6191ea-4b15-4c23-9dd7-25db7137258f");


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
