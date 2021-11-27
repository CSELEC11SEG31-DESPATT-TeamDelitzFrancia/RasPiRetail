-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Nov 26, 2021 at 12:19 PM
-- Server version: 10.4.21-MariaDB-1:10.4.21+maria~focal
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `despatt`
--

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_Admins`
--

CREATE TABLE `RasPiRetail_Admins` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_Admins`
--

INSERT INTO `RasPiRetail_Admins` (`id`, `username`, `password`) VALUES
(1, 'admin', 'KQHBx2DzwJpxycLIkMRjgQ==');

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_CustomerPackages`
--

CREATE TABLE `RasPiRetail_CustomerPackages` (
  `packageID` int(11) NOT NULL,
  `packageType` varchar(50) NOT NULL,
  `customerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_CustomerPackages`
--

INSERT INTO `RasPiRetail_CustomerPackages` (`packageID`, `packageType`, `customerID`) VALUES
(1, 'Standard Electrostatic', 1),
(2, 'Standard Electrostatic', 2);

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_Customers`
--

CREATE TABLE `RasPiRetail_Customers` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `addressLine1` text NOT NULL,
  `addressLine2` text DEFAULT NULL,
  `ccNo` varchar(20) NOT NULL,
  `accountCreationTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `accountUpdatedTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_Customers`
--

INSERT INTO `RasPiRetail_Customers` (`id`, `username`, `email`, `password`, `firstName`, `lastName`, `addressLine1`, `addressLine2`, `ccNo`, `accountCreationTime`, `accountUpdatedTime`) VALUES
(1, 'cindrmon', 'me@cindrmon.blue', 'kXvTEhw/zeg8zUCYugGu0Q==', 'Cindr', 'Mon', 'Something, Xyz St.', 'Somewhere', '378282246310005', '2021-11-01 09:20:20', '0000-00-00 00:00:00'),
(2, 'monter.acindo', 'monter.acindo@cindrmon.blue', 'ic2niUVlkPxh7Fm9Fk+rbQ==', 'Acindo', 'Monter', 'Somewhere Else, Abc St.', NULL, '371449635398431', '2021-11-01 09:20:20', '0000-00-00 00:00:00'),
(3, 'oberon1198', 'dingo-oberon@protonmail.ch', 'a77rskMNm32xb/4BK1SIBQ==', 'Rick', 'Oberon', 'There St. From Somewhere', NULL, '6331101999990016', '2021-11-01 09:20:20', '0000-00-00 00:00:00'),
(4, 'chi-kun', 'mochi-kun@kawaiimail.moe', '2i6XogHoTcOQ0QNZYoyWPw==', 'Chi', 'Akindo', 'Kawaii St.', 'alongside Moe Ave.', '5610591081018250', '2021-11-01 09:20:20', '0000-00-00 00:00:00'),
(5, 'starman', 'starman@nasa.com', 'twy22NWLvDhjyMtMTkplMRZhmuJlOX99RLtyfzMQS7o=', 'David', 'Bowie', 'America St.', NULL, '4929721825469423', '2021-11-01 09:20:20', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Stand-in structure for view `RasPiRetail_CustomersCart`
-- (See below for the actual view)
--
CREATE TABLE `RasPiRetail_CustomersCart` (
`customerID` int(11)
,`productID` int(11)
,`productName` varchar(50)
,`quantity` int(11)
,`subtotal` double
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `RasPiRetail_CustomersPackages`
-- (See below for the actual view)
--
CREATE TABLE `RasPiRetail_CustomersPackages` (
`packageID` int(11)
,`customerID` int(11)
,`packageType` varchar(50)
,`totalQty` decimal(32,0)
,`grandTotal` double
);

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_GuestCustomerCarts`
--

CREATE TABLE `RasPiRetail_GuestCustomerCarts` (
  `guestID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT current_timestamp(),
  `dateUpdated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_GuestCustomerCarts`
--

INSERT INTO `RasPiRetail_GuestCustomerCarts` (`guestID`, `productID`, `quantity`, `dateCreated`, `dateUpdated`) VALUES
(1, 1, 1, '2021-11-01 09:34:44', '2021-11-01 09:34:44'),
(1, 5, 5, '2021-11-01 09:34:44', '2021-11-01 09:34:44'),
(2, 2, 2, '2021-11-01 09:34:44', '2021-11-01 09:34:44'),
(2, 4, 3, '2021-11-01 09:34:44', '2021-11-01 09:34:44');

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_GuestPackages`
--

CREATE TABLE `RasPiRetail_GuestPackages` (
  `packageID` int(11) NOT NULL,
  `packageType` varchar(50) NOT NULL,
  `guestID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_GuestPackages`
--

INSERT INTO `RasPiRetail_GuestPackages` (`packageID`, `packageType`, `guestID`) VALUES
(1, 'Standard Electrostatic', 1);

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_Guests`
--

CREATE TABLE `RasPiRetail_Guests` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `addressLine1` text NOT NULL,
  `addressLine2` text DEFAULT NULL,
  `ccNo` varchar(20) NOT NULL,
  `guestAccountCreationTime` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_Guests`
--

INSERT INTO `RasPiRetail_Guests` (`id`, `email`, `firstName`, `lastName`, `addressLine1`, `addressLine2`, `ccNo`, `guestAccountCreationTime`) VALUES
(1, 'email@example.com', 'Example', 'DotCom', 'Example St.', 'Suburbia', '4035300539804083', '2021-11-01 09:33:04'),
(2, 'mr.real@realemail.com', 'Real', 'Mann', 'Totally Real St.', NULL, '4450787876620583', '2021-11-01 09:33:04');

-- --------------------------------------------------------

--
-- Stand-in structure for view `RasPiRetail_GuestsCart`
-- (See below for the actual view)
--
CREATE TABLE `RasPiRetail_GuestsCart` (
`guestID` int(11)
,`productID` int(11)
,`productName` varchar(50)
,`quantity` int(11)
,`subtotal` double
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `RasPiRetail_GuestsPackages`
-- (See below for the actual view)
--
CREATE TABLE `RasPiRetail_GuestsPackages` (
`packageID` int(11)
,`guestID` int(11)
,`packageType` varchar(50)
,`totalQty` decimal(32,0)
,`grandTotal` double
);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_Products`
--

INSERT INTO `RasPiRetail_Products` (`id`, `name`, `stock`, `price`, `type`, `description`, `url`) VALUES
(1, 'Raspberry Pi 3 Model A+', 100, 26.84, 'Mainline', '1.4GHz 64-bit quad-core processor, dual-band wireless LAN, Bluetooth 4.2/BLE in the same mechanical format as the Raspberry Pi 1 Model A+', 'https://thepihut.com/products/raspberry-pi-3-model-a-plus'),
(2, 'Raspberry Pi 4 Model B', 100, 62.32, 'Mainline', 'People have worked hard and long to develop the next generation of Raspberry Pis, and they present you the RPi4, the first of its kind to have 8GB Memory.', 'https://thepihut.com/products/raspberry-pi-4-model-b'),
(3, 'Raspberry Pi 400', 100, 77.21, 'Mainline', 'Featuring a quad-core 64-bit processor, 4GB of RAM, wireless networking, dual-display output, and 4K video playback, as well as a 40-pin GPIO header, Raspberry Pi 400 is a powerful, easy-to-use computer built into a neat and portable keyboard.', 'https://thepihut.com/products/raspberry-pi-400'),
(4, 'Raspberry Pi Pico', 100, 4.13, 'Micro', 'Raspberry Pi Pico is a tiny, fast, and versatile board built using RP2040, a brand new microcontroller chip designed by Raspberry Pi in the UK.', 'https://thepihut.com/products/raspberry-pi-pico'),
(5, 'Raspberry Pi Zero', 100, 5.51, 'Micro', 'The Raspberry Pi Zero is half the size of a Model A+, with twice the utility. A tiny Raspberry Pi that?s affordable enough for any project!', 'https://thepihut.com/products/raspberry-pi-zero'),
(6, 'Raspberry Pi Zero W', 100, 10.73, 'Micro', 'The Raspberry Pi Zero W extends the Pi Zero family and comes with added wireless LAN and Bluetooth connectivity.', 'https://thepihut.com/products/raspberry-pi-zero-w');

-- --------------------------------------------------------

--
-- Table structure for table `RasPiRetail_RegisteredCustomerCarts`
--

CREATE TABLE `RasPiRetail_RegisteredCustomerCarts` (
  `customerID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT current_timestamp(),
  `dateUpdated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RasPiRetail_RegisteredCustomerCarts`
--

INSERT INTO `RasPiRetail_RegisteredCustomerCarts` (`customerID`, `productID`, `quantity`, `dateCreated`, `dateUpdated`) VALUES
(1, 2, 2, '2021-11-01 09:28:38', '2021-11-01 09:28:38'),
(2, 2, 1, '2021-11-01 09:28:38', '2021-11-01 09:28:38'),
(2, 4, 5, '2021-11-01 09:28:38', '2021-11-01 09:28:38'),
(3, 3, 1, '2021-11-01 09:28:38', '2021-11-01 09:28:38'),
(4, 1, 1, '2021-11-01 09:28:38', '2021-11-01 09:28:38'),
(4, 6, 5, '2021-11-01 09:28:38', '2021-11-01 09:28:38'),
(5, 2, 1, '2021-11-01 09:28:38', '2021-11-01 09:28:38'),
(5, 6, 7, '2021-11-01 09:28:38', '2021-11-01 09:28:38');

-- --------------------------------------------------------

--
-- Structure for view `RasPiRetail_CustomersCart`
--
DROP TABLE IF EXISTS `RasPiRetail_CustomersCart`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `RasPiRetail_CustomersCart`  AS SELECT `ca`.`customerID` AS `customerID`, `ca`.`productID` AS `productID`, `rpi`.`name` AS `productName`, `ca`.`quantity` AS `quantity`, `rpi`.`price`* `ca`.`quantity` AS `subtotal` FROM ((`RasPiRetail_RegisteredCustomerCarts` `ca` join `RasPiRetail_Products` `rpi` on(`ca`.`productID` = `rpi`.`id`)) join `RasPiRetail_Customers` `cu` on(`ca`.`customerID` = `cu`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `RasPiRetail_CustomersPackages`
--
DROP TABLE IF EXISTS `RasPiRetail_CustomersPackages`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `RasPiRetail_CustomersPackages`  AS SELECT `cp`.`packageID` AS `packageID`, `cp`.`customerID` AS `customerID`, `cp`.`packageType` AS `packageType`, sum(`cc`.`quantity`) AS `totalQty`, sum(`cc`.`subtotal`) AS `grandTotal` FROM (`RasPiRetail_CustomerPackages` `cp` left join `RasPiRetail_CustomersCart` `cc` on(`cc`.`customerID` = `cp`.`customerID`)) GROUP BY `cp`.`packageID`, `cc`.`customerID` ;

-- --------------------------------------------------------

--
-- Structure for view `RasPiRetail_GuestsCart`
--
DROP TABLE IF EXISTS `RasPiRetail_GuestsCart`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `RasPiRetail_GuestsCart`  AS SELECT `ca`.`guestID` AS `guestID`, `ca`.`productID` AS `productID`, `rpi`.`name` AS `productName`, `ca`.`quantity` AS `quantity`, `rpi`.`price`* `ca`.`quantity` AS `subtotal` FROM ((`RasPiRetail_GuestCustomerCarts` `ca` join `RasPiRetail_Products` `rpi` on(`ca`.`productID` = `rpi`.`id`)) join `RasPiRetail_Guests` `gu` on(`ca`.`guestID` = `gu`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `RasPiRetail_GuestsPackages`
--
DROP TABLE IF EXISTS `RasPiRetail_GuestsPackages`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `RasPiRetail_GuestsPackages`  AS SELECT `gp`.`packageID` AS `packageID`, `gp`.`guestID` AS `guestID`, `gp`.`packageType` AS `packageType`, sum(`gc`.`quantity`) AS `totalQty`, sum(`gc`.`subtotal`) AS `grandTotal` FROM (`RasPiRetail_GuestPackages` `gp` left join `RasPiRetail_GuestsCart` `gc` on(`gc`.`guestID` = `gp`.`guestID`)) GROUP BY `gp`.`packageID`, `gc`.`guestID` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `RasPiRetail_Admins`
--
ALTER TABLE `RasPiRetail_Admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `RasPiRetail_CustomerPackages`
--
ALTER TABLE `RasPiRetail_CustomerPackages`
  ADD PRIMARY KEY (`packageID`),
  ADD KEY `customerID` (`customerID`);

--
-- Indexes for table `RasPiRetail_Customers`
--
ALTER TABLE `RasPiRetail_Customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `RasPiRetail_GuestCustomerCarts`
--
ALTER TABLE `RasPiRetail_GuestCustomerCarts`
  ADD PRIMARY KEY (`guestID`,`productID`),
  ADD KEY `RasPiRetail_GuestCustomerCarts_ibfk_2` (`productID`);

--
-- Indexes for table `RasPiRetail_GuestPackages`
--
ALTER TABLE `RasPiRetail_GuestPackages`
  ADD PRIMARY KEY (`packageID`),
  ADD KEY `guestID` (`guestID`);

--
-- Indexes for table `RasPiRetail_Guests`
--
ALTER TABLE `RasPiRetail_Guests`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `RasPiRetail_Products`
--
ALTER TABLE `RasPiRetail_Products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `RasPiRetail_RegisteredCustomerCarts`
--
ALTER TABLE `RasPiRetail_RegisteredCustomerCarts`
  ADD PRIMARY KEY (`customerID`,`productID`),
  ADD KEY `RasPiRetail_RegisteredCustomerCarts_ibfk_2` (`productID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `RasPiRetail_Admins`
--
ALTER TABLE `RasPiRetail_Admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `RasPiRetail_CustomerPackages`
--
ALTER TABLE `RasPiRetail_CustomerPackages`
  MODIFY `packageID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `RasPiRetail_Customers`
--
ALTER TABLE `RasPiRetail_Customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `RasPiRetail_GuestPackages`
--
ALTER TABLE `RasPiRetail_GuestPackages`
  MODIFY `packageID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `RasPiRetail_Guests`
--
ALTER TABLE `RasPiRetail_Guests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `RasPiRetail_Products`
--
ALTER TABLE `RasPiRetail_Products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `RasPiRetail_CustomerPackages`
--
ALTER TABLE `RasPiRetail_CustomerPackages`
  ADD CONSTRAINT `RasPiRetail_CustomerPackages_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `RasPiRetail_RegisteredCustomerCarts` (`customerID`);

--
-- Constraints for table `RasPiRetail_GuestCustomerCarts`
--
ALTER TABLE `RasPiRetail_GuestCustomerCarts`
  ADD CONSTRAINT `RasPiRetail_GuestCustomerCarts_ibfk_1` FOREIGN KEY (`guestID`) REFERENCES `RasPiRetail_Guests` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `RasPiRetail_GuestCustomerCarts_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `RasPiRetail_Products` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `RasPiRetail_GuestPackages`
--
ALTER TABLE `RasPiRetail_GuestPackages`
  ADD CONSTRAINT `RasPiRetail_GuestPackages_ibfk_1` FOREIGN KEY (`guestID`) REFERENCES `RasPiRetail_GuestCustomerCarts` (`guestID`);

--
-- Constraints for table `RasPiRetail_RegisteredCustomerCarts`
--
ALTER TABLE `RasPiRetail_RegisteredCustomerCarts`
  ADD CONSTRAINT `RasPiRetail_RegisteredCustomerCarts_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `RasPiRetail_Customers` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `RasPiRetail_RegisteredCustomerCarts_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `RasPiRetail_Products` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
