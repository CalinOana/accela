drop table if exists person;
drop table if exists address;

create table person
(
    `id`   char(36) not null,
    `first_name` varchar(255),
    `last_name` varchar(255),
    primary key (`id`)
);

create table address
(
    `id`        char(36) not null,
    `person_id`        char(36) not null,
    `street` varchar(255),
    `city` varchar(255),
    `state` varchar(255),
    `postal_code`   char(36) not null,
    primary key (`id`),
    constraint `fk_address_person_id` foreign key (`person_id`) references person (`id`)
);
