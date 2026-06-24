/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.29 : Database - lms_portal1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lms_portal1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `lms_portal1`;

/*Table structure for table `assignment` */

DROP TABLE IF EXISTS `assignment`;

CREATE TABLE `assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `lesson_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKitkm2jqcnrvh6i8ytlem7bsck` (`lesson_id`),
  CONSTRAINT `FKitkm2jqcnrvh6i8ytlem7bsck` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `assignment` */

insert  into `assignment`(`id`,`description`,`due_date`,`title`,`lesson_id`) values 
(1,'Submit a PDF explaining OOP Concepts','2025-12-01','Java OOP Assignment',1),
(2,'use Html, css ','2025-12-13','Complete Structure of webpage',4),
(3,'Submit a PDF explaining C Concepts','2025-12-01','C++ Assignment',1),
(4,'Submit a PDF explaining C# Concepts','2025-12-01','C# Assignment',1),
(5,'Submit a PDF explaining C# Concepts','2025-12-01','C# Assignment',4),
(6,'Submit a PDF explaining Java Adv Concepts','2025-12-01','Java Advanced Assignment',9),
(7,'Submit a PDF explaining Java Spring Concepts','2026-01-12','Java Spring Assignment',6),
(8,'Submit a PDF explaining Java Data JPA Concepts','2026-01-12','Java Data JPA Assignment',11),
(10,'Submit a PDF explaining Backend Concepts','2026-01-12','Backend Assignment',9),
(11,'Submit a PDF explaining Node Concepts','2026-01-12','Node Assignment',13),
(12,'explain j2ee concepts','2026-01-02','complete j2ee assignment',14),
(13,'All hooks concepts','2026-01-20','explain hooks concept in react',15);

/*Table structure for table `assignment_submission` */

DROP TABLE IF EXISTS `assignment_submission`;

CREATE TABLE `assignment_submission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `feedback` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `submitted_at` datetime(6) DEFAULT NULL,
  `assignment_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_assignment_student` (`assignment_id`,`student_id`),
  KEY `FKqsi9w45eary4louu2eds5a1r7` (`student_id`),
  CONSTRAINT `FKi9tdkyaqlb4j7qm7y2k74jd7o` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKqsi9w45eary4louu2eds5a1r7` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1404 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `assignment_submission` */

insert  into `assignment_submission`(`id`,`feedback`,`file_path`,`grade`,`submitted_at`,`assignment_id`,`student_id`) values 
(1,NULL,'1764843499017_invoice (1).pdf',95,'2025-12-04 15:48:19.024729',1,9),
(52,NULL,'1764936549970_invoice (1).pdf',NULL,'2025-12-05 17:39:09.974705',1,10),
(53,NULL,'1764936583694_invoice (1).pdf',95,'2025-12-05 17:39:43.696454',1,6),
(103,NULL,'1766382341903_withot retu andwith argu.cpp',NULL,'2025-12-22 11:15:41.906453',2,16),
(104,NULL,'1765427801852_invoice (1).pdf',NULL,'2025-12-11 10:06:41.855011',2,5),
(154,NULL,'1765966680230_invoice (1).pdf',NULL,'2025-12-17 15:48:00.235108',2,17),
(203,NULL,'1767075186747_sqlSequenceFile.txt',NULL,'2025-12-30 11:43:06.751129',1,16),
(252,NULL,'1765969334409_certificate_course_3.pdf',NULL,'2025-12-17 16:32:14.414757',1,4),
(253,NULL,'1765778300949_Lms_Portal.txt',NULL,'2025-12-15 11:28:20.950206',2,4),
(254,NULL,'1765778317894_Lms_Portal.txt',NULL,'2025-12-15 11:28:37.896799',3,4),
(256,NULL,'1765778697295_Lms_Portal.txt',NULL,'2025-12-15 11:34:57.296956',5,4),
(303,NULL,'1766382352794_with return type and withargu.cpp',NULL,'2025-12-22 11:15:52.797671',5,16),
(404,NULL,'1765802663160_updatedPonits.txt',NULL,'2025-12-15 18:14:23.164712',1,18),
(753,NULL,'1765967274780_updatedPonits.txt',NULL,'2025-12-17 15:57:54.786198',5,9),
(755,NULL,'1765956417007_updatedPonits.txt',NULL,'2025-12-17 12:56:57.008833',4,4),
(757,NULL,'1765956523284_Lms_Portal.txt',NULL,'2025-12-17 12:58:43.286549',7,4),
(1004,NULL,'1765965073333_Lms_Portal.txt',NULL,'2025-12-17 15:21:13.335064',6,4),
(1005,NULL,'1765969582716_Lms_Portal.txt',NULL,'2025-12-17 16:36:22.719979',10,4),
(1102,NULL,'1767075200623_Lms_Portal.txt',NULL,'2025-12-30 11:43:20.629187',3,16),
(1103,NULL,'1767075215850_updatedPonits.txt',NULL,'2025-12-30 11:43:35.856812',4,16),
(1104,NULL,'1767075236194_Lms_Portal.txt',NULL,'2025-12-30 11:43:56.198118',7,16),
(1105,NULL,'1767074557229_InstructDashboard.txt',NULL,'2025-12-30 11:32:37.299804',8,16),
(1106,NULL,'1766047427736_withot retu andwith argu.exe',NULL,'2025-12-18 14:13:47.741778',12,20),
(1152,NULL,'1766126797974_with return type and withargu.cpp',NULL,'2025-12-19 12:16:37.977187',13,16),
(1202,NULL,'1767084216048_Lms_Portal.txt',NULL,'2025-12-30 14:13:36.052451',6,16),
(1203,NULL,'1766382496377_vowel or con if else.exe',NULL,'2025-12-22 11:18:16.379830',10,16),
(1204,NULL,'1766483812450_with return type and withargu.exe',NULL,'2025-12-23 15:26:52.455248',11,16),
(1205,NULL,'1766395993395_vowel or con if else.exe',NULL,'2025-12-22 15:03:13.399532',12,16),
(1252,NULL,'1766562485601_withot retu andwith argu.exe',NULL,'2025-12-24 13:18:05.611945',11,22),
(1253,NULL,'1766562581147_with return type and withargu.cpp',NULL,'2025-12-24 13:19:41.150729',6,22),
(1254,NULL,'1766562591631_vowel or con if else.exe',NULL,'2025-12-24 13:19:51.634496',10,22),
(1352,NULL,'1767087620716_certificate_course_3.pdf',NULL,'2025-12-30 15:10:20.728330',13,9),
(1402,NULL,'1767327002700_EnrollmentUpdate.txt',NULL,'2026-01-02 09:40:02.720228',6,28),
(1403,NULL,'1767327015100_RejectRoleType.txt',NULL,'2026-01-02 09:40:15.104190',10,28);

/*Table structure for table `assignment_submission_seq` */

DROP TABLE IF EXISTS `assignment_submission_seq`;

CREATE TABLE `assignment_submission_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `assignment_submission_seq` */

insert  into `assignment_submission_seq`(`next_val`) values 
(1501);

/*Table structure for table `certificate` */

DROP TABLE IF EXISTS `certificate`;

CREATE TABLE `certificate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `certificate_code` varchar(255) NOT NULL,
  `issue_date` datetime(6) DEFAULT NULL,
  `status` enum('ISSUED','REVOKED') DEFAULT NULL,
  `course_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKi0j02j91y0g2ygon6xvpqaeyl` (`certificate_code`),
  KEY `FK13t46rtyipt6ayvme7crsdvs4` (`course_id`),
  KEY `FKbuiuwkgfo5yed0yvv1ec24gv0` (`student_id`),
  CONSTRAINT `FK13t46rtyipt6ayvme7crsdvs4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKbuiuwkgfo5yed0yvv1ec24gv0` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `certificate` */

insert  into `certificate`(`id`,`certificate_code`,`issue_date`,`status`,`course_id`,`student_id`) values 
(1,'1982cb10-6343-4c31-a167-605c8fca6678','2025-12-15 11:35:57.336156','ISSUED',1,4),
(2,'1b0c4da1-1a9c-453d-82a2-0c35cb44a8d2','2025-12-17 15:30:16.060392','ISSUED',3,4),
(3,'86cb6b3c-f6a0-42ad-882f-29e4983e6c83','2025-12-18 13:30:38.946421','ISSUED',1,16),
(4,'962d4ca8-ec36-4ccb-adf7-285a5b4690dc','2025-12-18 14:33:50.242180','ISSUED',6,20),
(5,'5d2ccdea-f46b-4c90-b34a-2e46bac37010','2025-12-19 12:16:45.263702','ISSUED',7,16),
(6,'8fda4071-9db7-4b65-8e96-a220a2812fbe','2025-12-22 14:34:06.345414','ISSUED',3,16),
(7,'13ac34c9-a97d-410e-84b6-cf54f036d88e','2025-12-23 15:27:54.805364','ISSUED',6,16),
(8,'d6011f2d-796c-403d-849c-f7f38516eb0a','2025-12-24 13:19:56.446195','ISSUED',3,22),
(10,'30f81edc-0640-4c49-85bb-ddebca97f951','2025-12-30 15:10:25.851372','ISSUED',7,9);

/*Table structure for table `chatbot_faq` */

DROP TABLE IF EXISTS `chatbot_faq`;

CREATE TABLE `chatbot_faq` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chatbot_faq` */

/*Table structure for table `course_categories` */

DROP TABLE IF EXISTS `course_categories`;

CREATE TABLE `course_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKnxksnnnkvgo1vca6ffhyfrpux` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `course_categories` */

insert  into `course_categories`(`id`,`description`,`name`) values 
(1,'Updated description','Programming Updated'),
(2,'All AI related courses','Artificial Intelligence'),
(3,'All React related courses','React Developer'),
(4,'Used for Testing','Testing'),
(5,'full','pyton');

/*Table structure for table `course_module` */

DROP TABLE IF EXISTS `course_module`;

CREATE TABLE `course_module` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoxnv7ml9seonn275kmgq5sxy4` (`course_id`),
  CONSTRAINT `FKoxnv7ml9seonn275kmgq5sxy4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `course_module` */

insert  into `course_module`(`id`,`description`,`title`,`course_id`) values 
(1,'Basics, JVM, JDK, Syntax','Introduction to Java',1),
(2,'Frontend','React',1),
(5,'Basics, JVM, JDK, Syntax','Introduction to JDK',3),
(6,'Basics, Node, JDK, Syntax','Introduction to Node',5),
(7,'Basics, j2ee, java, Syntax','Introduction to j2ee',6),
(8,'full react','React ',7);

/*Table structure for table `courses` */

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(2000) DEFAULT NULL,
  `instructor_id` bigint DEFAULT NULL,
  `level` enum('ADVANCED','BEGINNER','INTERMEDIATE') NOT NULL DEFAULT 'BEGINNER',
  `price` double DEFAULT NULL,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlrqjut8xtbtgs6h7uosddyut7` (`category_id`),
  CONSTRAINT `FKlrqjut8xtbtgs6h7uosddyut7` FOREIGN KEY (`category_id`) REFERENCES `course_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `courses` */

insert  into `courses`(`id`,`description`,`instructor_id`,`level`,`price`,`published`,`title`,`category_id`) values 
(1,'Updated content',NULL,'INTERMEDIATE',3599,1,'Java Fullstack Advanced',1),
(3,'Complete Java Backend + node',NULL,'INTERMEDIATE',3000,1,'Java Stack',2),
(5,'Complete Java Backend + node',NULL,'BEGINNER',3999,0,'Java Advanced',1),
(6,'j2ee',NULL,'INTERMEDIATE',12222,0,'j2ee',1),
(7,'java full stack',NULL,'INTERMEDIATE',9999,0,'Web development',1),
(8,'stack java  course',NULL,'ADVANCED',8999,0,'Software development',3),
(9,'full Ai  relatedcourse',NULL,'ADVANCED',5999,0,'Robotics and Automation',2),
(10,'Ai related course',NULL,'ADVANCED',9999,0,'Data science',2),
(11,'testing',NULL,'INTERMEDIATE',5999,0,'learn selenium',4),
(12,'full course',NULL,'ADVANCED',6999,1,'Api testing',4),
(13,'full course',NULL,'ADVANCED',8999,0,'quality Assurance and  manual testing',4),
(14,'full react course',NULL,'ADVANCED',5999,0,'Frontend developer',3),
(15,'full course',NULL,'ADVANCED',7999,0,'React developer',3),
(16,'full course',NULL,'INTERMEDIATE',5999,0,'Python developer',1);

/*Table structure for table `discussion` */

DROP TABLE IF EXISTS `discussion`;

CREATE TABLE `discussion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqw6oyllv86nlaonnwy6b5hos3` (`course_id`),
  KEY `FK4l2updie132u6i1b6npfah6d7` (`student_id`),
  CONSTRAINT `FK4l2updie132u6i1b6npfah6d7` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqw6oyllv86nlaonnwy6b5hos3` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `discussion` */

insert  into `discussion`(`id`,`question`,`course_id`,`student_id`) values 
(1,'What is difference between JVM and JRE?',1,5),
(2,'java defination',1,16),
(3,'What is difference between JVM and JRE?',1,7),
(4,'What is difference between JVM and JRE?',1,5),
(5,'update new videos',1,16);

/*Table structure for table `enrollments` */

DROP TABLE IF EXISTS `enrollments`;

CREATE TABLE `enrollments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_paid` double DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `enrolled_at` datetime(6) DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `progress_percentage` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2lha5vwilci2yi3vu5akusx4a` (`student_id`),
  KEY `FKho8mcicp4196ebpltdn9wl6co` (`course_id`),
  CONSTRAINT `FK2lha5vwilci2yi3vu5akusx4a` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKho8mcicp4196ebpltdn9wl6co` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `enrollments` */

insert  into `enrollments`(`id`,`amount_paid`,`course_id`,`enrolled_at`,`student_id`,`progress_percentage`) values 
(1,599,1,'2025-12-01 11:59:42.654370',4,0),
(3,1,1,'2025-12-10 05:51:43.137708',16,100),
(4,12,3,'2025-12-15 09:43:37.386621',4,11),
(5,500,6,'2025-12-18 07:18:53.076990',20,50),
(6,0,3,'2025-12-18 10:35:48.112492',16,0),
(7,0,1,'2025-12-18 10:35:52.836116',16,0),
(8,0,1,'2025-12-18 10:36:05.623297',16,0),
(9,0,5,'2025-12-18 10:36:18.583592',16,0),
(10,0,1,'2025-12-18 10:46:49.291372',16,0),
(11,0,1,'2025-12-18 12:06:42.723057',16,0),
(12,0,1,'2025-12-19 04:04:20.842911',16,0),
(13,0,7,'2025-12-19 06:46:07.695299',16,0),
(14,0,6,'2025-12-22 05:32:10.519272',16,0),
(15,0,3,'2025-12-22 05:47:38.455351',16,0),
(16,0,3,'2025-12-22 08:35:38.370058',16,0),
(17,0,5,'2025-12-22 08:36:34.319222',16,0),
(18,0,3,'2025-12-22 09:01:08.633915',16,0),
(19,0,5,'2025-12-22 09:02:05.734295',16,0),
(20,0,8,'2025-12-22 09:31:38.874227',16,0),
(21,5999,1,'2025-12-22 10:07:41.478087',20,50),
(22,5999,1,'2025-12-22 11:48:41.504080',16,50),
(23,5999,1,'2025-12-22 12:01:28.735120',16,40),
(24,5999,9,'2025-12-22 12:05:41.945441',20,50),
(25,3599,1,'2025-12-22 12:14:53.975168',20,50),
(26,8999,8,'2025-12-22 12:15:12.548115',20,50),
(27,3999,5,'2025-12-22 12:15:35.015675',20,30),
(28,3000,3,'2025-12-22 12:15:59.659811',20,30),
(29,3599,1,'2025-12-22 12:32:49.833536',20,40),
(30,3599,1,'2025-12-23 04:55:44.183076',16,40),
(31,3000,3,'2025-12-23 04:56:43.973545',16,50),
(32,3999,5,'2025-12-23 06:01:46.953881',16,30),
(33,3999,5,'2025-12-23 09:56:16.352751',16,50),
(34,3599,1,'2025-12-24 07:36:58.420904',22,0),
(35,3999,5,'2025-12-24 07:47:23.052360',22,0),
(36,3000,3,'2025-12-24 07:49:05.299331',22,0),
(37,5999,9,'2025-12-26 05:33:48.828732',4,0),
(38,5999,9,'2025-12-26 05:33:48.879285',4,0),
(39,599,9,'2025-12-27 11:18:06.287658',16,0),
(41,5999,16,'2025-12-28 14:13:37.574760',16,0),
(42,3999,5,'2025-12-28 14:36:49.458716',16,0),
(43,5999,16,'2025-12-28 14:49:04.776513',16,0),
(44,8999,13,'2025-12-28 15:07:43.761171',16,0),
(45,7999,15,'2025-12-30 04:53:26.240363',16,0),
(46,7999,15,'2025-12-30 04:53:26.240363',16,0),
(47,1000,14,'2025-12-30 04:55:13.839671',25,50),
(48,9999,10,'2025-12-30 09:36:39.560230',9,0),
(49,9999,10,'2025-12-30 09:36:39.560230',9,0),
(50,9999,7,'2025-12-30 09:39:17.332518',9,0),
(51,9999,10,'2025-12-30 10:43:32.450375',16,0),
(52,9999,10,'2025-12-30 10:43:32.653859',16,0),
(53,5999,14,'2025-12-30 11:12:29.112469',16,0),
(54,5999,14,'2025-12-30 11:12:29.114468',16,0),
(55,3000,3,'2026-01-02 04:09:26.343393',28,0),
(56,3000,3,'2026-01-02 04:09:26.343393',28,0);

/*Table structure for table `lesson` */

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `module_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  KEY `FKimy8d7m2mtfspqjldlvr1sw99` (`module_id`),
  CONSTRAINT `FKimy8d7m2mtfspqjldlvr1sw99` FOREIGN KEY (`module_id`) REFERENCES `course_module` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lesson` */

insert  into `lesson`(`id`,`content`,`title`,`module_id`) values 
(1,'Variables, Data Types, Operators','Java Basics',1),
(4,'Sturcture of webpage','HTML',2),
(6,'methods','java',1),
(9,'Variables, Data Types, Operators','Java Advanced',5),
(11,'Variables, Data Types, Operators','Java Spring',2),
(13,'Variables, Data Types, Operators','Node Structure',6),
(14,'j2ee advance concept','java j2ee',7),
(15,'all states, function , classes','introduction of React',8);

/*Table structure for table `notifications` */

DROP TABLE IF EXISTS `notifications`;

CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `message` varchar(2000) DEFAULT NULL,
  `target_role` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `send_to` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `notifications` */

insert  into `notifications`(`id`,`created_at`,`message`,`target_role`,`title`,`send_to`) values 
(1,'2025-11-26 12:49:36.212097','LMS will be offline tonight from 12 AM to 1 AM',NULL,'Server Maintenance','ALL'),
(2,'2025-11-26 12:52:00.044654','LMS will be offline tonight from 12 AM to 1 AM',NULL,'Server Maintenance','ALL'),
(3,'2025-11-27 04:08:58.690213','LMS will be offline tonight from 12 AM to 1 AM',NULL,'Server Maintenance','ALL'),
(4,'2025-11-27 04:41:36.340200','LMS will be offline tonight from 12 AM to 1 AM',NULL,'Server Maintenance',NULL),
(5,'2025-11-27 04:42:52.244174','LMS will be offline tonight from 12 AM to 1 AM',NULL,'Server Maintenance',NULL),
(6,'2025-11-27 04:44:17.065630','LMS will be online tonight from 12 AM to 1 AM',NULL,'Server Maintenance','STUDENT'),
(7,'2025-11-27 04:45:25.245630','LMS will be offline tonight from 12 AM to 1 AM',NULL,'Server Maintenance',NULL),
(8,'2025-11-27 04:45:42.661996','LMS will be offline tonight from 12 AM to 1 AM',NULL,'Server Maintenance','ALL'),
(9,'2025-12-09 11:18:00.746729','weekend holiday',NULL,'Holiday',NULL),
(10,'2025-12-15 08:58:39.596367','hello',NULL,'good afternoon','STUDENT'),
(11,'2025-12-18 06:48:06.127860','welcome to all',NULL,'welcome ','STUDENT'),
(12,'2025-12-22 08:35:06.085916','check enrolled course ',NULL,'welcome','STUDENT');

/*Table structure for table `quiz` */

DROP TABLE IF EXISTS `quiz`;

CREATE TABLE `quiz` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answer` varchar(255) DEFAULT NULL,
  `optiona` varchar(255) DEFAULT NULL,
  `optionb` varchar(255) DEFAULT NULL,
  `optionc` varchar(255) DEFAULT NULL,
  `optiond` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `lesson_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi10d7n4lf738sm3mon21aubik` (`lesson_id`),
  CONSTRAINT `FKi10d7n4lf738sm3mon21aubik` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `quiz` */

insert  into `quiz`(`id`,`correct_answer`,`optiona`,`optionb`,`optionc`,`optiond`,`question`,`lesson_id`) values 
(1,'A','Java Virtual Machine','Java Volume Manager','Joint Virtual Manager','Java Vendor Machine','What is JVM?',1),
(2,'A','Hyper text markup language','high language','Higher marketing language','all of them','HTML full form',4),
(3,'B','Java Virtual Machine','Java Development Kit','Joint Development kit','Java Developer kit','What is JDK?',4),
(4,'A','Cascading StyleShit','com style shit','come stay style','All of the above','What is css?',6),
(5,'D','Air India','Artifact Intel','Airtel Intelligence','Artificial Intelligence','What is AI?',9),
(6,'D','Container of data','Block of memory','Used to store Data','All of the above','What is Variable?',11),
(8,'A','create singlepage App','Frontend style','Backend','Node','What is React?',13),
(9,'A','json web token','java web token','java web config token','none of the above','what is JWT',14),
(10,'D','usestate','useeffect','navigation','all of the above','explain hooks in react',15);

/*Table structure for table `quiz_result` */

DROP TABLE IF EXISTS `quiz_result`;

CREATE TABLE `quiz_result` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answers` int NOT NULL,
  `lesson_id` bigint NOT NULL,
  `score_percentage` double NOT NULL,
  `student_id` bigint NOT NULL,
  `total_questions` int NOT NULL,
  `passed` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_quizresult_lesson` (`lesson_id`),
  KEY `fk_quizresult_student` (`student_id`),
  CONSTRAINT `fk_quizresult_lesson` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_quizresult_student` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=703 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `quiz_result` */

insert  into `quiz_result`(`id`,`correct_answers`,`lesson_id`,`score_percentage`,`student_id`,`total_questions`,`passed`) values 
(1,0,1,0,9,1,0),
(2,1,1,100,9,1,0),
(3,1,6,100,16,1,0),
(4,1,4,50,16,2,0),
(5,2,4,100,16,2,0),
(6,1,11,100,16,1,0),
(7,1,14,100,20,1,0),
(8,1,14,100,20,1,0),
(9,1,1,100,16,1,0),
(52,1,1,100,9,1,0),
(53,1,1,100,4,1,0),
(54,1,1,100,4,1,0),
(55,0,11,0,16,1,0),
(56,1,1,100,16,1,0),
(57,1,6,100,16,1,0),
(58,2,4,100,16,2,0),
(59,1,11,100,16,1,0),
(60,1,1,100,16,1,0),
(61,1,6,100,16,1,0),
(62,1,1,100,16,1,0),
(63,1,15,100,16,1,0),
(102,1,1,100,16,1,0),
(103,1,1,100,16,1,0),
(104,1,9,100,16,1,0),
(105,1,9,100,16,1,0),
(106,1,9,100,4,1,0),
(107,1,1,100,4,1,0),
(108,1,9,100,4,1,0),
(152,1,1,100,4,1,0),
(153,1,6,100,16,1,0),
(154,1,1,100,16,1,0),
(155,2,4,100,16,2,0),
(156,1,11,100,16,1,0),
(157,1,11,100,16,1,0),
(158,2,4,100,16,2,0),
(159,1,1,100,16,1,0),
(160,1,9,100,16,1,0),
(161,0,13,0,16,1,0),
(162,0,13,0,16,1,0),
(163,1,13,100,16,1,0),
(164,1,13,100,16,1,0),
(165,0,13,0,16,1,0),
(166,1,13,100,16,1,0),
(167,1,13,100,16,1,0),
(168,1,14,100,16,1,0),
(169,1,1,100,16,1,0),
(202,1,6,100,4,1,0),
(203,2,4,100,4,2,0),
(204,1,13,100,16,1,0),
(205,0,13,0,16,1,0),
(206,1,13,100,16,1,0),
(207,1,13,100,16,1,0),
(208,1,13,100,16,1,0),
(209,1,13,100,16,1,0),
(252,1,9,100,4,1,0),
(253,0,13,0,22,1,0),
(254,0,13,0,22,1,0),
(255,1,9,100,22,1,0),
(302,1,9,100,4,1,0),
(352,1,9,100,4,1,0),
(353,1,1,100,4,1,0),
(354,1,1,100,4,1,0),
(355,1,9,100,4,1,0),
(356,1,13,100,4,1,0),
(359,1,13,100,4,1,0),
(452,1,1,100,16,1,0),
(453,1,6,100,16,1,0),
(454,2,4,100,16,2,0),
(455,1,11,100,16,1,0),
(456,1,11,100,16,1,0),
(457,2,4,100,16,2,0),
(458,1,1,100,16,1,0),
(459,1,6,100,16,1,0),
(460,2,4,100,16,2,0),
(461,1,11,100,16,1,0),
(462,1,1,100,16,1,0),
(463,1,6,100,16,1,0),
(464,2,4,100,16,2,0),
(465,1,11,100,16,1,0),
(466,1,1,100,16,1,0),
(467,1,6,100,16,1,0),
(468,2,4,100,16,2,0),
(469,1,11,100,16,1,0),
(470,1,1,100,16,1,0),
(471,1,6,100,16,1,0),
(472,2,4,100,16,2,0),
(473,1,11,100,16,1,0),
(474,1,1,100,16,1,0),
(475,1,6,100,16,1,0),
(476,1,1,100,16,1,0),
(477,1,6,100,16,1,0),
(478,2,4,100,16,2,0),
(479,1,11,100,16,1,0),
(502,1,1,100,16,1,1),
(503,1,6,100,16,1,1),
(504,2,4,100,16,2,1),
(505,1,11,100,16,1,1),
(506,1,1,100,16,1,1),
(507,1,6,100,16,1,1),
(508,2,4,100,16,2,1),
(509,1,11,100,16,1,1),
(552,1,9,100,16,1,1),
(602,1,9,100,16,1,1),
(652,1,15,100,9,1,1),
(702,1,9,100,28,1,1);

/*Table structure for table `quiz_result_seq` */

DROP TABLE IF EXISTS `quiz_result_seq`;

CREATE TABLE `quiz_result_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `quiz_result_seq` */

insert  into `quiz_result_seq`(`next_val`) values 
(801),
(801);

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `discussion_id` bigint DEFAULT NULL,
  `instructor_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjlsdwpqsfbrcuuhpgeo69rvtg` (`discussion_id`),
  KEY `FK5d4j004bhtlbhmxnhw5l13g76` (`instructor_id`),
  CONSTRAINT `FK5d4j004bhtlbhmxnhw5l13g76` FOREIGN KEY (`instructor_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKjlsdwpqsfbrcuuhpgeo69rvtg` FOREIGN KEY (`discussion_id`) REFERENCES `discussion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `reply` */

insert  into `reply`(`id`,`message`,`discussion_id`,`instructor_id`) values 
(1,'Great question! Let me explain...',1,14),
(2,'ok',1,14),
(3,'Java is object oriented programming language',2,14),
(4,'Great question! Let me explain...',1,2),
(5,'Great question! Let me explain...',2,2),
(6,'check in course and submit in assignment',2,21),
(7,'ok',5,21),
(8,'ok',5,21),
(9,'ok correct',1,21);

/*Table structure for table `settings` */

DROP TABLE IF EXISTS `settings`;

CREATE TABLE `settings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(255) DEFAULT NULL,
  `setting_value` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `settings` */

/*Table structure for table `submission` */

DROP TABLE IF EXISTS `submission`;

CREATE TABLE `submission` (
  `id` bigint NOT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `assignment_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3q8643roa73llngo64dvpvtxt` (`assignment_id`),
  KEY `FKjlg2bff2kbwi7yof7yc7fbokn` (`student_id`),
  CONSTRAINT `FK3q8643roa73llngo64dvpvtxt` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`),
  CONSTRAINT `FKjlg2bff2kbwi7yof7yc7fbokn` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `submission` */

/*Table structure for table `submission_seq` */

DROP TABLE IF EXISTS `submission_seq`;

CREATE TABLE `submission_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `submission_seq` */

insert  into `submission_seq`(`next_val`) values 
(1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','INSTRUCTOR','STUDENT') DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`name`,`password`,`role`,`active`,`approved`) values 
(2,'rahul@instructor.com','Rahul Instructor','$2a$10$0Tohv7zVT3ZzBdRv7H5sI.V/nvzKde80LJv.AxPJ3Cg2L02NzaynW','INSTRUCTOR',0,1),
(3,'admin@lms.com','Admin User','$2a$10$D.y8xpX5hgaKGOeT2jGH1.jsPJaA2KHuDgaQeTXFA3ZCHpW/X8ph2','INSTRUCTOR',0,0),
(4,'shubh@student.com','Shubh Verma','$2a$10$0FVslY2HOc98IU5rZ0ORiOtiOxoHfP13rqTepF9Jg2qwKAiB0jvQa','STUDENT',0,0),
(5,'shubhraj@student.com','Shubhraj','$2a$10$nk/WEHw4Te.PIeddNfgK0.cW4hU7W72p/FObVpDvZSl.1V2itzenm','STUDENT',0,0),
(6,'shubhraj4@student.com','shubhraj4','$2a$10$r/MNwqHLFFLtyHY2F.TIpukRsOoyffnjuNhn9dvI8LONDXiMbghWW','INSTRUCTOR',0,1),
(7,'shubhraj5@example.com','shubhraj5','$2a$10$FwR11JAZSexsvVa/lZS86uKVPXphH4jC.IDsXTawSQtKbyu8SnFs.','INSTRUCTOR',0,0),
(8,'shubhraj6@example.com','shubhraj6','$2a$10$VFH.Cu3sanrWqBlEva.1O.InzBrLquWn0.0HKF1iIfnDIV1OfXCKy','ADMIN',0,0),
(9,'shubhraj7@gmail.com','shubhraj7','$2a$10$P2UY.mCw.9S/DVULZprshe4wuN0NyCNu3QvjqOTHhqjghv5EZFmt2','STUDENT',0,0),
(10,'shubhraj7@example.com','shubhraj7','$2a$10$VQ080G.Ak5mJugYvcuNb8OQqheZ1KfBN/gavBh6YKjZ.JwCXaYQ4q','STUDENT',0,0),
(12,'admin1@gmail.com','Admin1','$2a$10$AkaIfRkObwzXvsvo2u9Ks.noA2B1KWTRYMgFSCQFfmc/AS7ye3I6m','ADMIN',1,0),
(13,'Admin2@gmail.com','Admin2','$2a$10$kiTEgdXlKwZGN.p7BNy/B.E6IhwCRw3AcGBHjFRhf3VvY8vDx/2Nu','ADMIN',1,0),
(14,'Instructor2@gmail.com','Instructor2','$2a$10$/kiaWXDvDhkcMAqIYieYk.wWOesy7p0pnxN8XPyOBPrQ3/IHKdqjm','INSTRUCTOR',1,1),
(15,'admin3@gmail.com','Admin3','$2a$10$84WwOiVkHpS5F3yI8e272.d8x8NNmbmcCuD47RGtoNd74bJbjKoZy','ADMIN',1,0),
(16,'Student6@gmail.com','Student6','$2a$10$udxqS00P7xBKZulwAAj/7eLfukihxE.loT17KydMOlz3JEwzqV3..','STUDENT',1,0),
(17,'shubhraj2@student.com','Shubhraj2','$2a$10$MvxmBMUFuCVp2a8UCy0/R.gXl3DkW/UoEIT3yNiWDqWMZQik8vG1y','INSTRUCTOR',1,0),
(18,'shubhraj17@gmailcom','Shubhraj17','$2a$10$OhRpzlOYwVUvk9PwFN7o/uR1gmxBFhufIULYmfp2991qAI1Wia8oO','STUDENT',1,0),
(19,'instructor1@gmail.com','Instructor One','$2a$10$0Tohv7zVT3ZzBdRv7H5sI.V/nvzKde80LJv.AxPJ3Cg2L02NzaynW','INSTRUCTOR',1,1),
(20,'student2403@gmail.com','student2403','$2a$10$XiXNgA.gKjI.EG.wLk8Rj.e5NP9l5247.rJxmdyhuZ8wu20pLSjWG','STUDENT',1,0),
(21,'instructornew@gmail.com','instructor new','$2a$10$VQNOJaeOt85rOWyKoIBVYut.9cVBp0GR7aiOUkqTl9vn/v5HFrdZS','INSTRUCTOR',1,1),
(22,'ketan@gamil.com','Ketan  Taware ','$2a$10$aIM8rxeXRLX7TXl4sDKm9eusLTwFmq2cY0wDfS7XjtnryVeb/YwTu','STUDENT',1,0),
(24,'Student9@gmail.com','StudentRaj','$2a$10$2dLWRv4YRfKeefyGkdAIqeuEY1s150tKQ2CPT.AmtpyRTqFlByCDC','STUDENT',1,0),
(25,'shubhraj20@gmail.com','ShubhRaj','$2a$10$2TfErS8OPpPFhKkkAHX2Mus8S3rnedRdRSWuYvGhL3H3Vf/GPjGBO','STUDENT',1,0),
(27,'raja22@gmail.com','Raja','$2a$10$..H0Z8ibVOvbU2Uu1imuF.Fae5N0fsQsV5jK1VJoW5VxITi569dki','STUDENT',1,0),
(28,'student20@gmail.com','StudenT','$2a$10$XIzj8GKMKojx4ZZ7gbMHXu9JZ0FeDeQcaNJfRst.9FLuX6gcfnKW2','STUDENT',1,0);

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `lesson_id` bigint DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_lesson_video` (`lesson_id`),
  KEY `UKlgdmfa11t9qt2t6lshmqb9upi` (`lesson_id`),
  CONSTRAINT `FKt5q6im9i6ky6luw2l7ytxkwlm` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `video` */

insert  into `video`(`id`,`title`,`video_url`,`lesson_id`,`duration`) values 
(1,'Hibernate','/videos/97faf2a8-b10e-4dce-9dc4-1aa527ef3e09_DemonSlayer.mp4',1,540),
(5,'java demo','/videos/42e7cd79-dd74-4ae0-b344-4b9f7060020a_WhatsAppVideo.mp4',4,540),
(11,'methodss','/videos/ef6e73b1-b837-46e0-8e89-ab93190b42ca_WhatsAppVideo.mp4',6,540),
(15,'java Adv','/videos/a9ecb6b1-a476-4351-b24a-425957a3c010_WhatsAppVideo.mp4',9,540),
(17,'Hibernate','/videos/c42499fd-4c49-48c6-b5ad-e30befb7bfc8_DemonSlayer.mp4',13,540),
(18,'new','/videos/e431dbc0-68b8-4994-98f1-7ac54e6efdd7_demo video.mp4',14,540),
(19,'Hibernate','/videos/eddf106f-5624-492c-ace3-90ffbdc745b5_DemonSlayer.mp4',11,540),
(20,'react basics','/videos/132b7064-a69e-4bb1-ad32-6be8e85de0b4_demo video.mp4',15,560);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
