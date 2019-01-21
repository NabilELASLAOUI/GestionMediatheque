-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 21 Janvier 2019 à 18:38
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gestionmediatheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(25),
(25);

-- --------------------------------------------------------

--
-- Structure de la table `media`
--

CREATE TABLE IF NOT EXISTS `media` (
  `mediaId` bigint(20) NOT NULL,
  `mediaAuthor` varchar(30) NOT NULL,
  `mediaDescription` varchar(255) NOT NULL,
  `mediaStatus` bit(1) NOT NULL,
  `mediaTitle` varchar(30) NOT NULL,
  `typeId` bigint(20) NOT NULL,
  PRIMARY KEY (`mediaId`),
  KEY `FKnhc5ldeegeshvi9qmhmgkkg5f` (`typeId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `media`
--

INSERT INTO `media` (`mediaId`, `mediaAuthor`, `mediaDescription`, `mediaStatus`, `mediaTitle`, `typeId`) VALUES
(17, 'J. K. Rowling', 'Harry Potter est une série littéraire de fantasy écrite par l''auteure britannique J. K. Rowling, dont la suite romanesque s''est achevée en 2007.', b'1', 'Harry Potter', 1),
(18, 'Álex Pina', 'Huit voleurs font une prise d''otages dans la Maison royale de la Monnaie d''Espagne, tandis qu''un génie du crime manipule la police pour mettre son plan à exécution.', b'1', 'LA CASA DE PAPEL', 4),
(19, 'Victor Hugo', 'Victor Hugo est un poète, dramaturge, prosateur et dessinateur romantique français, né le 26 février 1802 à Besançon et mort le 22 mai 1885 à Paris. Il est considéré comme l''un des plus importants écrivains de langue française.', b'0', 'Victor Hugo', 1),
(24, 'moi meme', 'haryrr poterrr', b'1', 'testttttt', 1);

-- --------------------------------------------------------

--
-- Structure de la table `mediatype`
--

CREATE TABLE IF NOT EXISTS `mediatype` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `mediatype`
--

INSERT INTO `mediatype` (`typeId`, `typeName`) VALUES
(1, 'Livre'),
(2, 'CD'),
(3, 'Audio'),
(4, 'Film/Série');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`roleId`, `roleName`) VALUES
(1, 'ADMIN'),
(2, 'CLIENT'),
(3, 'EMPLOYE');

-- --------------------------------------------------------

--
-- Structure de la table `subscription`
--

CREATE TABLE IF NOT EXISTS `subscription` (
  `subscriptionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `beginningDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `subscriptionStatus` bit(1) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `typeId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`subscriptionId`),
  KEY `FKkqxk3wi69v7d30dktdl4dc7lt` (`typeId`),
  KEY `FKkyox4slrm4am71jf32k4kxt8l` (`userId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `subscription`
--

INSERT INTO `subscription` (`subscriptionId`, `beginningDate`, `endDate`, `subscriptionStatus`, `titre`, `typeId`, `userId`) VALUES
(1, '2019-01-26 12:11:00', '2019-01-30 12:02:00', b'1', 'demande d''abonnement', 1, 10);

-- --------------------------------------------------------

--
-- Structure de la table `subscriptiontype`
--

CREATE TABLE IF NOT EXISTS `subscriptiontype` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) DEFAULT NULL,
  `typePrix` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `subscriptiontype`
--

INSERT INTO `subscriptiontype` (`typeId`, `typeName`, `typePrix`) VALUES
(1, 'MENSUEL', '19.9'),
(2, 'ANNUEL', '190');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Password` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `userAddress` varchar(255) NOT NULL,
  `userMail` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `userPhone` varchar(255) NOT NULL,
  `roles_roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`),
  KEY `FKp1ew1gcf0i8l0eu0m6r35djcs` (`roles_roleId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`userId`, `Password`, `enabled`, `userAddress`, `userMail`, `userName`, `userPhone`, `roles_roleId`) VALUES
(1, '$2a$10$iFG/jRVSUQWbaW7yzlJsXO7Ht.HSJwx9LfNUCcN9M85XCD8AH/V/O', b'1', '12 rue des freres lumières', 'miagemulhouse@uha.fr', 'miagemulhouse', '0663731871', 1),
(9, '$2a$10$iFG/jRVSUQWbaW7yzlJsXO7Ht.HSJwx9LfNUCcN9M85XCD8AH/V/O', b'1', '53 rue du manège', 'employe@uha.fr', 'employe', '+33663731871', 3),
(10, '$2a$10$Qoa16HHa49rFfFE3Wav7I.izYN4c.6QR7iRiHsrpU25LujaO2EjIO', b'1', '53 rue du manège', 'nabilelaslaoui@gmail.com', 'adil', '+33663731871', 2);

-- --------------------------------------------------------

--
-- Structure de la table `user_media`
--

CREATE TABLE IF NOT EXISTS `user_media` (
  `borrowingDate` datetime DEFAULT NULL,
  `returnDate` datetime DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `mediaId` bigint(20) NOT NULL,
  PRIMARY KEY (`mediaId`,`userId`),
  KEY `FKm8sinedjg82dmgi6qo806k876` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user_media`
--

INSERT INTO `user_media` (`borrowingDate`, `returnDate`, `status`, `userId`, `mediaId`) VALUES
('2019-01-21 18:29:34', '2019-02-20 18:29:34', b'1', 10, 17);

-- --------------------------------------------------------

--
-- Structure de la table `verificationtoken`
--

CREATE TABLE IF NOT EXISTS `verificationtoken` (
  `id` bigint(20) NOT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5c4yk5cnbi5x2go5a1oxepopc` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `verificationtoken`
--

INSERT INTO `verificationtoken` (`id`, `expiryDate`, `token`, `user_id`) VALUES
(1, NULL, '1b7cef47-8402-44f3-bb14-d7a9817477b8', 1),
(2, NULL, '59c513d9-7551-456a-b963-0efdba86bef8', 1),
(3, NULL, '234988df-fdd7-4736-8213-8fd335673ef1', 2),
(4, NULL, '248a84d9-4fe2-4a3b-89c1-ee7e15bdc5d1', 2),
(5, NULL, '3e24c703-57a0-47f7-b11b-b6c0bba33898', 3),
(6, NULL, 'eb5604d2-9549-4c88-800d-ba7ad8ec78b9', 3),
(7, NULL, '1385e5af-9052-40d3-a6bf-0da996187195', 4),
(8, NULL, 'e446bdf5-08ca-4cd1-abb3-f2d30effae32', 4),
(9, NULL, '92e1be26-994e-4518-80fc-369f78bc49da', 5),
(10, NULL, '0ecff20f-8f10-454f-b07d-e8514dd1342b', 5),
(11, NULL, 'c03b4c98-23f6-470e-96c0-4dce64911c1f', 6),
(12, NULL, '7e1bfb61-86ab-4278-88eb-602c6ec28a27', 6),
(13, NULL, '5c393378-ef4d-493e-8192-aab02df22f38', 7),
(14, NULL, 'bb236fe2-92e2-4590-983f-9e6f07a6ec60', 7),
(15, NULL, '6407b284-1720-46da-9742-819398f70a82', 8),
(16, NULL, '77949ac8-950d-45ad-a9d4-3c3eafd4c9ed', 8),
(20, NULL, '6c0f3226-9fd1-4cf7-9a20-e6fa75b2465e', 9),
(21, NULL, 'f87ed4a6-e692-4b5b-b716-eed95f29a259', 9),
(22, NULL, 'b58d1287-4170-4933-9cde-8fa54dc1b8eb', 10),
(23, NULL, '5fa66d37-4466-4796-aaae-fdd4c6340c7b', 10);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
