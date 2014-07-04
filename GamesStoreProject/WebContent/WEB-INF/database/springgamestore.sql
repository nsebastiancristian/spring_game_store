-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Gazda: 127.0.0.1
-- Timp de generare: 04 Iul 2014 la 14:00
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
-- Structura de tabel pentru tabelul `avatars`
--

CREATE TABLE IF NOT EXISTS `avatars` (
  `username` varchar(60) NOT NULL,
  `avatar` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='This table holds the avatar picture file names';

--
-- Salvarea datelor din tabel `avatars`
--

INSERT INTO `avatars` (`username`, `avatar`) VALUES
('Zulu', 'Zulu'),
('McBride:Loid''s', 'McBride Loid''s');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `companies`
--

CREATE TABLE IF NOT EXISTS `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `isPublisher` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Flag to see that this company is/isn''t publisher',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='List of all the game developer/publisher companies' AUTO_INCREMENT=16 ;

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
(9, 'Sega', 1),
(11, 'Team 17', 0),
(12, 'EA', 0),
(13, 'Sierra Studios', 0),
(14, 'Harebrained Schemes', 0),
(15, 'Paradox Entertainment', 0);

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
  `discount` int(11) NOT NULL DEFAULT '0',
  `price` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='The list of games in the database' AUTO_INCREMENT=23 ;

--
-- Salvarea datelor din tabel `games`
--

INSERT INTO `games` (`id`, `name`, `addedOn`, `releasedOn`, `idDeveloper`, `idPublisher`, `description`, `discount`, `price`) VALUES
(1, 'Starcraft', '2014-03-16', '1998-03-31', 1, 1, 'Starcraft is one of the best real time strategy games ever. \r\nIt featured 3 distinct races that play differently, something that was revolutionary at that time.\r\nIt''s also a very competitive game, having been used extensively throughout Electronic Sports Championships. There are tournaments held reguralry even today.', 30, 24.99),
(2, 'Rome: Total War', NULL, NULL, 8, 9, '', 50, 19.25),
(3, 'Crusader Kings 2', NULL, NULL, 0, 0, '', 30, 17.89),
(4, 'Warcraft III', NULL, NULL, 0, 0, '', 0, 45),
(5, 'Hearts of Iron III', NULL, NULL, 0, 0, '', 0, 27.2),
(8, 'Morrowind', '2014-04-29', '1994-04-02', 4, 9, 'A very good rpg', 50, 15.99),
(9, 'Shadowrun Returns', '2014-05-06', '2013-07-25', 14, 1, 'MAN MEETS MAGIC & MACHINE. The year is 2054. Magic has returned to the world, awakening powerful creatures of myth and legend. Technology merges with flesh and consciousness. Elves, trolls, orks and dwarves walk among us, while ruthless corporations bleed the world dry. ', 0, 20.19),
(15, 'Victoria II', '2014-06-03', '2014-06-13', 15, 9, 'Players take control of any country during the Victorian era.', 0, 24.99),
(16, 'Rayman', '2014-06-04', '2014-06-06', 4, 1, 'Rayman is a platformer', 0, 29.99),
(17, 'Larry', '2014-06-04', '2014-06-10', 3, 1, 'Larry is an adult humorous adventure title.', 0, 39.99),
(18, 'TTTTTT', '2014-06-04', '2014-06-12', 7, 1, 'a dfas dads fasd asd ', 0, 10),
(19, 'ZZZZZZZZZZZ', '2014-06-04', '2014-06-04', 3, 1, 'asdfasd fsd fasd fsad f', 0, 20),
(20, 'Dark Souls', '2014-06-04', '2014-06-12', 4, 1, 'Darks Souls is very hard.', 0, 29.99),
(21, 'Terminator 2', '2014-06-04', '2014-06-05', 14, 9, 'I''ll be back!', 0, 11.99),
(22, 'Testing Now', '2014-06-05', '2014-06-04', 9, 9, 'bla bla bla bla bla', 0, 39.99);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `games_genres`
--

CREATE TABLE IF NOT EXISTS `games_genres` (
  `id` int(11) NOT NULL,
  `genre` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `games_genres`
--

INSERT INTO `games_genres` (`id`, `genre`) VALUES
(12, 'Racing'),
(12, 'Shooter'),
(12, 'Sport'),
(13, 'First-Person Shooter'),
(13, 'Shooter'),
(13, 'Sport'),
(14, 'Shooter'),
(14, 'Survival Horror'),
(15, 'Grand Strategy'),
(15, 'History'),
(16, 'Platform'),
(16, 'Indie'),
(17, 'Point-and-Click'),
(17, 'Puzzle'),
(17, 'Puzzle'),
(18, 'Racing'),
(18, 'Tower Defense'),
(19, 'Tactical Action'),
(19, '4X'),
(19, 'Grand Strategy'),
(20, 'Fighting'),
(20, 'First-Person Shooter'),
(21, 'Fighting'),
(21, 'First-Person Shooter'),
(21, 'Shooter'),
(21, 'Survival Horror'),
(22, 'First-Person Shooter'),
(22, 'Platform'),
(22, 'Tactical Action');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `games_platforms`
--

CREATE TABLE IF NOT EXISTS `games_platforms` (
  `gameId` int(11) NOT NULL,
  `platform` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `games_platforms`
--

INSERT INTO `games_platforms` (`gameId`, `platform`) VALUES
(15, 'PC/Windows'),
(16, 'PC/Windows'),
(16, 'Mac'),
(16, 'Android'),
(17, 'PC/Windows'),
(17, 'Linux'),
(18, 'PC/Windows'),
(18, 'Linux'),
(19, 'PC/Windows'),
(19, 'Mac'),
(20, 'PC/Windows'),
(20, 'Mac'),
(21, 'PC/Windows'),
(21, 'Mac'),
(22, 'PC/Windows'),
(22, 'Linux');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `games_tags`
--

CREATE TABLE IF NOT EXISTS `games_tags` (
  `gameId` int(60) NOT NULL,
  `tagId` int(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `games_tags`
--

INSERT INTO `games_tags` (`gameId`, `tagId`) VALUES
(22, 1),
(22, 2),
(22, 5),
(22, 6),
(22, 7);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `games_themes`
--

CREATE TABLE IF NOT EXISTS `games_themes` (
  `gameId` int(11) NOT NULL,
  `themeId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `games_themes`
--

INSERT INTO `games_themes` (`gameId`, `themeId`) VALUES
(21, 1),
(21, 3),
(21, 6),
(21, 10),
(22, 1),
(22, 3),
(22, 5),
(22, 6);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `genres`
--

CREATE TABLE IF NOT EXISTS `genres` (
  `genre` varchar(60) NOT NULL,
  `category` enum('Action','Strategy','RPG','Adventure','Simulator','Casual','Indie','Kids') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='The genre of the games';

--
-- Salvarea datelor din tabel `genres`
--

INSERT INTO `genres` (`genre`, `category`) VALUES
('Fighting', 'Action'),
('First-Person Shooter', 'Action'),
('Platform', 'Action'),
('Racing', 'Action'),
('Shooter', 'Action'),
('Sport', 'Action'),
('Survival Horror', 'Action'),
('Tactical Action', 'Action'),
('Third-Person Shooter', 'Action'),
('4X', 'Strategy'),
('Grand Strategy', 'Strategy'),
('History', 'Strategy'),
('MOBA (Multiplayer Online Battle Arena)', 'Strategy'),
('Real-Time Strategy', 'Strategy'),
('Tower Defense', 'Strategy'),
('Turn-Based Strategy', 'Strategy'),
('Action RPG', 'RPG'),
('MMORPG', 'RPG'),
('Real-time Strategy RPG', 'RPG'),
('Roguelike', 'RPG'),
('Roleplaying Game', 'RPG'),
('Turn-based RPG', 'RPG'),
('Action Adventure', 'Adventure'),
('Horror Adventure', 'Adventure'),
('Open World Action-Adventure', 'Adventure'),
('Point-and-Click', 'Adventure'),
('Puzzle', 'Adventure'),
('Business Simulator', 'Simulator'),
('City-building Games', 'Simulator'),
('Combat Simulator', 'Simulator'),
('Driving Simulator', 'Simulator'),
('Life Simulator', 'Simulator'),
('Manager Simulator', 'Simulator'),
('Racing Simulator', 'Simulator'),
('Arcade', 'Casual'),
('Card and Casino Games', 'Casual'),
('Hidden Object', 'Casual'),
('Match 3', 'Casual'),
('Music and Dancing', 'Casual'),
('Party Games', 'Casual'),
('Relation', 'Casual'),
('Retro', 'Casual'),
('Time Management', 'Casual'),
('Indie', 'Indie'),
('Pets and Animals', 'Kids'),
('Puzzle', 'Kids');

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
-- Structura de tabel pentru tabelul `pics`
--

CREATE TABLE IF NOT EXISTS `pics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(20) NOT NULL,
  `gameId` int(11) NOT NULL,
  `userId` int(11) NOT NULL DEFAULT '0',
  `dateAdded` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Stores the name of the picture files on disk' AUTO_INCREMENT=54 ;

--
-- Salvarea datelor din tabel `pics`
--

INSERT INTO `pics` (`id`, `filename`, `gameId`, `userId`, `dateAdded`) VALUES
(46, 'Shadowrun Returns_46', 9, 0, '2014-05-07 00:00:00'),
(47, 'Shadowrun Returns_47', 9, 0, '2014-05-07 00:00:00'),
(48, 'Shadowrun Returns_48', 9, 0, '2014-05-07 00:00:00'),
(49, 'Shadowrun Returns_49', 9, 0, '2014-05-07 00:00:00'),
(50, 'Shadowrun Returns_50', 9, 0, '2014-05-07 12:04:00'),
(51, 'Shadowrun Returns_51', 9, 0, '2014-05-07 13:29:29'),
(52, 'Shadowrun Returns_52', 9, 0, '2014-05-07 13:29:59'),
(53, 'Shadowrun Returns_53', 9, 0, '2014-05-07 13:29:59');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Salvarea datelor din tabel `tags`
--

INSERT INTO `tags` (`id`, `tag`) VALUES
(1, 'Grand Strategy'),
(2, 'Medieval'),
(3, 'Strategy'),
(4, 'Historic'),
(5, 'Fighting'),
(6, 'Action'),
(7, 'Game for Windows Live'),
(8, 'Sandbox'),
(9, 'Crafting'),
(10, 'Survival'),
(11, 'Indie'),
(12, 'Space'),
(13, 'FPS'),
(14, 'Multiplayer'),
(15, 'Shooter'),
(16, 'Co-op'),
(17, 'RPG'),
(18, 'Adventure'),
(19, 'Party-Based RPG'),
(20, 'Classic'),
(21, 'Fantasy'),
(22, '4x'),
(23, 'Turn Based Strategy'),
(24, 'One More Turn'),
(25, 'Total War'),
(26, 'Adventure'),
(27, 'Point & Click'),
(28, 'Mistery'),
(29, 'Crime'),
(30, 'Comedy'),
(31, 'Zombies');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `themes`
--

CREATE TABLE IF NOT EXISTS `themes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Salvarea datelor din tabel `themes`
--

INSERT INTO `themes` (`id`, `theme`) VALUES
(1, 'Cyberpunk / Dark Sci-Fi'),
(2, 'Fantasy'),
(3, 'Sci-Fi / Futuristic'),
(4, 'Turn Based'),
(5, 'Historical Battle'),
(6, 'Real-Time'),
(7, 'Adult'),
(8, 'Puzzle-Solving'),
(9, 'Managerial'),
(10, 'Shooter'),
(11, 'Arcade'),
(12, 'Fighting'),
(13, 'Martial Arts'),
(14, 'Naval'),
(15, 'Stealth'),
(16, 'Anime / Manga');

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
('McBride:Loid''s', 'mcbain123', 1, 'ROLE_USER', 'User McBrain', 'mcbain@yahoo.com'),
('Mike', 'apass', 1, 'ROLE_ADMIN', 'Michellangelo', 'mike@yahoo.com'),
('Sebas', 'maverik123', 1, 'ROLE_USER', 'Sebastian', 'seba@yahoo.com'),
('Zulu', 'zulu123', 1, 'ROLE_USER', 'Zulu McMullu', 'zulu@yahoo.com');

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
