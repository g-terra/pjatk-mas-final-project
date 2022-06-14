drop table if exists orders;
drop table if exists user_roles;
drop table if exists roles;
drop table if exists users;

create table users
(
    user_id             bigint auto_increment primary key,
    email               varchar(255) not null,
    name                varchar(255) not null,
    surname             varchar(255) not null,
    clint_id            bigint,
    employee_id         bigint,
    foreign key (user_id) references clients (user_id),
    foreign key (employee_id) references employees (employee_id)

);

create table clients
(
    clint_id        bigint auto_increment primary key,
    document_number varchar(255) null,
    document_type   varchar(255) null,
    client_phone    varchar(255) null,
    regon           varchar(255) null,
    user_id         varchar(255) null
);

create table employees
(
    employee_id     bigint auto_increment primary key,
    employment_date datetime     null,
    employee_phone  varchar(255) null
);



create index recommender_user_id_index
    on users (recommender_user_id);

create table roles
(
    role_id int auto_increment primary key,
    name    varchar(255) not null
);

create table user_roles
(
    user_id bigint,
    role_id int,
    foreign key (user_id) references users (user_id),
    foreign key (role_id) references roles (role_id),
    unique (user_id, role_id)
);

create table orders
(
    order_id        bigint primary key,
    order_status    varchar(255) not null default 'PLACED',
    placement_date  datetime     not null default now(),
    shipping_date   datetime     null,
    street          varchar(255) not null,
    building_number int          not null,
    flat_number     int,
    zip             varchar(255) not null,
    city            varchar(255) not null,
    deleted         bool                  default false,
    owner_id        bigint       not null,
    foreign key (owner_id) references users (user_id)
);


create table order_items
(
    order_item_id bigint primary key,

    color         varchar(255) not null,

)