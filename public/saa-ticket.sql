-- Création de la base de données
CREATE DATABASE IF NOT EXISTS saaticket_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE saaticket_db;

-- Table des services
CREATE TABLE service (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         description TEXT
);

-- Table des localisations
CREATE TABLE location (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address TEXT,
                          service_id BIGINT,
                          FOREIGN KEY (service_id) REFERENCES service(id)
);

-- Table des files d'attente
CREATE TABLE queue (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       current_number INT DEFAULT 0,
                       last_number INT DEFAULT 0,
                       location_id BIGINT UNIQUE,
                       FOREIGN KEY (location_id) REFERENCES location(id)
);

-- Table des tickets
CREATE TABLE ticket (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        ticket_number INT NOT NULL,
                        creation_time DATETIME NOT NULL,
                        is_served BOOLEAN DEFAULT FALSE,
                        queue_id BIGINT,
                        FOREIGN KEY (queue_id) REFERENCES queue(id)
);

-- Table des utilisateurs
CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(20) NOT NULL,
                      location_id BIGINT,
                      FOREIGN KEY (location_id) REFERENCES location(id)
);

-- Insertion de données de test
INSERT INTO service (name, description) VALUES
                                            ('Seneau', 'Service de distribution d''eau'),
                                            ('Orange', 'Service de télécommunication'),
                                            ('Senelec', 'Service d''électricité');

INSERT INTO location (name, address, service_id) VALUES
                                                     ('Seneau Dakar Centre', 'Avenue Leopold Sedar Senghor, Dakar', 1),
                                                     ('Orange Plateau', 'Rue Mohamed V, Plateau', 2),
                                                     ('Senelec Point E', 'Point E, Rue de Saint Louis', 3);

-- Création d'un administrateur par défaut (mot de passe: admin123)
INSERT INTO user (username, password, role) VALUES
    ('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN');