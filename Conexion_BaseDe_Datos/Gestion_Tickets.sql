
CREATE TABLE Usuario (
    idusuario SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL
);


CREATE TABLE Administrador (
    idadministrador SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL
);

CREATE TABLE Tecnico (
    idtecnico SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL
);


CREATE TABLE Ticket (
    idticket SERIAL PRIMARY KEY,
    idusuario INT NOT NULL,
    codigo_ticket SERIAL UNIQUE,  -- Cambio aquí, ahora se genera automáticamente
    nombre_ticket VARCHAR(100) NOT NULL, 
    empresa VARCHAR(100) NOT NULL, 
    prioridad VARCHAR(20) CHECK (prioridad IN ('Baja', 'Media', 'Alta')) NOT NULL, 
    departamento VARCHAR(50) NOT NULL, 
    descripcion TEXT, 
    cantidad_disponible INT, 
    fecha_vencimiento DATE, 
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) DEFAULT 'Abierto',
    FOREIGN KEY (idusuario) REFERENCES Usuario(idusuario)
);


-- Tabla Nota_Ticket (relacionada correctamente)
CREATE TABLE Nota_Ticket (
    idnota SERIAL PRIMARY KEY,
    idticket INT NOT NULL,
    idusuario INT NOT NULL,
    contenido TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idticket) REFERENCES Ticket(idticket),
    FOREIGN KEY (idusuario) REFERENCES Usuario(idusuario)
);






-- Primero borra las relaciones hijas (por dependencia)
DELETE FROM Nota_Ticket;
DELETE FROM Ticket;
DELETE FROM Tecnico;
DELETE FROM Administrador;
DELETE FROM Usuario;

-- Reinicia los contadores de ID (serial) en todas las tablas
ALTER SEQUENCE usuario_idusuario_seq RESTART WITH 1;
ALTER SEQUENCE administrador_idadministrador_seq RESTART WITH 1;
ALTER SEQUENCE tecnico_idtecnico_seq RESTART WITH 1;
ALTER SEQUENCE ticket_idticket_seq RESTART WITH 1;
ALTER SEQUENCE nota_ticket_idnota_seq RESTART WITH 1;