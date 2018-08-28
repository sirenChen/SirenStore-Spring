-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: SirenStore
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `cid` int(32) NOT NULL,
  `cname` varchar(23) DEFAULT NULL,
  `cdesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Books','Gorky said “Books are the ladder of human progress”. More read, less talk. More code, less bug.'),(2,'Computers','A good computer may not make your code better, but may make you more comfortable while coding.'),(3,'Toys','Boys never grow up, just their toys get bigger.');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitem` (
  `ooid` varchar(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ooid`),
  KEY `oid` (`oid`),
  KEY `pid` (`pid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `total` double DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `receiverAdd` varchar(1024) DEFAULT NULL,
  `receiverName` varchar(20) DEFAULT NULL,
  `receiverPhone` varchar(20) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `pid` varchar(32) NOT NULL,
  `pname` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `pimage` varchar(200) DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `pdesc` varchar(1000) DEFAULT NULL,
  `pflag` int(11) DEFAULT NULL,
  `isHot` int(11) DEFAULT NULL,
  `cid` int(32) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `cid` (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('63F7CA0FE4034AD2897F1367F2FB1EC5','DJI Mavic Pro Drone',1349,'/img/63F7CA0FE4034AD2897F1367F2FB1EC5.jpg','2017-02-03','Wherever You Go The DJI Mavic Pro is a small yet powerful drone that turns the sky into your creative canvass easily and without worry, helping you make every moment an aerial moment. Its compact size hides a high degree of complexity that makes it one of DJI’s most sophisticated flying cameras ever',1,1,3),('49F9F7CE30FC473B9C8E6AC793D50B8F','Thinking in Java',53,'/img/49F9F7CE30FC473B9C8E6AC793D50B8F.jpg','2017-02-03','Wherever You Go The DJI Mavic Pro is a small yet powerful drone that turns the sky into your creative canvass easily and without worry, helping you make every moment an aerial moment. Its compact size hides a high degree of complexity that makes it one of DJI’s most sophisticated flying cameras ever',1,1,1),('B83BCA00507E459ABED769626EF85931','Java Network Programming',58.75,'/img/B83BCA00507E459ABED769626EF85931.jpg','2017-02-03','This practical guide provides a complete introduction to developing network programs with Java. You’ll learn how to use Java’s network class library to quickly and easily accomplish common networking tasks such as writing multithreaded servers, encrypting communications, broadcasting to the local network, and posting data to server-side programs.',1,0,1),('0206423FDE994C16B84A5E223BCF8C92','Core Java',54.05,'/img/0206423FDE994C16B84A5E223BCF8C92.jpg','2017-02-03','Designed for serious programmers, this reliable, unbiased, no-nonsense tutorial illuminates key Java language and library features with thoroughly tested code examples. As in previous editions, all code is easy to understand, reflects modern best practices, and is specifically designed to help jumpstart your projects.',1,0,1),('F386225D1AE84315AB1F50BC4247F735','Macbook Pro 13-Inch',1780,'/img/F386225D1AE84315AB1F50BC4247F735.jpg','2017-02-03','Designed to reduce power consumption while maintaining high performance. With over 5 million pixels, everything is visible down to the finest detail in images and text',1,1,2),('5704BE91F2B54E2387584F92FA4A77DA','Professional JavaScript for Web Developers',37.71,'/img/5704BE91F2B54E2387584F92FA4A77DA.jpg','2017-02-03','If you want to achieve JavaScript\'s full potential, it is critical to understand its nature, history, and limitations. To that end, this updated version of the bestseller by veteran author and JavaScript guru Nicholas C. Zakas covers JavaScript from its very beginning to the present-day incarnations including the DOM, Ajax, and HTML5.',1,1,1),('59FD730254314034968E2CAEC0304EA9','Java Concurrency in Practice',48.86,'/img/59FD730254314034968E2CAEC0304EA9.jpg','2017-02-03','This is the book you need if you\'re writing--or designing, or debugging, or maintaining, or contemplating--multithreaded Java programs. If you\'ve ever had to synchronize a method and you weren\'t sure why, you owe it to yourself and your users to read this book, cover to cover.',1,0,1),('8715A45FC9BD41698399F426B9F4591A','Java NIO: Regular Expressions and High-Performance I/O',45.74,'/img/8715A45FC9BD41698399F426B9F4591A.jpg','2017-02-03','The NIO package includes many things that have been missing from previous editions of Java that are critical to writing high-performance, large-scale applications: improvements in the areas of buffer management, scalable network and file I/O, character-set support, and regular expression matching. ',1,0,1),('0CE9B5FB8D394F55B1460CD49BD79228','Head First Design Patterns',53.71,'/img/0CE9B5FB8D394F55B1460CD49BD79228.jpg','2017-02-03','By the time you finish this book, you’ll be able to take advantage of the best design practices and experiences of those who have fought the beast of software design and triumphed. It is an intelligent and well thought-out discussion of Java design patterns, and if you don\'t know what a design pattern is then this is an excellent way to find out. It is also an interested discussion of object-oriented design.',1,0,1),('7EE186286DAA460AB6DFA620FE85C703','Spring in Action',52.96,'/img/7EE186286DAA460AB6DFA620FE85C703.jpg','2017-02-03','Spring in Action, Fourth Edition is a hands-on guide to the Spring Framework, updated for version 4. It covers the latest features, tools, and practices including Spring MVC, REST, Security, Web Flow, and more.',1,1,1),('8020C1BD4FAC4C7887A121A30869113A','Dell XPS 15-inch',2000,'/img/8020C1BD4FAC4C7887A121A30869113A.jpg','2017-02-03','Sleek and well built, the XPS 15 is Dell’s answer to the 15-inch MacBook Pro. Top-end processing power and dedicated graphics come together to produce what could be the ultimate Windows 10 laptop.',1,1,2),('DC65340F00F3483A954B4E5B2CF698BA','Dell U2415',415,'/img/DC65340F00F3483A954B4E5B2CF698BA.jpg','2017-02-03','The U2415’s 1,920-by-1,200-pixel resolution gives it 11 percent more screen space than a 1080p monitor, and its ultra-thin bezel makes its screen feel bigger and look better than monitors with thicker bezels. Its stand lifts, tilts, pivots, rotates, and swivels, so you can put the screen exactly where you need it.',1,0,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `activeCode` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `registerTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-01 20:00:35
