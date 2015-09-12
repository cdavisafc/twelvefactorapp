create table greeting_tmp like greeting;
ALTER TABLE `greeting_tmp` CHANGE COLUMN `date` `date` BIGINT NOT NULL;
insert greeting_tmp select id, UNIX_TIMESTAMP(DATE_FORMAT(date, '%Y-%m-%d')), first_name from greeting;
rename table `greeting` to `greeting_bak`, `greeting_tmp` to `greeting`;
