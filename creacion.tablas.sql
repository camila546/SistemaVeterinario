-- Creación de la base de datos
CREATE DATABASE bd_veterinaria;

-- Activación de la base de datos
USE bd_veterinaria;

-- Tabla de clientes
CREATE TABLE t_clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    cedula VARCHAR(30) UNIQUE,
    telefono VARCHAR(30),
    correo VARCHAR(100)
);

-- Tabla de mascotas
CREATE TABLE t_mascotas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idCliente INT NOT NULL,
    idMascota VARCHAR(30) UNIQUE,
    nombre VARCHAR(100),
    especie VARCHAR(50),
    raza VARCHAR(100),
    edad INT,
    FOREIGN KEY (idCliente) REFERENCES t_clientes(id)
);

-- Tabla de veterinarios
CREATE TABLE t_veterinarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    cedula VARCHAR(30) UNIQUE,
    telefono VARCHAR(30),
    correo VARCHAR(100),
    especialidad VARCHAR(100)
);

-- Tabla de consultas
CREATE TABLE t_consultas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100),
    fecha DATE,
    hora TIME,
    costo DOUBLE,
    diagnostico TEXT,
    estado VARCHAR(50),
    idMascota INT NOT NULL,
    idVeterinario INT NOT NULL,
    FOREIGN KEY (idMascota) REFERENCES t_mascotas(id),
    FOREIGN KEY (idVeterinario) REFERENCES t_veterinarios(id)
);
