-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 08, 2014 at 08:17 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `set4`
--

-- --------------------------------------------------------

--
-- Table structure for table `currentgame`
--

CREATE TABLE IF NOT EXISTS `currentgame` (
  `RoomId` varchar(45) NOT NULL,
  `Cards` varchar(45) NOT NULL,
  `p1` varchar(10) NOT NULL,
  `p2` varchar(10) NOT NULL,
  `p3` varchar(10) NOT NULL,
  `p4` varchar(10) NOT NULL,
  PRIMARY KEY (`RoomId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `currentgame`
--

INSERT INTO `currentgame` (`RoomId`, `Cards`, `p1`, `p2`, `p3`, `p4`) VALUES
('rno1415421828', '3,2,3,2,1,4,4,2,3,2,1,3,4,4,1,1', '3,2,3,2', '4,4,2,3', '2,1,3,4,4,', '4,4,1,1'),
('rno1415424651', '4,3,2,1,3,3,4,3,1,2,1,4,4,1,2,2', '', '', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
