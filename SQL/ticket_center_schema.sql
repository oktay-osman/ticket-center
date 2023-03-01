select * from user;CREATE TABLE `distribution` (
  `distributor_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  KEY `distribution_distributor_fk_idx` (`distributor_id`),
  KEY `distribution_event_fk_idx` (`event_id`),
  CONSTRAINT `distribution_distributor_fk` FOREIGN KEY (`distributor_id`) REFERENCES `distributor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `distribution_event_fk` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `distributor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `honorarium` double NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `distributor_user_fk_idx` (`user_id`),
  CONSTRAINT `distributor_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type_id` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `max_tickets_per_person` int(11) NOT NULL,
  `organizer_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `finish_date` datetime NOT NULL,
  `stamp_created` timestamp NOT NULL DEFAULT current_timestamp(),
  `stamp_modified` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `event_organizer_fk_idx` (`organizer_id`),
  KEY `event_event_type_fk_idx` (`type_id`),
  CONSTRAINT `event_event_type_fk` FOREIGN KEY (`type_id`) REFERENCES `event_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `event_organizer_fk` FOREIGN KEY (`organizer_id`) REFERENCES `organizer` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `event_owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_owner_user_fk_idx` (`user_id`),
  CONSTRAINT `event_owner_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `event_tickets` (
  `event_id` int(11) NOT NULL,
  `ticket_type` int(11) NOT NULL,
  `price` double NOT NULL,
  `available_tickets` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `event_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `organizer` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `organizer_user_fk_idx` (`user_id`),
  CONSTRAINT `organizer_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `seats` (
  `id` int(11) NOT NULL,
  `row` int(11) NOT NULL,
  `seat_number` int(11) NOT NULL,
  `ticket_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `ticket_type_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `stamp_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_fk_idx` (`role_id`),
  CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
