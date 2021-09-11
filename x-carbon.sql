-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 11 Eyl 2021, 23:50:22
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `x-carbon`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `admins`
--

CREATE TABLE `admins` (
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `admins`
--

INSERT INTO `admins` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `certificates`
--

CREATE TABLE `certificates` (
  `tuple_start_id` bigint(20) NOT NULL,
  `tuple_finish_id` bigint(20) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `number_of_certificates` int(11) NOT NULL,
  `register_month` int(11) NOT NULL,
  `register_year` int(11) NOT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `companies`
--

CREATE TABLE `companies` (
  `id` int(11) NOT NULL,
  `organization_name` text NOT NULL,
  `tax_number` bigint(20) NOT NULL,
  `mail` text NOT NULL,
  `register_type` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `companies`
--

INSERT INTO `companies` (`id`, `organization_name`, `tax_number`, `mail`, `register_type`, `password`) VALUES
(4, 'sagsad', 11111111114, 'sadgasd@asdgasd', 'Producer', 'asdgsa'),
(5, 'sdagass', 11111111132, 'asgfas@gasmf.com', 'Trader', 'sdagas'),
(6, 'sadgsagsdagsda', 114241, 'asdgasdg@asdgsad', 'Producer', 'sadgsdag'),
(7, 'sdagasdgasd', 142421124, 'asdlfjsdajkgl@asdgjnsadjk', 'Producer', 'sadgjasgksdja'),
(8, 'sdgsdlajgnasdkljg', 214412, 'sadgnsdlagjn@asgjasndjg', 'Producer', 'sdgkmsadl'),
(9, 'sadgasdgsdagljngnksdj', 124412421, 'sadgsad@asdgsda', 'Producer', 'asldkgnslda'),
(10, 'dsgljnsdgajklsngkjsan', 1142412521, 'sdlgjnsgdajl@asdkgljsndajk', 'Producer', ' sasdgdsag'),
(11, 'sdgsadasgljksdngl', 2142141, 'asdlgsad@sdagsad', 'Producer', 'asdgasd'),
(12, 'sdgasdgasdg', 14214, '@', 'Trader', 'asdgsad'),
(13, 'sadasdgdsg', 14112412, 'asdgasdgds@asgs', 'Producer', 'sdfgasdgs'),
(14, 'sadgşksdngklsadnlk', 41412141, 'sldgkasdklgs@xn--sslhkasdlkgasd-4zc', 'Producer', 'asdgksnda'),
(15, 'denemekeee', 121242141, 'denemeekee@gmail.com', 'Producer', 'gslknsgkgknşsa');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `fake_bank`
--

CREATE TABLE `fake_bank` (
  `bank_id` bigint(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `money` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `fake_bank`
--

INSERT INTO `fake_bank` (`bank_id`, `owner_id`, `money`) VALUES
(4, 4, 1000),
(5, 5, 1000),
(6, 6, 1000),
(7, 7, 1000),
(8, 8, 1000),
(9, 9, 1000),
(10, 10, 1000),
(11, 11, 1000),
(12, 12, 1000),
(13, 13, 1000),
(14, 14, 1000),
(15, 15, 1000);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `admins`
--
ALTER TABLE `admins`
  ADD UNIQUE KEY `username` (`username`) USING HASH;

--
-- Tablo için indeksler `certificates`
--
ALTER TABLE `certificates`
  ADD PRIMARY KEY (`tuple_start_id`,`tuple_finish_id`);

--
-- Tablo için indeksler `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `fake_bank`
--
ALTER TABLE `fake_bank`
  ADD PRIMARY KEY (`bank_id`),
  ADD UNIQUE KEY `owner_id` (`owner_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `fake_bank`
--
ALTER TABLE `fake_bank`
  MODIFY `bank_id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
