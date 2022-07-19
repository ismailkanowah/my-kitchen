-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2022 at 12:33 AM
-- Server version: 10.5.9-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `my_kitchen`
--

-- --------------------------------------------------------

--
-- Table structure for table `ingredient`
--

CREATE TABLE `ingredient` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ingredient`
--

INSERT INTO `ingredient` (`id`, `name`, `description`) VALUES
(1, 'egg', 'dizef'),
(2, 'Milk', 'Dilai'),
(3, 'Salt', 'Disel');

-- --------------------------------------------------------

--
-- Table structure for table `recipe`
--

CREATE TABLE `recipe` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `taste` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recipe`
--

INSERT INTO `recipe` (`id`, `name`, `description`, `taste`, `type`) VALUES
(1, 'Moussaka', 'Moussaka is an eggplant- or potato-based dish, often including ground meat, which is common in the Balkans and the Middle East, with many local and regional variations.', 1, 2),
(2, 'Rasgulla', 'Rasgulla, also known in Rasagola, Rosogola or Rosogolla, is a syrupy dessert popular in the Indian subcontinent and regions with South Asian diaspora.', 0, 2),
(3, 'Rasmalai', 'Ras malai, rasamalai, or rossomalai, or is a dessert originating from the eastern regions of the Indian subcontinent. ', 0, 1),
(4, 'Kokokrunch', 'Kokokrunch is a chocolate-flavored whole-grain breakfast cereal distributed by Nestlé in most of Europe, Asia, the Middle-East and Latin America. ', 0, 0),
(5, 'Omelette', 'In cuisine, an omelette is a dish made from beaten eggs, fried with butter or oil in a frying pan.', 1, 0),
(6, 'Biryani', 'Biryani is a mixed rice dish originating among the royal khansamas of the durbar of Old Delhi, under the Mughal Empire, during the late 16th century of the then Mughal Court.', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `recipe_ingredient`
--

CREATE TABLE `recipe_ingredient` (
  `id` int(11) NOT NULL,
  `recipeId` int(11) NOT NULL,
  `ingredientId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recipe_ingredient`
--

INSERT INTO `recipe_ingredient` (`id`, `recipeId`, `ingredientId`) VALUES
(1, 5, 1),
(2, 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `step`
--

CREATE TABLE `step` (
  `id` int(11) NOT NULL,
  `recipeId` int(11) NOT NULL,
  `stepOrder` int(11) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `step`
--

INSERT INTO `step` (`id`, `recipeId`, `stepOrder`, `content`) VALUES
(1, 5, 1, 'Search for 3 big eggs'),
(2, 5, 2, 'Break the 3 big eggs'),
(3, 5, 3, 'Mix with a pinch of salt'),
(4, 5, 4, 'Pour a tsp of oil in a pan and heat'),
(5, 5, 5, 'Pour the mixed egg into the pan'),
(6, 5, 6, 'Turn egg around when no more liquid'),
(7, 5, 7, 'Remove egg from pan after 30 seconds and put on a plate'),
(8, 5, 8, 'Eat and enjoy ! ☺');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `step`
--
ALTER TABLE `step`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `step`
--
ALTER TABLE `step`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
