-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Gazda: 127.0.0.1
-- Timp de generare: 20 Mar 2014 la 16:38
-- Versiune server: 5.6.11
-- Versiune PHP: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Bază de date: `springgamestore`
--
CREATE DATABASE IF NOT EXISTS `springgamestore` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `springgamestore`;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `companies`
--

CREATE TABLE IF NOT EXISTS `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `isPublisher` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Flag to see that this company is/isn''t publisher',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='List of all the game developer/publisher companies' AUTO_INCREMENT=10 ;

--
-- Salvarea datelor din tabel `companies`
--

INSERT INTO `companies` (`id`, `name`, `isPublisher`) VALUES
(1, 'Blizzard', 1),
(3, 'Westwood Studios', 0),
(4, 'Bethesda', 0),
(6, 'Westwood', 0),
(7, 'Bullfrog', 0),
(8, 'Creative Assembly', 0),
(9, 'Sega', 1);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `games`
--

CREATE TABLE IF NOT EXISTS `games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `addedOn` date DEFAULT NULL COMMENT 'The date on which the game was added to the database.',
  `releasedOn` date DEFAULT NULL COMMENT 'The date on which the game was released.',
  `idDeveloper` int(11) NOT NULL COMMENT 'The developer''s id taken from the companies table',
  `idPublisher` int(11) NOT NULL COMMENT 'The publisher''s id taken from the companies table.',
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='The list of games in the database' AUTO_INCREMENT=6 ;

--
-- Salvarea datelor din tabel `games`
--

INSERT INTO `games` (`id`, `name`, `addedOn`, `releasedOn`, `idDeveloper`, `idPublisher`, `description`) VALUES
(1, 'Starcraft', '2014-03-16', '1998-03-31', 1, 1, 'Starcraft is one of the best real time strategy games ever. \r\nIt featured 3 distinct races that play differently, something that was revolutionary at that time.\r\nIt''s also a very competitive game, having been used extensively throughout Electronic Sports Championships. There are tournaments held reguralry even today.'),
(2, 'Rome: Total War', NULL, NULL, 8, 9, ''),
(3, 'Crusader Kings 2', NULL, NULL, 0, 0, ''),
(4, 'Warcraft III', NULL, NULL, 0, 0, ''),
(5, 'Hearts of Iron III', NULL, NULL, 0, 0, '');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `ownedgames`
--

CREATE TABLE IF NOT EXISTS `ownedgames` (
  `games_id` int(11) NOT NULL,
  `username` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `ownedgames`
--

INSERT INTO `ownedgames` (`games_id`, `username`) VALUES
(2, 'andy'),
(4, 'Mike'),
(2, 'Mike'),
(1, 'Mike'),
(3, 'Mike'),
(5, 'Mike');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `authority` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT 'The name that appears on the user''s profile',
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='The user''s list';

--
-- Salvarea datelor din tabel `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`, `authority`, `name`, `email`) VALUES
('Administrator', 'hellohello', 1, 'ROLE_ADMIN', 'The Administrator', 'seba_4_all@yahoo.com'),
('andy', 'andy123', 1, 'ROLE_USER', 'Adrian Toma', 'adrian@yahoo.com'),
('Mike', 'apass', 1, 'ROLE_ADMIN', 'Michellangelo', 'mike@yahoo.com');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `wishlistgames`
--

CREATE TABLE IF NOT EXISTS `wishlistgames` (
  `games_id` int(11) NOT NULL,
  `username` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `wishlistgames`
--

INSERT INTO `wishlistgames` (`games_id`, `username`) VALUES
(4, 'Mike');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
