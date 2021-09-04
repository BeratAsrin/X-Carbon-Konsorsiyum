-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 04 Eyl 2021, 23:50:40
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

--
-- Tablo döküm verisi `certificates`
--

INSERT INTO `certificates` (`tuple_start_id`, `tuple_finish_id`, `owner_id`, `number_of_certificates`, `register_month`, `register_year`, `expiration_month`, `expiration_year`) VALUES
(1, 140, 1, 140, 9, 2021, 9, 2022),
(141, 190, 3, 50, 9, 2021, 9, 2022),
(191, 198, 2, 8, 9, 2021, 9, 2022),
(199, 200, 1, 2, 9, 2021, 9, 2022),
(201, 1000, 4, 800, 9, 2021, 9, 2022),
(1001, 1190, 2, 190, 9, 2021, 9, 2022),
(1191, 1200, 1, 10, 9, 2021, 9, 2022);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `companies`
--

CREATE TABLE `companies` (
  `id` int(11) NOT NULL,
  `organization_name` text NOT NULL,
  `tax_number` text NOT NULL,
  `mail` text NOT NULL,
  `register_type` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `companies`
--

INSERT INTO `companies` (`id`, `organization_name`, `tax_number`, `mail`, `register_type`, `password`) VALUES
(1, 'company', '456', 'deneme@gmail.com', 'Producer', '123'),
(2, 'deneme aş2', '111111', 'deneme@gmail.com', 'Producer', '12345'),
(3, 'company', '456', 'deneme@gmail.com', 'Producer', 'afAGSDG'),
(4, 'company', '456', 'deneme@gmail.com', 'Producer', '3423'),
(5, 'companydenemeee', '456', 'deneme@gmail.com', 'Producer', '123');

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
(1, 1, 1000),
(2, 2, 1000),
(3, 3, 1000),
(4, 4, 1000),
(5, 5, 1000);

--
-- Dökümü yapılmış tablolar için indeksler
--

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `fake_bank`
--
ALTER TABLE `fake_bank`
  MODIFY `bank_id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
