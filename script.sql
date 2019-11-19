CREATE SCHEMA `ventas` ;

CREATE TABLE `ventas`.`proveedor` (
  `id` BIGINT NOT NULL,
  `razon_social` VARCHAR(150) NOT NULL,
  `cuit` BIGINT NULL,
  `domicilio` VARCHAR(150) NULL,
  `telefono` VARCHAR(150) NULL,
  `email` VARCHAR(150) NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `ventas`.`cliente` (
  `id` BIGINT NOT NULL,
  `razon_social` VARCHAR(150) NOT NULL,
  `documento` BIGINT NULL,
  `domicilio` VARCHAR(150) NULL,
  `telefono` VARCHAR(150) NULL,
  `email` VARCHAR(150) NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `ventas`.`item_venta` (
  `id` BIGINT NOT NULL,
  `importe` NUMERIC NOT NULL,
  `cantidad` NUMERIC NULL,
  `id_venta` BIGINT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `ventas`.`venta` (
  `id` BIGINT NOT NULL,
  `importe` NUMERIC NOT NULL,
  `fecha` TIMESTAMP NOT NULL,
  `id_cliente` BIGINT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `ventas`.`producto` (
  `id` BIGINT NOT NULL,
  `nombre` VARCHAR(150) NULL,
  `descripcion` VARCHAR(150) NULL,
  `precio` NUMERIC NOT NULL,
  `stock` NUMERIC NULL,
  `id_proveedor` BIGINT NULL,
  PRIMARY KEY (`id`));
  
ALTER TABLE `ventas`.`item_venta` ADD INDEX `venta_fk_idx` (`id_venta` ASC) VISIBLE;
;
ALTER TABLE `ventas`.`item_venta` ADD CONSTRAINT `venta_fk` FOREIGN KEY (`id_venta`) REFERENCES `ventas`.`venta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `ventas`.`cliente` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `ventas`.`proveedor` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `ventas`.`item_venta` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `ventas`.`producto` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `ventas`.`venta` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;