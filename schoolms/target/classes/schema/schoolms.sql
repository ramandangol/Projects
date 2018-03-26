-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for school_ms
CREATE DATABASE IF NOT EXISTS `school_ms` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `school_ms`;

-- Dumping structure for table school_ms.attendance
CREATE TABLE IF NOT EXISTS `attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datetime` date DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `status` enum('undefined','Present','Absent') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.attendance: ~15 rows (approximately)
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`id`, `datetime`, `student_id`, `status`) VALUES
	(1, '2018-03-14', 24, 'Present'),
	(2, '2018-03-14', 21, 'Present'),
	(3, '2018-03-14', 22, 'Absent'),
	(4, '2018-03-14', 24, 'Present'),
	(5, '2018-03-14', 21, 'Present'),
	(6, '2018-03-14', 22, 'Absent'),
	(7, '2018-03-14', 24, 'Present'),
	(8, '2018-03-14', 21, 'Present'),
	(9, '2018-03-14', 22, 'Present'),
	(10, '2018-03-14', 24, 'Present'),
	(11, '2018-03-14', 21, 'Absent'),
	(12, '2018-03-14', 22, 'Present'),
	(13, '2018-03-15', 24, 'Present'),
	(14, '2018-03-15', 21, 'Absent'),
	(15, '2018-03-15', 22, 'Present');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;

-- Dumping structure for table school_ms.course
CREATE TABLE IF NOT EXISTS `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `course_description` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `FK_teacher_ID` (`teacher_id`),
  CONSTRAINT `FK_teacher_ID` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.course: ~5 rows (approximately)
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`id`, `course_name`, `teacher_id`, `course_description`, `active`) VALUES
	(9, 'Nepali', 3, 'Subject nepali class teacher john thakuri', 1),
	(10, 'English I', 4, 'Subject english i class teacher sitadangol', 1),
	(11, 'English Grammar', 3, 'Subject english grammar class teacher john Thakuri', 1),
	(12, 'Computer', 6, 'Subject computer class teacher raman dangol', 1),
	(13, 'GK', 8, 'Course GK subject Teacher Renu Dangol', 1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;

-- Dumping structure for table school_ms.exam_date
CREATE TABLE IF NOT EXISTS `exam_date` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `exam_typeid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_examid` (`exam_typeid`),
  CONSTRAINT `FK1_examid` FOREIGN KEY (`exam_typeid`) REFERENCES `exam_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.exam_date: ~4 rows (approximately)
/*!40000 ALTER TABLE `exam_date` DISABLE KEYS */;
INSERT INTO `exam_date` (`id`, `start_date`, `exam_typeid`) VALUES
	(1, '2018-02-28', 4),
	(2, '2018-04-30', 3),
	(4, '2018-07-15', 5),
	(5, '2018-08-01', 6);
/*!40000 ALTER TABLE `exam_date` ENABLE KEYS */;

-- Dumping structure for table school_ms.exam_type
CREATE TABLE IF NOT EXISTS `exam_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.exam_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `exam_type` DISABLE KEYS */;
INSERT INTO `exam_type` (`id`, `exam_name`, `description`) VALUES
	(3, 'Mid Term', 'Mid Term Examination'),
	(4, 'First Term', 'First Term Examination'),
	(5, 'Second Term ', 'Second Term Examination'),
	(6, 'Final Term', 'Final Examination');
/*!40000 ALTER TABLE `exam_type` ENABLE KEYS */;

-- Dumping structure for table school_ms.grade
CREATE TABLE IF NOT EXISTS `grade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(100) DEFAULT NULL,
  `grade_section` enum('A','B','C','D','E') DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `grade_description` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `FK1_teacher_id` (`teacher_id`),
  CONSTRAINT `FK1_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.grade: ~3 rows (approximately)
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` (`id`, `grade_name`, `grade_section`, `teacher_id`, `grade_description`, `active`) VALUES
	(12, 'One', 'A', 4, 'Class one class teacher sita dangol', 1),
	(15, 'Five', 'A', 8, 'class five class teacher renu dangol', 1),
	(16, 'Nine', 'A', 3, '', 1);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;

-- Dumping structure for table school_ms.grade_course
CREATE TABLE IF NOT EXISTS `grade_course` (
  `grade_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  KEY `FK1_grade_id` (`grade_id`),
  KEY `FK2_course_id` (`course_id`),
  CONSTRAINT `FK1_grade_id` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `FK2_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.grade_course: ~7 rows (approximately)
/*!40000 ALTER TABLE `grade_course` DISABLE KEYS */;
INSERT INTO `grade_course` (`grade_id`, `course_id`) VALUES
	(12, 9),
	(12, 12),
	(12, 10),
	(12, 11),
	(16, 12),
	(16, 11),
	(16, 10);
/*!40000 ALTER TABLE `grade_course` ENABLE KEYS */;

-- Dumping structure for table school_ms.parents
CREATE TABLE IF NOT EXISTS `parents` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zip` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.parents: ~0 rows (approximately)
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
/*!40000 ALTER TABLE `parents` ENABLE KEYS */;

-- Dumping structure for table school_ms.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- Dumping data for table school_ms.role: ~29 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `description`) VALUES
	(17, 'Teacher_Add', 'Addition of new Teacher'),
	(18, 'Teacher_Update', 'Update an existing teacher'),
	(19, 'Teacher_Delete', 'Delete the selected Teacher'),
	(20, 'Teacher_List', 'Listing of all teacher'),
	(21, 'Course_Add', 'Addition of new Course'),
	(22, 'Course_List', 'Listing of all course'),
	(23, 'Course_Update', 'Update an existing Course'),
	(24, 'Course_Delete', 'Delete the selected Course'),
	(25, 'Grade_Add', 'Addition of new Grade'),
	(26, 'Grade_List', 'Listing of all Grade'),
	(27, 'Grade_Update', 'Update an existing Grade'),
	(28, 'Grade_Delete', 'Delete the selected Grade'),
	(29, 'Student_Add', 'Addition of new Student'),
	(30, 'Student_List', 'Listing of all Student'),
	(31, 'Student_Update', 'Update an existing Student'),
	(32, 'Student_Delete', 'Delete the selected Student'),
	(35, 'Role_Add', 'Addition of new Role'),
	(36, 'Role_List', 'Listing of all roles'),
	(37, 'Role_Update', 'Update an existing role'),
	(38, 'Role_Delete', 'Delete the selected Role'),
	(39, 'User_Add', 'Addition of new User'),
	(40, 'User_Delete', 'Delete the selected user'),
	(41, 'User_List', 'Listing of all user'),
	(42, 'User_Update', 'Update an existing user'),
	(43, 'Attendance_List', 'Listing of all student to attendance'),
	(44, 'Exam_Add', 'Addition of new Exam Type'),
	(45, 'Exam_List', 'Listing of all Exam Types'),
	(46, 'Examdate_Add', 'Addition of new Exam Date'),
	(47, 'Examdate_List', 'Listing of all exam dates');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table school_ms.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_year` enum('2074-2075','2075-2076','2076-2077','2077-2078') DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `parent_name` varchar(100) DEFAULT NULL,
  `grade_id` bigint(20) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `date_of_join` datetime DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `UKfe0i52si7ybu0wjedj6motiim` (`email`),
  KEY `FK1_gradeid` (`grade_id`),
  CONSTRAINT `FK1_gradeid` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.student: ~3 rows (approximately)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `batch_year`, `firstname`, `lastname`, `address`, `city`, `state`, `zipcode`, `gender`, `dateofbirth`, `parent_name`, `grade_id`, `phone`, `email`, `password`, `image`, `date_of_join`, `active`) VALUES
	(21, '2075-2076', 'Raman', 'Dangol', 'khokana', 'lalitpur', 'Bagmati', '44600', 'male', '2018-03-09', 'Ram Bhakta Dangol', 12, '34567', 'ramandangol07@gmail.com', NULL, NULL, '2018-03-18 22:52:56', 1),
	(22, '2075-2076', 'sneha', 'Dangol', 'khokan', 'Lalitpur', 'Bagmati', '44600', 'male', '2018-03-16', 'ertyui', 12, '434', 'aaa@gmail.com', NULL, NULL, '2018-03-18 22:53:05', 1),
	(24, '2075-2076', 'ronaldo', 'sadf', 'asdf', 'sdf', 'sdf', '4545', 'male', '2018-03-18', 'sfd', 15, '43434', 'aaaadsf@gmail.com', NULL, NULL, '2018-03-18 22:53:01', 1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- Dumping structure for table school_ms.student_history
CREATE TABLE IF NOT EXISTS `student_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) DEFAULT NULL,
  `batch_year` varchar(50) DEFAULT NULL,
  `grade_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_studentid` (`student_id`),
  CONSTRAINT `FK_studentid` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.student_history: ~0 rows (approximately)
/*!40000 ALTER TABLE `student_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_history` ENABLE KEYS */;

-- Dumping structure for table school_ms.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zipcode` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `dateofjoin` datetime DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `UK3kv6k1e64a9gylvkn3gnghc2q` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Dumping data for table school_ms.teacher: ~4 rows (approximately)
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`id`, `firstname`, `lastname`, `address`, `city`, `state`, `zipcode`, `gender`, `phone`, `email`, `password`, `dateofjoin`, `image`, `active`) VALUES
	(3, 'John', 'Thakuri', 'khokana', 'Lalitpur', 'Bagmati', '44600', 'male', '984123', 'johnthakuri@gmail.com', NULL, '2018-03-12 10:31:43', NULL, 1),
	(4, 'Sita', 'Dangol', 'lagankhel', 'Lalitpur', 'Bagmati', '44700', 'female', '987452', 'sitadangol@gmail.com', NULL, '2018-03-15 13:08:25', NULL, 1),
	(6, 'Raman', 'Dangol', 'khokana', 'Lalitpur', 'Bagmati', '44600', 'male', '9841192652', 'ramandangol07@gmail.com', 'raman123', '2018-02-18 14:51:50', NULL, 1),
	(8, 'Renu', 'Dangol', 'Khokana', 'Lalitpur', 'Bagmati', '44600', 'female', '987456622', 'renudangol@gmail.com', 'renu123', '2018-02-20 09:16:31', NULL, 1);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

-- Dumping structure for table school_ms.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(100) NOT NULL,
  `password` varchar(128) NOT NULL,
  `user_type` enum('ADMIN','MANAGER','OPERATOR') NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact_no` varchar(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 1,
  `password_token` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_id` (`email_id`),
  UNIQUE KEY `UKr9kvst217faqa7vgeyy51oos0` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table school_ms.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email_id`, `password`, `user_type`, `name`, `contact_no`, `active`, `password_token`) VALUES
	(10, 'ramandangol07@gmail.com', '$2a$12$r3wb54qv5Xc8fz3cCgfR3OalkkZMDFurTDIU6GgQ02oQ4m6Q9TRwG', 'ADMIN', 'Raman', '9841192652', 1, NULL),
	(11, 'snehadangol@gmail.com', '$2a$12$ZJJkbD3SFCfS.H4IcZepEeTE3RiI0zDIPHL.EJ4YD/1HescPhczky', 'ADMIN', 'sneha', '988711', 1, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table school_ms.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `fk_user_role_user_id` (`user_id`),
  KEY `fk_user_role_role_id` (`role_id`),
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table school_ms.user_role: ~34 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(11, 26),
	(11, 20),
	(11, 30),
	(11, 17),
	(11, 22),
	(10, 37),
	(10, 44),
	(10, 31),
	(10, 22),
	(10, 29),
	(10, 40),
	(10, 19),
	(10, 23),
	(10, 32),
	(10, 42),
	(10, 27),
	(10, 41),
	(10, 47),
	(10, 36),
	(10, 30),
	(10, 20),
	(10, 43),
	(10, 25),
	(10, 46),
	(10, 26),
	(10, 35),
	(10, 24),
	(10, 45),
	(10, 21),
	(10, 38),
	(10, 17),
	(10, 28),
	(10, 18),
	(10, 39);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
