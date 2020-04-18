CREATE DATABASE IF NOT EXISTS `beke_library` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;

USE beke_library;

CREATE TABLE users (
    id int AUTO_INCREMENT PRIMARY KEY ,
    full_name varchar(50) NOT NULL ,
    mobile_number varchar(20) UNIQUE NOT NULL,
    id_card_number varchar(20) UNIQUE NOT NULL,
    home_address varchar(255) NOT NULL,
    deleted_user int(1) NOT NULL CHECK (deleted_user IN (0,1))
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

CREATE TABLE loanables (
    id int PRIMARY KEY AUTO_INCREMENT,
    author varchar(25) UNIQUE NOT NULL,
    title varchar(25) UNIQUE NOT NULL,
    supplied_date datetime NOT NULL,
    type ENUM ('cd', 'dvd', 'book', 'loudbook') NOT NULL,
    status ENUM ('borrowed', 'loanable', 'scrapped') NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

CREATE TABLE loanings (
    id int PRIMARY KEY AUTO_INCREMENT,
    who_borrowed int NOT NULL ,
    what_borrowed int NOT NULL ,
    when_borrowed datetime NOT NULL DEFAULT NOW(),
    when_got_back datetime DEFAULT NULL,
    FOREIGN KEY (who_borrowed) REFERENCES users(id),
    FOREIGN KEY (what_borrowed) REFERENCES loanables(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

