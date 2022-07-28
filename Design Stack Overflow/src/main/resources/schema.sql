create table answer
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    answer_status varchar(255),
    description   varchar(255),
    member_id     bigint,
    question_id   bigint,
    primary key (id)
);


create table badges
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    title         varchar(255),
    primary key (id)
);

create table comment
(
    id             bigint not null,
    created_date   timestamp,
    modified_date  timestamp,
    comment_status varchar(255),
    description    varchar(255),
    entity_id      bigint,
    entity_type    varchar(255),
    member_id      bigint,
    primary key (id)
);

create table members
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    email_id      varchar(255),
    name          varchar(255),
    user_id       varchar(255),
    primary key (id)
);

create table question
(
    id              bigint not null,
    created_date    timestamp,
    modified_date   timestamp,
    description     varchar(255),
    member_id       bigint,
    question_status varchar(255),
    title           varchar(255),
    primary key (id)
);

create table question_tag
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    question_id   bigint,
    tag_id        bigint,
    primary key (id)
);

create table report
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    report_type   varchar(255),
    entity_id     bigint,
    entity_type   varchar(255),
    primary key (id)
);

create table tags
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    description   varchar(255),
    title         varchar(255),
    primary key (id)
);

create table user_badges
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    batch_id      bigint,
    member_id     bigint,
    primary key (id)
);

create table vote
(
    id            bigint not null,
    created_date  timestamp,
    modified_date timestamp,
    entity_id     bigint,
    entity_type   varchar(255),
    points        double,
    vote_type     varchar(255),
    primary key (id)
);
