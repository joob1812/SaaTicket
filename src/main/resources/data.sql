-- src/main/resources/data.sql
INSERT INTO service (name, description) VALUES
                                            ('Seneau', 'Service de distribution d''eau'),
                                            ('Orange', 'Service de télécommunication'),
                                            ('Senelec', 'Service d''électricité')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO location (name, address, service_id) VALUES
                                                     ('Seneau Dakar Centre', 'Avenue Leopold Sedar Senghor, Dakar', 1),
                                                     ('Orange Plateau', 'Rue Mohamed V, Plateau', 2),
                                                     ('Senelec Point E', 'Point E, Rue de Saint Louis', 3)
ON DUPLICATE KEY UPDATE name=name;

-- Mot de passe: admin123
INSERT INTO user (username, password, role) VALUES
    ('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN')
ON DUPLICATE KEY UPDATE username=username;