create table if not exists user
(
    id                 bigint       not null auto_increment,
    created_date       timestamp,
    last_modified_date timestamp,
    city               varchar(255),
    district           varchar(255),
    image_url          varchar(255),
    nickname           varchar(255),
    provider           varchar(255),
    provider_id        varchar(255) not null,
    refresh_token      varchar(255),
    university         varchar(255),
    primary key (id)
) engine = InnoDB;

create table if not exists favorite_category
(
    id       bigint not null auto_increment,
    category varchar(255),
    user_id  bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists group_tb
(
    id                 bigint  not null auto_increment,
    created_date       timestamp,
    last_modified_date timestamp,
    category           varchar(255),
    city               varchar(255),
    district           varchar(255),
    image_url          varchar(255),
    introduction       longtext,
    is_end             boolean not null,
    is_offline         boolean not null,
    name               varchar(255),
    recruitment_cnt    integer not null,
    start_date         date,
    university         varchar(255),
    manager_id         bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists participant
(
    id                 bigint not null auto_increment,
    created_date       timestamp,
    last_modified_date timestamp,
    group_id           bigint,
    user_id            bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists schedule
(
    id                 bigint  not null auto_increment,
    created_date       timestamp,
    last_modified_date timestamp,
    attendance_check   boolean not null,
    contents           longtext,
    is_offline         boolean not null,
    start_date_time    timestamp,
    title              varchar(255),
    author_id          bigint,
    group_id           bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists attendance
(
    id          bigint  not null auto_increment,
    is_attend   boolean not null,
    user_id     bigint  not null,
    group_id    bigint,
    schedule_id bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists post
(
    id                 bigint not null auto_increment,
    created_date       timestamp,
    last_modified_date timestamp,
    contents           longtext,
    title              varchar(255),
    type               varchar(255),
    author_id          bigint,
    group_id           bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists image
(
    id        bigint not null auto_increment,
    image_url varchar(255),
    post_id   bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists comment
(
    id                 bigint not null auto_increment,
    created_date       timestamp,
    last_modified_date timestamp,
    contents           longtext,
    post_id            bigint,
    user_id            bigint,
    primary key (id)
) engine = InnoDB;

create table if not exists district
(
    id            bigint       not null auto_increment,
    city          varchar(255) not null,
    district_name varchar(255) not null,
    primary key (id)
) engine = MyISAM;

create table if not exists favorite_group
(
    id                 bigint not null auto_increment,
    group_id           bigint not null,
    user_id            bigint not null,
    created_date       timestamp,
    last_modified_date timestamp,
    primary key (id)
) engine = InnoDB;

alter table attendance
    add constraint groups_fk_attendance
        foreign key (group_id)
            references group_tb (id)
            on delete cascade;

alter table attendance
    add constraint schedule_fk_attendance
        foreign key (schedule_id)
            references schedule (id)
            on delete cascade;

alter table comment
    add constraint post_fk_comment
        foreign key (post_id)
            references post (id)
            on delete cascade;

alter table comment
    add constraint user_fk_comment
        foreign key (user_id)
            references user (id)
            on delete cascade;

alter table favorite_category
    add constraint user_fk_favorite_category
        foreign key (user_id)
            references user (id)
            on delete cascade;

alter table group_tb
    add constraint user_fk_groups
        foreign key (manager_id)
            references user (id);

alter table image
    add constraint post_fk_image
        foreign key (post_id)
            references post (id)
            on delete cascade;

alter table participant
    add constraint groups_fk_participant
        foreign key (group_id)
            references group_tb (id)
            on delete cascade;

alter table participant
    add constraint user_fk_participant
        foreign key (user_id)
            references user (id)
            on delete cascade;

alter table post
    add constraint user_fk_post
        foreign key (author_id)
            references user (id)
            on delete cascade;

alter table post
    add constraint groups_fk_post
        foreign key (group_id)
            references group_tb (id)
            on delete cascade;

alter table schedule
    add constraint user_fk_schedule
        foreign key (author_id)
            references user (id);


alter table schedule
    add constraint groups_fk_schedule
        foreign key (group_id)
            references group_tb (id)
            on delete cascade;

alter table favorite_group
    add constraint group_tb_fk_favorite_group
        foreign key (group_id)
            references group_tb (id)
            on delete cascade;

alter table favorite_group
    add constraint user_fk_favorite_group
        foreign key (user_id)
            references user (id)
            on delete cascade;
