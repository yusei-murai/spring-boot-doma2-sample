CREATE DATABASE IF NOT EXISTS sampledb;
USE sampledb;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE emails (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    mail VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users (name, email) VALUES 
('田中太郎', 'tanaka@example.com'),
('佐藤花子', 'sato@example.com'),
('鈴木次郎', 'suzuki@example.com');

INSERT INTO emails (user_id, mail) VALUES 
(1, 'tanaka.main@example.com'),
(1, 'tanaka.sub@example.com'),
(2, 'sato@example.com'),
(3, 'suzuki.work@example.com'),
(3, 'suzuki.private@example.com');