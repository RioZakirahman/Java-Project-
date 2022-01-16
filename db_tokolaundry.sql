-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2022 at 05:21 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tokolaundry`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CustomerID` int(11) NOT NULL,
  `Customer_Name` varchar(255) NOT NULL,
  `No_telp` char(12) NOT NULL,
  `Address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustomerID`, `Customer_Name`, `No_telp`, `Address`) VALUES
(3, 'Ragil ', '081908826101', 'Dirumah');

-- --------------------------------------------------------

--
-- Table structure for table `order_tbl`
--

CREATE TABLE `order_tbl` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `TransactionID` int(11) NOT NULL,
  `Package_TypeID` int(11) NOT NULL,
  `staffID` int(11) NOT NULL,
  `Order_Date` date NOT NULL,
  `Return_Date` date NOT NULL,
  `Weight` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_tbl`
--

INSERT INTO `order_tbl` (`OrderID`, `CustomerID`, `TransactionID`, `Package_TypeID`, `staffID`, `Order_Date`, `Return_Date`, `Weight`, `Status`) VALUES
(3, 3, 3, 2, 1, '2022-01-07', '2022-01-08', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `packagetype`
--

CREATE TABLE `packagetype` (
  `Package_TypeID` int(11) NOT NULL,
  `Package_TypeName` varchar(255) NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `packagetype`
--

INSERT INTO `packagetype` (`Package_TypeID`, `Package_TypeName`, `Price`) VALUES
(1, 'Paket Satu', 12000),
(2, 'Paket Dua', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL,
  `PaymentTypeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PaymentID`, `PaymentTypeID`) VALUES
(3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `paymenttype`
--

CREATE TABLE `paymenttype` (
  `PaymentTypeID` int(11) NOT NULL,
  `PaymentTypeName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paymenttype`
--

INSERT INTO `paymenttype` (`PaymentTypeID`, `PaymentTypeName`) VALUES
(1, 'OVO'),
(2, 'DANA'),
(3, 'CASH');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffID` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffID`, `email`, `password`, `role`) VALUES
(1, 'kasir@gmail.com', 'kasir', 2),
(2, 'kasir2@gmail.com', 'kasir2', 2);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `TransactionID` int(11) NOT NULL,
  `PaymentID` int(11) NOT NULL,
  `Total_Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`TransactionID`, `PaymentID`, `Total_Price`) VALUES
(3, 3, 40000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `order_tbl`
--
ALTER TABLE `order_tbl`
  ADD PRIMARY KEY (`OrderID`),
  ADD KEY `fk+staffID` (`staffID`),
  ADD KEY `fk_customerID` (`CustomerID`),
  ADD KEY `fk_packageTypeID` (`Package_TypeID`),
  ADD KEY `fk_transactionID` (`TransactionID`);

--
-- Indexes for table `packagetype`
--
ALTER TABLE `packagetype`
  ADD PRIMARY KEY (`Package_TypeID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`),
  ADD KEY `fk_paymentTypeID` (`PaymentTypeID`);

--
-- Indexes for table `paymenttype`
--
ALTER TABLE `paymenttype`
  ADD PRIMARY KEY (`PaymentTypeID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `fk_paymentID` (`PaymentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `order_tbl`
--
ALTER TABLE `order_tbl`
  MODIFY `OrderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `packagetype`
--
ALTER TABLE `packagetype`
  MODIFY `Package_TypeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `paymenttype`
--
ALTER TABLE `paymenttype`
  MODIFY `PaymentTypeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staffID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `TransactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_tbl`
--
ALTER TABLE `order_tbl`
  ADD CONSTRAINT `fk+staffID` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_customerID` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_packageTypeID` FOREIGN KEY (`Package_TypeID`) REFERENCES `packagetype` (`Package_TypeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_transactionID` FOREIGN KEY (`TransactionID`) REFERENCES `transaction` (`TransactionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_paymentTypeID` FOREIGN KEY (`PaymentTypeID`) REFERENCES `paymenttype` (`PaymentTypeID`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_paymentID` FOREIGN KEY (`PaymentID`) REFERENCES `payment` (`PaymentID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
