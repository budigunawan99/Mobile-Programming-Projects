-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: toko
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `smartphone`
--

DROP TABLE IF EXISTS `smartphone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smartphone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smartphone`
--

LOCK TABLES `smartphone` WRITE;
/*!40000 ALTER TABLE `smartphone` DISABLE KEYS */;
INSERT INTO `smartphone` VALUES (1,'Smartphone','SAMSUNG Galaxy S7 Edge 128GB - Black Pearl','Exynos 8890 Octa core (2.3GHz Quad + 1.6Hz Quad), 128GB Storage, Camera 12MP+5MP, Dual SIM, Battery 3600mAh, Dust Proof and Water Resistant, 5.5 inch, 4GB RAM, Gorilla Glass 4, LTE, NFC, Android OS v6.0 Marshmallow','Rp. 11.499.000','s7-edge-black-pearl.jpg'),(2,'Smartphone','SAMSUNG Galaxy S7 Edge - Gold Platinum','Exynos 8890 Octa core (2.3GHz Quad + 1.6Hz Quad), 32GB Storage, Camera 12MP+5MP, Dual SIM, Battery 3600mAh, Dust Proof and Water Resistant, 5.5 inch, 4GB RAM, Gorilla Glass 4, LTE, NFC, Android OS v6.0 Marshmallow','Rp. 10.499.000','s7-edge-gold-platinum.jpg'),(3,'Smartphone','SAMSUNG Galaxy Note 5 - Gold ','Exynos 7420 Octa core (2.1GHz Quad + 1.5GHz Quad), 32GB Storage, Camera 16MP+5MP, LTE, WiFi, Android OS 5.1 Lollipop, 5.7 inch, 4GB RAM, S Pen, NFC, Bluetooth v4.2','Rp. 7.959.596','note-5-gold.jpg'),(4,'Smartphone','SAMSUNG Galaxy A7 - Black Sky','Octa Core 1.9Ghz, 32GB Storage, Camera 16MP+16MP, LTE, Bluetooth, 600 mAh, 5.7 inch, 3GB RAM, Dual Sim, Wifi, FingerScanner, Android OS v6.0 Marshmallow','Rp. 5.899.000','a7-black-sky.jpg'),(5,'Smartphone','SAMSUNG Galaxy A5 - Black Sky','Octa Core 1.9Ghz, 32GB Storage, Camera 16MP+16MP, LTE, Bluetooth, 3000 mAh, 5.2 inch, 3GB RAM, Dual Sim, Wifi, FingerScanner, Android OS v6.0 Marshmallow','Rp. 4.899.000','a5-black-sky.jpg'),(6,'Smartphone','SAMSUNG Galaxy J7 - White','Octa Core 1.5GHz, 16GB Storage, Camera 13MP+5MP, Wifi, Battery 3000mAh, 5.5 Inch, 1.5GB RAM, Dual Sim, Bluetooth, Android OS 5.1 Lollipop','Rp. 3.999.000','j7-white.jpg'),(7,'Smartphone','APPLE iPhone 7 Plus 256GB - Jet Black','Apple A10 Quad-core 2.34 GHz, 256GB Storage, Camera 12MP+7MP, Battery 2900 mAh, iOS 10, 5.5 inch, 3GB RAM, Fingerprint, LTE','Rp. 17.689.000','iphone7-jetblack.jpg'),(8,'Smartphone','APPLE iPhone 7 256GB - Gold','Apple A10 Quad-core 2.34 GHz, 256GB Storage, Camera 12MP+7MP, Battery 1960 mAh, iOS 10, 4.7 inch, 2GB RAM, Fingerprint, LTE','Rp. 15.589.000','iphone7-gold.jpg'),(9,'Smartphone','SONY Xperia Z5 Premium - Chrome','Octa Core (Quad-core 1.5 GHz Cortex-A53 and Quad-core 2 GHz Cortex-A57), GPU Adreno 430, 32GB Stiorage, Camera 23MP+5.1MP, LTE, WiFi, MHL 3, Android OS v5.1.1 Lollipop, 5.5 inch, 3GB RAM, Dual Sim, NFC, Bluetooth, Battery 3430mAh','Rp. 11.799.000','xperia-z5-premium-chrome.jpg'),(10,'Smartphone','LG V20 - Titan','Qualcomm Snapdragon 820 Quad-core, 4GB RAM, Battery 3200mAh, WiFi, NFC, 5.7 inch, 64GB Storage, Camera 16MP+5MP+8MP, 4G LTE, Bluetooth, Android OS 7.0 Nougat','Rp. 8.839.000','v20-titan.jpg'),(11,'Smartphone','ASUS ZenFone 3 Deluxe - Shimmer Gold','Qualcomm MSM8996 Snapdragon 820 Quad-Core 2.15GHz, 64GB Storage, 23MP & 8MP Camera, LTE, Android 6.0, 5.7 Inch, 6GB RAM, NFC, Corning Gorilla Glass 4','Rp. 8.478.145','zenfone-3-deluxe-shimmer-gold.jpg'),(12,'Smartphone','SONY Xperia Z5 Compact - White','Octa Core (Quad-core 1.5 GHz Cortex-A53 and Quad-core 2 GHz Cortex-A57), GPU Adreno 430, 32GB Storage, Camera 23MP+5MP, LTE, Bluetooth, Battery 2700mAh, 4.6 inch, 2GB RAM, Wifi, NFC, Android OS v5.1.1 Lollipop','Rp. 7.999.000','xperia-z5-compact-white.jpg'),(13,'Smartphone','LG Optimus G Pro 2 - Black','Qualcomm MSM8974 Snapdragon 800 Quad-core 2.26 GHz Krait 400, 5.9-inch True IPS+ LCD capacitive touchscreen, LTE, 13 MP + 2.1 MP Camera, 16GB Internal, Wifi, Bluetooth, Android 4.4 Kitkat','Rp. 7.499.000','lg-g-pro-2-black.jpg'),(14,'Smartphone','LG G4 - Metallic Silver','Qualcomm Snapdragon 808 processor with X10 LTE, 1.8GHz 64-bit Hexa-Core, 5.5 inch, 3GB RAM, Battery 3000mAh, LTE, Bluetooth, 32GB Storage, Camera 16MP+8MP, Dual Sim, Android OS 5.1 Lollipop','Rp. 7.250.000','lg-g4-metalic-silver.jpg'),(15,'Smartphone','NOKIA Lumia 1020 - Black','Qualcomm Snapdragon Dual-core 1.5 GHz, 4.5 inch AMOLED Capacitive touchscreen, LTE, 41MP + 1.2MP Camera, 32GB Internal, WiFi, Windows Phone 8','Rp. 6.999.000','nokia-lumia-1020-black.jpg'),(16,'Smartphone','OPPO F3 Plus - Gold','Qualcomm Snapdragon 653 Octa-core 1.95GHz, 64GB Storage, Camera 16MP+16MP, LTE, Fingerprint, Android OS v6.0.1 Marshmallow, 6.0 inch, 4GB RAM, Dual Sim, Battery 4000mAh, Corning Gorilla Glass 5','Rp. 6.499.000','oppo-f3-plus-gold.jpg'),(17,'Smartphone','OPPO F1 Plus - Gold','MediaTek MT6755 Octa core 2.0 GHz, 64GB Storage, Camera 16MP+13MP, Bluetooth, Dual Sim, Battery 2850mAh, Android OS v5.1 Lollipop, 5.5 inch, 4GB RAM, Wify, LTE, Gorilla Glass 4','Rp. 5.499.000','oppo-f1-plus.jpg'),(18,'Tablet','SAMSUNG Galaxy NotePro - White','Octa Core Processor (1.9GHz Quad + 1.3GHz Quad), 3GB RAM, Dual-band, Wi-Fi hotspot, microUSB v2.0, 12.2 inch WQXGA TFT LCD capacitive touchscreen, 32 GB storage, Bluetooth, 8MP + 2MP Camera, Android 4.4 KitKat','Rp. 7.725.000','galaxy-note-pro-white.jpg'),(19,'Tablet','SAMSUNG Galaxy Tab S2 - White','Octa Core (Quad Core 1.9 GHz and Quad Core 1.3 GHz), Qualcomm MSM 8976, 8.0 inch sAMOLED, 3GB RAM, Battery 4000 mAh, 32GB Storage, Camera 8MP+2.1MP, Android OS v6.0 Marshmallow','Rp. 5.999.000','galaxy-tab-s2-white.jpg'),(20,'Tablet','HP Pro Tablet 408 G1','Intel Atom Quad-Core Processor Z3736F, 64GB storage, Camera 8MP+2MP, Intel HD Graphics, Windows OS 8.1 Pro32, 8.0 inch, 2GB RAM, Wifi only, Battery 4800mAh','Rp. 5.500.000','hp-pro-tablet-408-g1.jpg'),(21,'Tablet','SAMSUNG Galaxy Tab A 10.1 with S Pen - White','1.6GHz Octa Core, 3GB RAM, Bluetooth, 10.1 inch WUXGA, 7300 mAh Battery, 16GB Storage, Wifi, 8MP + 2 MP Camera, LTE, Android OS v6.0','Rp. 4.799.000','galaxy-tab-a.jpg'),(22,'Tablet','APPLE iPad Pro Wi-Fi 128GB - Gold','Apple Chip A9X Dual-core 2.2 GHz, 128GB Storage, Camera 8MP+1.2MP, Bluetooth, 12.9 inch, 4GB RAM, Wifi Only, iOS 9','Rp. 14.958.000','apple_128gb_ipad_pro_wi_fi.jpg'),(23,'Tablet','APPLE iPad Air 2 Wifi - Grey','Triple-core 1.5 GHz Apple A8X, 7 inch, 2GB RAM, Wifi + Cell, Bluetooth, 128GB Storage, Camera 8M+1.2MP, LTE, iOS 8','Rp. 13.499.000','ipad-air-gray-wifi.jpg');
/*!40000 ALTER TABLE `smartphone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-22 10:25:08