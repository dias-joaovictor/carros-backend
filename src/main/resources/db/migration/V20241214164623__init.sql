CREATE TABLE carro
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo              VARCHAR(255) NOT NULL,
    ano                 INT          NOT NULL,
    cor                 VARCHAR(255) NOT NULL,
    cavalos_de_potencia INT          NOT NULL,
    fabricante          VARCHAR(255) NOT NULL,
    pais                VARCHAR(255) NOT NULL
);

CREATE TABLE usuario
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    nome     VARCHAR(255) NOT NULL,
    cargo    VARCHAR(255) NOT NULL,
    avatar   VARCHAR(255),
    password VARCHAR(255) NOT NULL
);
