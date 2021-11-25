-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 27, 2021 at 08:46 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `RasPiRetail`
--

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_CustomerCarts`
--

CREATE TABLE `RasPiRetail_CustomerCarts` (
  `customerID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `quantityOrdered` int(11) DEFAULT NULL,
  `dateCreated` timestamp NULL DEFAULT current_timestamp(),
  `dateUpdated` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_CustomerCarts`
--

INSERT INTO `RasPiRetail_CustomerCarts` (`customerID`, `productID`, `quantityOrdered`, `dateCreated`, `dateUpdated`) VALUES
(1, 1, 4, '2021-10-26 21:42:25', '0000-00-00 00:00:00'),
(1, 3, 1, '2021-10-26 21:42:25', '0000-00-00 00:00:00'),
(1, 5, 2, '2021-10-26 21:44:16', '0000-00-00 00:00:00'),
(2, 2, 1, '2021-10-26 21:44:31', '0000-00-00 00:00:00'),
(2, 5, 10, '2021-10-26 21:44:31', '0000-00-00 00:00:00'),
(2, 6, 2, '2021-10-26 21:44:31', '0000-00-00 00:00:00'),
(3, 3, 1, '2021-10-26 21:44:44', '0000-00-00 00:00:00'),
(4, 5, 5, '2021-10-26 21:44:44', '0000-00-00 00:00:00'),
(4, 6, 5, '2021-10-26 21:44:44', '0000-00-00 00:00:00'),
(5, 4, 10, '2021-10-26 21:46:23', '0000-00-00 00:00:00'),
(5, 6, 10, '2021-10-26 21:46:23', '0000-00-00 00:00:00'),
(6, 2, 1, '2021-10-26 21:46:23', '0000-00-00 00:00:00'),
(6, 5, 3, '2021-10-26 21:46:23', '0000-00-00 00:00:00'),
(6, 6, 3, '2021-10-26 21:46:23', '0000-00-00 00:00:00'),
(7, 1, 1, '2021-10-26 21:46:23', '0000-00-00 00:00:00'),
(7, 2, 1, '2021-10-26 21:46:23', '0000-00-00 00:00:00'),
(8, 3, 1, '2021-10-26 21:46:23', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_Products`
--

CREATE TABLE `RasPiRetail_Products` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `stock` int(11) NOT NULL,
  `price` double NOT NULL,
  `type` varchar(50) NOT NULL,
  `description` longtext NOT NULL,
  `url` varchar(100) NOT NULL
) ;

--
-- Dumping data for table `RasPiRetail_Products`
--

INSERT INTO `RasPiRetail_Products` (`id`, `name`, `stock`, `price`, `type`, `description`, `url`) VALUES
(1, 'Raspberry Pi 3 Model A+', 100, 26.84, 'Mainline', '1.4GHz 64-bit quad-core processor, dual-band wireless LAN, Bluetooth 4.2/BLE in the same mechanical format as the Raspberry Pi 1 Model A+', 'https://thepihut.com/products/raspberry-pi-3-model-a-plus'),
(2, 'Raspberry Pi 4 Model B', 100, 62.32, 'Mainline', 'People have worked hard and long to develop the next generation of Raspberry Pis, and they present you the RPi4, the first of its kind to have 8GB Memory.', 'https://thepihut.com/products/raspberry-pi-4-model-b'),
(3, 'Raspberry Pi 400', 100, 77.21, 'Mainline', 'Featuring a quad-core 64-bit processor, 4GB of RAM, wireless networking, dual-display output, and 4K video playback, as well as a 40-pin GPIO header, Raspberry Pi 400 is a powerful, easy-to-use computer built into a neat and portable keyboard.', 'https://thepihut.com/products/raspberry-pi-400'),
(4, 'Raspberry Pi Pico', 100, 4.13, 'Small/Mini', 'Raspberry Pi Pico is a tiny, fast, and versatile board built using RP2040, a brand new microcontroller chip designed by Raspberry Pi in the UK.', 'https://thepihut.com/products/raspberry-pi-pico'),
(5, 'Raspberry Pi Zero', 100, 5.51, 'Small/Mini', 'The Raspberry Pi Zero is half the size of a Model A+, with twice the utility. A tiny Raspberry Pi that’s affordable enough for any project!', 'https://thepihut.com/products/raspberry-pi-zero'),
(6, 'Raspberry Pi Zero W', 100, 10.73, 'Small/Mini', 'The Raspberry Pi Zero W extends the Pi Zero family and comes with added wireless LAN and Bluetooth connectivity.', 'https://thepihut.com/products/raspberry-pi-zero-w');

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_Users`
--

CREATE TABLE `RasPiRetail_Users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `ccNo` varchar(20) DEFAULT NULL UNIQUE
) ;

--
-- Dumping data for table `RasPiRetail_Users`
--

INSERT INTO `RasPiRetail_Users` (`id`, `username`, `password`, `userType`, `firstName`, `lastName`, `ccNo`) VALUES
(1, 'admin', 'password1', 'Admin', 'Administrator', 'Admin', NULL),
(2, 'cindrmon', 'password1', 'Customer', 'Cindr', 'Mon', '378282246310005'),
(3, 'monter.acindo', 'asdfghjkl', 'Customer', 'Acindo', 'Monter', '371449635398431'),
(4, 'meow-kun', 'OriMonterAllisys314@!~', 'Customer', 'Meow', 'Meow', '378734493671000'),
(5, 'oberon1198', 'Password*1198', 'Customer', 'Rick', 'Oberon', '6331101999990016'),
(6, 'chi-kun', 'ChiChiIsAwesome!', 'Customer', 'Chi', 'Akindo', '5610591081018250'),
(7, 'solus111', 'Password1234!', 'Customer', 'Sol', 'Mergot', '30569309025904'),
(8, 'raznar0k', 'qwertyuiop', 'Customer', 'Razzy', 'Oxen', '6011111111111117'),
(9, 'scoville3257', 'passwordissocomplicated', 'Customer', 'Richard', 'Scoville', '4222222222222');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `RasPiRetail_CustomerCarts`
--
ALTER TABLE `RasPiRetail_CustomerCarts`
  ADD PRIMARY KEY (`customerID`,`productID`),
  ADD KEY `productID` (`productID`);

--
-- Indexes for table `RasPiRetail_Products`
--
ALTER TABLE `RasPiRetail_Products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `RasPiRetail_Users`
--
ALTER TABLE `RasPiRetail_Users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `RasPiRetail_Products`
--
ALTER TABLE `RasPiRetail_Products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `RasPiRetail_Users`
--
ALTER TABLE `RasPiRetail_Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `RasPiRetail_CustomerCarts`
--
ALTER TABLE `RasPiRetail_CustomerCarts`
  ADD CONSTRAINT `RasPiRetail_CustomerCarts_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `RasPiRetail_Users` (`id`),
  ADD CONSTRAINT `RasPiRetail_CustomerCarts_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `RasPiRetail_Products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
