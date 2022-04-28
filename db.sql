CREATE DATABASE IF NOT EXISTS `learntogether_ver2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `learntogether_ver2`;
-- Create table --

CREATE TABLE IF NOT EXISTS `comment_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `like` INT NOT NULL DEFAULT '0',
  `dislike` INT NOT NULL DEFAULT '0',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  `course_id` INT NOT NULL,
  `user_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `comment_news` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `like` INT NOT NULL DEFAULT '0',
  `dislike` INT NOT NULL DEFAULT '0',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  `news_id` INT NOT NULL,
  `user_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
  
  CREATE TABLE IF NOT EXISTS `comment_post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `comment_score` INT NOT NULL DEFAULT '0',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
  
  CREATE TABLE IF NOT EXISTS `course` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `view_number` INT DEFAULT '0',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  `tag_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `role` (
  `id` TINYINT NOT NULL DEFAULT '5',
  `role_name` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Member',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

  CREATE TABLE IF NOT EXISTS `news` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NOT NULL DEFAULT '0',
  `title` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `view_number` INT DEFAULT '0',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `view_number` INT NOT NULL DEFAULT '0',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  `user_id` INT NOT NULL,
  `score` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `score` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `score_type` TINYINT NOT NULL COMMENT '1: up, -1: down',
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `score_of_comment_post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `score_type` TINYINT NOT NULL COMMENT '1: up, -1: down',
  `user_id` INT NOT NULL,
  `comment_post_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `tag` (
  `id` INT NOT NULL,
  `tag_name` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tag_slug` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(450) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '1: active, 0: block',
  `avatar` varchar(720) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '/assets/Uploads/Avatar/unset-icon.png',
  `fullname` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT 'Chưa có',
  `email` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone_number` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT 'Chưa có',
  `user_quote` varchar(720) COLLATE utf8mb4_unicode_ci DEFAULT 'Học tập cùng LearnTogether',
  `created_date` timestamp null,
  `modified_date` timestamp null,
  `role_id` TINYINT NOT NULL DEFAULT '5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `post_tag_mtm` (
  `post_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY(`post_id`, `tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `news_tag_mtm` (
  `news_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY(`news_id`, `tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- create foreign key --

-- One to many --
ALTER TABLE `user` ADD CONSTRAINT fk_user_role FOREIGN KEY (`role_id`) REFERENCES `role`(`id`);
ALTER TABLE `score` ADD CONSTRAINT fk_score_post FOREIGN KEY (`post_id`) REFERENCES `post`(`id`);
ALTER TABLE `score` ADD CONSTRAINT fk_score_user FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `score_of_comment_post` ADD CONSTRAINT fk_score_of_cmtpost_to_cmtpost FOREIGN KEY (`comment_post_id`) REFERENCES `comment_post`(`id`);
ALTER TABLE `score_of_comment_post` ADD CONSTRAINT fk_score_of_cmtpost_to_user FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `news` ADD CONSTRAINT fk_news_user FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `comment_post` ADD CONSTRAINT fk_commentpost_user FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `comment_post` ADD CONSTRAINT fk_commentpost_post FOREIGN KEY (`post_id`) REFERENCES `post`(`id`);
ALTER TABLE `comment_news` ADD CONSTRAINT fk_commentnews_user FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `comment_news` ADD CONSTRAINT fk_commentnews_news FOREIGN KEY (`news_id`) REFERENCES `news`(`id`);
ALTER TABLE `comment_course` ADD CONSTRAINT fk_commentcourse_user FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `comment_course` ADD CONSTRAINT fk_commentcourse_course FOREIGN KEY (`course_id`) REFERENCES `course`(`id`);
-- Many to many --
ALTER TABLE `post_tag_mtm` ADD CONSTRAINT fk_mtm_to_post FOREIGN KEY (`post_id`) REFERENCES `post`(`id`);
ALTER TABLE `post_tag_mtm` ADD CONSTRAINT fk_mtm_to_tag_for_post FOREIGN KEY (`tag_id`) REFERENCES `tag`(`id`);
ALTER TABLE `news_tag_mtm` ADD CONSTRAINT fk_mtm_to_news FOREIGN KEY (`news_id`) REFERENCES `news`(`id`);
ALTER TABLE `news_tag_mtm` ADD CONSTRAINT fk_mtm_to_tag_for_news FOREIGN KEY (`tag_id`) REFERENCES `tag`(`id`);


-- create TRIGGER --
-- Tính điểm của từng bài viết rồi cập nhật dữ liệu vào từng cột điểm của bài viết tương ứng:
-- 2 bảng: scores(`score_type`, `user_id`, `post_id`), posts(`post_id`, `score`)
DELIMITER $$
CREATE TRIGGER update_PostScore_when_update
AFTER UPDATE
ON `score` for each row
BEGIN 
	DECLARE up INT;
	DECLARE down INT;
	DECLARE score INT;
	SET up  = (SELECT COUNT(`user_id`) FROM `score` WHERE `score_type` = 1 AND `post_id` = new.`post_id`);
	SET down = (SELECT COUNT(`user_id`) FROM `score` WHERE `score_type` = -1 AND `post_id` = new.`post_id`);
	SET score = up - down;
	UPDATE `post` SET `score`= score WHERE `id` = new.`post_id`;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER update_PostScore_when_insert
AFTER INSERT
ON `score` for each row
BEGIN 
	DECLARE up INT;
	DECLARE down INT;
	DECLARE score INT;
	SET up  = (SELECT COUNT(`user_id`) FROM `score` WHERE `score_type` = 1 AND `post_id` = new.`post_id`);
	SET down = (SELECT COUNT(`user_id`) FROM `score` WHERE `score_type` = -1 AND `post_id` = new.`post_id`);
	SET score = up - down;
	UPDATE `post` SET `score`= score WHERE `id` = new.`post_id`;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER update_score_of_comment_post_when_update
AFTER UPDATE
ON `score_of_comment_post` for each row
BEGIN 
	DECLARE up INT;
	DECLARE down INT;
	DECLARE score INT;
	SET up  = (SELECT COUNT(`user_id`) FROM `score_of_comment_post` WHERE `score_type` = 1 AND `comment_post_id` = new.`comment_post_id`);
	SET down = (SELECT COUNT(`user_id`) FROM `score_of_comment_post` WHERE `score_type` = -1 AND `comment_post_id` = new.`comment_post_id`);
	SET score = up - down;
	UPDATE `comment_post` SET `comment_score`= score WHERE `id` = new.`comment_post_id`;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER update_score_of_comment_post_when_insert
AFTER INSERT
ON `score_of_comment_post` for each row
BEGIN 
	DECLARE up INT;
	DECLARE down INT;
	DECLARE score INT;
	SET up  = (SELECT COUNT(`user_id`) FROM `score_of_comment_post` WHERE `score_type` = 1 AND `comment_post_id` = new.`comment_post_id`);
	SET down = (SELECT COUNT(`user_id`) FROM `score_of_comment_post` WHERE `score_type` = -1 AND `comment_post_id` = new.`comment_post_id`);
	SET score = up - down;
	UPDATE `comment_post` SET `comment_score`= score WHERE `id` = new.`comment_post_id`;
END$$
DELIMITER ;
-- insert data --

INSERT INTO `role` (`id`, `role_name`) VALUES
(2, 'Admin'),
(4, 'Creation'),
(1, 'Root'),
(5, 'Member'),
(3, 'Mod');

INSERT INTO `user` (`id`, `username`, `password`, `avatar`, `fullname`, `email`, `phone_number`, `role_id`, `user_quote`) VALUES
(1, 'sysadmin', '$2y$10$ngPwr.wWccslXYfCyJ9RfemoxsW437Z88toLmWPgBUTAyqp4De2Pe', '/assets/Uploads/Avatar/user-avatar-1.jpg', 'Nguyễn Đức Mạnh', 'vantasi2000@gmail.com', '0987175947', 1, 'This is my fucking Website'),
(2, 'systester', '$2y$10$ngPwr.wWccslXYfCyJ9RfemoxsW437Z88toLmWPgBUTAyqp4De2Pe', '/assets/Uploads/Avatar/user-avatar-2.png', 'Chưa có', 'testercute@gmail.com', 'Chưa có', 2, 'LearnTogether'),
(3, 'drgon10', '$2y$10$ngPwr.wWccslXYfCyJ9RfemoxsW437Z88toLmWPgBUTAyqp4De2Pe', '/assets/Uploads/Avatar/user-avatar-3.jpg', 'Nguyễn Đức Mạnh', 'ducmanh01082000@gmail.com', 'Chưa có', 5, 'Học tập cùng LearnTogether');

INSERT INTO `tag` (`id`, `tag_name`, `tag_slug`) VALUES
(1, 'Hỏi đáp/Chia sẻ', 'question-share'),
(2, 'C++', 'cpp'),
(3, 'Java', 'java'),
(4, 'PHP', 'php'),
(5, 'Python', 'python'),
(6, 'Cấu trúc dữ liệu ', 'data-structures'),
(7, 'Giải thuật', 'algorimths'),
(8, 'C#', 'csharp');

