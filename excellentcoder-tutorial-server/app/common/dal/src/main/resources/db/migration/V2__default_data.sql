-- roles
INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');


-- users: 密码为: 888888
INSERT INTO `users`(`id`, `name`, `username`, `email`, `password`, `gmt_create`, `gmt_modified`) VALUES (1, '叶升狐', '叶升', 'yesheng@163.com', '$2a$10$KupKGeBN8E7fWfxBn7CfDujTUE79e.2NhbxkWDeW3CGLsNFlt0FEe', '2019-11-06 17:55:03', '2019-11-06 17:55:06');
INSERT INTO `users`(`id`, `name`, `username`, `email`, `password`, `gmt_create`, `gmt_modified`) VALUES (2, '陆昇远', '陆昇远', 'lushengyuan@163.com', '$2a$10$KupKGeBN8E7fWfxBn7CfDujTUE79e.2NhbxkWDeW3CGLsNFlt0FEe', '2019-11-06 17:55:59', '2019-11-06 17:56:01');
INSERT INTO `users`(`id`, `name`, `username`, `email`, `password`, `gmt_create`, `gmt_modified`) VALUES (3, '紫樱儿', '紫樱儿', 'ziyinger@gmail.com', '$2a$10$KupKGeBN8E7fWfxBn7CfDujTUE79e.2NhbxkWDeW3CGLsNFlt0FEe', '2019-11-06 17:56:36', '2019-11-06 17:56:38');

-- user_roles
INSERT INTO `user_roles`(`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_roles`(`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles`(`user_id`, `role_id`) VALUES (3, 1);

-- polls
INSERT INTO `polls`(`id`, `question`, `gmt_expiration`, `gmt_create`, `gmt_modified`, `created_by`, `updated_by`) VALUES (1, 'What\'s your favorite web framework for Java?', '2025-11-01 18:56:48', '2019-10-27 08:57:31', '2019-10-27 08:57:32', 1, 1);
INSERT INTO `polls`(`id`, `question`, `gmt_expiration`, `gmt_create`, `gmt_modified`, `created_by`, `updated_by`) VALUES (2, 'Which social media platforms do you spend the most time on?', '2025-11-01 19:03:22', '2019-10-27 09:03:24', '2019-10-27 09:03:24', 2, 2);
INSERT INTO `polls`(`id`, `question`, `gmt_expiration`, `gmt_create`, `gmt_modified`, `created_by`, `updated_by`) VALUES (3, '年轻人为啥不爱结婚？', '2025-11-06 17:50:06', '2019-11-06 17:50:26', '2019-11-06 17:50:30', 1, 2);
INSERT INTO `polls`(`id`, `question`, `gmt_expiration`, `gmt_create`, `gmt_modified`, `created_by`, `updated_by`) VALUES (4, '男生从不送任何礼物，是不爱女朋友吗？', '2025-11-06 17:50:54', '2019-11-06 17:50:59', '2019-11-06 17:51:01', 2, 1);

-- choices
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (1, 'Spring Boot', 1);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (2, 'Play framework', 1);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (3, 'Dropwizard', 1);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (4, 'Facebook', 2);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (5, 'Twitter', 2);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (6, 'Instagram', 2);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (7, 'Snapchat', 2);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (8, '因为“穷”', 3);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (9, '没有结婚对象', 3);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (10, '害怕失去自由', 3);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (11, '其他原因', 3);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (12, '还是喜欢的，爱的方式不同', 4);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (13, '舍不得为你花钱就是不爱！', 4);
INSERT INTO `choices`(`id`, `text`, `poll_id`) VALUES (14, '从不送……有点过分了', 4);

-- votes
INSERT INTO `votes`(`id`, `user_id`, `poll_id`, `choice_id`, `gmt_create`, `gmt_modified`) VALUES (1, 1, 1, 1, '2019-11-06 18:05:39', '2019-11-06 18:05:41');
INSERT INTO `votes`(`id`, `user_id`, `poll_id`, `choice_id`, `gmt_create`, `gmt_modified`) VALUES (2, 1, 2, 6, '2019-11-06 18:06:33', '2019-11-06 18:06:35');
INSERT INTO `votes`(`id`, `user_id`, `poll_id`, `choice_id`, `gmt_create`, `gmt_modified`) VALUES (3, 2, 3, 9, '2019-11-10 21:07:47', '2019-11-10 21:07:49');
INSERT INTO `votes`(`id`, `user_id`, `poll_id`, `choice_id`, `gmt_create`, `gmt_modified`) VALUES (4, 1, 3, 8, '2019-11-11 14:39:33', '2019-11-11 14:39:33');