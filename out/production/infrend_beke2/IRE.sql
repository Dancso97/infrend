-- phpMyAdmin SQL Dump

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

#Deleting tables;
CREATE SCHEMA IF NOT EXISTS beke_library DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;

USE beke_library;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `full_name` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
                                       `mobile_number` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
                                       `id_card_number` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
                                       `home_address` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
                                       `deleted_user` int(1) NOT NULL,
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `mobile_number` (`mobile_number`),
                                       UNIQUE KEY `id_card_number` (`id_card_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

INSERT INTO users (id, full_name, mobile_number, id_card_number, home_address, deleted_user) VALUES(1, 'Kovács Kálmán Örs', '(73)/223-1781', '378213KG', '3242 Miskolc Belváros út 3', 0);
INSERT INTO users (id, full_name, mobile_number, id_card_number, home_address, deleted_user) VALUES(2, 'Beke Dániel', '(84)/932-8894', '394329AD', '3428 Mályinka Nemtudom utca 32', 0);
INSERT INTO users (id, full_name, mobile_number, id_card_number, home_address, deleted_user) VALUES(3, 'Asd Ferenc', '(93)/294-3329', '832894OG', '2334 Tapolca keresettaferi út 4', 0);

DROP TABLE IF EXISTS loanables;
CREATE TABLE IF NOT EXISTS loanables (
                                           `id` int(11) NOT NULL AUTO_INCREMENT,
                                           `author` varchar(25) COLLATE utf8_hungarian_ci NOT NULL,
                                           `title` varchar(25) COLLATE utf8_hungarian_ci NOT NULL,
                                           `supplied_date` datetime NOT NULL,
                                           `type` enum('cd','dvd','book','loudbook') COLLATE utf8_hungarian_ci NOT NULL,
                                           `status` enum('borrowed','loanable','scrapped') COLLATE utf8_hungarian_ci NOT NULL,
                                           PRIMARY KEY (`id`),
                                           UNIQUE KEY `author` (`author`),
                                           UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

INSERT INTO loanables (id, author, title, supplied_date, type, status) VALUES(1, 'Beke Dániel', 'Java for noobies', '2020-04-18 13:35:44', 'book', 'borrowed');
INSERT INTO loanables (id, author, title, supplied_date, type, status) VALUES(2, 'Adi Ender', 'Bölcsességek 2.', '2020-04-18 13:36:14', 'cd', 'borrowed');
INSERT INTO loanables (id, author, title, supplied_date, type, status) VALUES(3, 'Régi Rudof', 'Téli élményeim', '2020-04-18 13:36:47', 'dvd', 'scrapped');
INSERT INTO loanables (id, author, title, supplied_date, type, status) VALUES(4, 'Nemtudom Nándor', 'Élet értelmei', '2020-04-18 13:37:13', 'cd', 'loanable');
INSERT INTO loanables (id, author, title, supplied_date, type, status) VALUES(5, 'Valaki Vilmos', 'Kedvenc DVD lemezem', '2020-04-18 13:37:41', 'dvd', 'loanable');
INSERT INTO loanables (id, author, title, supplied_date, type, status) VALUES(6, 'Hangoskönyv Helga', 'Trumps best speeches', '2020-04-18 13:38:13', 'loudbook', 'loanable');

DROP TABLE IF EXISTS loanings;
CREATE TABLE IF NOT EXISTS loanings (
                                          `id` int(11) NOT NULL AUTO_INCREMENT,
                                          `who_borrowed` int(11) NOT NULL,
                                          `what_borrowed` int(11) NOT NULL,
                                          `when_borrowed` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          `when_got_back` datetime DEFAULT NULL,
                                          `loaned_days` int(11),
                                          PRIMARY KEY (`id`),
                                          KEY `who_borrowed` (`who_borrowed`),
                                          KEY `what_borrowed` (`what_borrowed`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

INSERT INTO loanings (id, who_borrowed, what_borrowed, when_borrowed, when_got_back, loaned_days) VALUES(1, 2, 2, '2020-04-18 13:38:45', NULL, DATEDIFF(NOW(),'2020-04-18 13:38:45'));
INSERT INTO loanings (id, who_borrowed, what_borrowed, when_borrowed, when_got_back, loaned_days) VALUES(2, 2, 1, '2020-04-18 13:39:12', NULL, DATEDIFF(NOW(),'2020-04-18 13:39:12'));

ALTER TABLE loanings
    ADD CONSTRAINT loanings_ibfk_1 FOREIGN KEY (`who_borrowed`) REFERENCES users (`id`),
    ADD CONSTRAINT loanings_ibfk_2 FOREIGN KEY (`what_borrowed`) REFERENCES loanables (`id`);
COMMIT;
