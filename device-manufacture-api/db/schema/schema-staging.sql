create table device_types
(
    device_type_id     bigint auto_increment
        primary key,
    crated_at          datetime(6)  null,
    device_type_status varchar(255) not null,
    name               varchar(255) not null,
    power_consumption  int          not null
);

create table device_type_versions
(
    device_type_version_id     bigint auto_increment
        primary key,
    create_date_time           datetime(6)  not null,
    device_type_version_status varchar(255) null,
    version_number             bigint       not null,
    device_type_id             bigint       not null,
    constraint FKbsdemh3ublundk6jhjk6lmfrk
        foreign key (device_type_id) references device_types (device_type_id)
);

create table device_type_version_property_values
(
    device_type_version_device_type_version_id bigint       not null,
    name                                       varchar(255) null,
    parent_functionality_id                    bigint       not null,
    value                                      varchar(255) null,
    constraint FK50f1w22ynhqc8kbncyxbyevxi
        foreign key (device_type_version_device_type_version_id) references device_type_versions (device_type_version_id)
);

create table factories
(
    id              bigint auto_increment
        primary key,
    city            varchar(255) not null,
    building_number varchar(255) not null,
    flat_number     varchar(255) null,
    street          varchar(255) not null,
    zip             varchar(255) not null
);

create table functionalities
(
    functionality_id bigint auto_increment
        primary key,
    name             varchar(255) not null,
    constraint UK_ps7mj42c7chy48bhlu6gbfy0f
        unique (name)
);

create table device_type_versions_functionalities
(
    device_type_version_id bigint not null,
    functionality_id       bigint not null,
    constraint FK7659912pnbkpf0vbqryd0t68v
        foreign key (device_type_version_id) references device_type_versions (device_type_version_id),
    constraint FK7c8icyayjohl29edh6bnpmkdc
        foreign key (functionality_id) references functionalities (functionality_id)
);

create table functionality_properties
(
    functionality_functionality_id bigint       not null,
    name                           varchar(255) null,
    type                           varchar(255) null,
    constraint FKeomtqpimmnyv75y1okqlu6gpo
        foreign key (functionality_functionality_id) references functionalities (functionality_id)
);

create table roles
(
    role_id bigint auto_increment
        primary key,
    name    varchar(255) not null,
    constraint UK_ofx66keruapi6vyqpv6f2or37
        unique (name)
);

create table teams
(
    id                    bigint auto_increment
        primary key,
    team_name             varchar(255) not null,
    target_device_type_id bigint       not null,
    constraint UK_dsqu2wx93en6lbl2bnrjy7kol
        unique (team_name),
    constraint FKc8fneu7fhgy1nfta9p8the69
        foreign key (target_device_type_id) references device_types (device_type_id)
);

create table users
(
    user_id bigint auto_increment
        primary key,
    email   varchar(255) not null,
    name    varchar(255) not null,
    surname varchar(255) not null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email)
);

create table clients
(
    client_id          bigint auto_increment
        primary key,
    id_document_number varchar(255) null,
    id_document_type   varchar(255) null,
    phone              varchar(255) not null,
    regon              varchar(255) null,
    recommender_id     bigint       null,
    user_id            bigint       not null,
    constraint UK_smrp6gi0tckq1w5rnd7boyowu
        unique (user_id),
    constraint FK4a3eonqo5xkpgxevr3jqi3ovd
        foreign key (recommender_id) references clients (client_id),
    constraint FKtiuqdledq2lybrds2k3rfqrv4
        foreign key (user_id) references users (user_id)
);

create table employees
(
    id               bigint auto_increment
        primary key,
    employement_date datetime(6)  null,
    phone            varchar(255) not null,
    team_id          bigint       null,
    user_id          bigint       not null,
    factory_id       bigint       not null,
    constraint UK_j2dmgsma6pont6kf7nic9elpd
        unique (user_id),
    constraint FK69x3vjuy1t5p18a5llb8h2fjx
        foreign key (user_id) references users (user_id),
    constraint FKa5cxjw6yuqlbp0np1g51o03gf
        foreign key (team_id) references teams (id),
    constraint FKsqpes7kg39xmkoe2d7aw6ssef
        foreign key (factory_id) references factories (id)
);

create table employee_paychecks
(
    employee_id bigint         not null,
    paymentdate datetime(6)    null,
    value       decimal(19, 2) null,
    constraint FKhieax6m5ytqxpavt9b4pjfqbl
        foreign key (employee_id) references employees (id)
);

create table orders
(
    order_id        bigint auto_increment
        primary key,
    deleted         bit          not null,
    order_status    varchar(255) not null,
    placement_date  datetime(6)  not null,
    city            varchar(255) not null,
    building_number varchar(255) not null,
    flat_number     varchar(255) null,
    street          varchar(255) not null,
    zip             varchar(255) not null,
    shipping_date   datetime(6)  null,
    owner_id        bigint       not null,
    constraint FK8t9wejyr7l482qglsypi1vyea
        foreign key (owner_id) references clients (client_id)
);

create table order_items
(
    order_item_id bigint auto_increment
        primary key,
    color         varchar(255) not null,
    advisor_id    bigint       not null,
    order_id      bigint       null,
    constraint FK8o872its9p9u9w3o7x7yl9cfj
        foreign key (advisor_id) references employees (id),
    constraint FKbioxgbv59vetrxe0ejfubep1w
        foreign key (order_id) references orders (order_id)
);

create table products
(
    product_id             bigint auto_increment
        primary key,
    build_date             datetime(6)  null,
    product_status         varchar(255) not null,
    serial                 varchar(255) not null,
    device_type_version_id bigint       not null,
    factory_id             bigint       not null,
    order_item_id          bigint       null,
    constraint UK_1enllrih2f37wba1ukxvhv1cj
        unique (order_item_id),
    constraint FK4dd0waf2w9u2qrfwa0i4uukr7
        foreign key (factory_id) references factories (id),
    constraint FKe70g0f4xs8l1u1wxgbvg3jm72
        foreign key (order_item_id) references order_items (order_item_id),
    constraint FKh5uwlna1sd0l48ugdmjvpbaro
        foreign key (device_type_version_id) references device_type_versions (device_type_version_id)
);

create table components
(
    component_id         bigint auto_increment
        primary key,
    base_price           decimal(19, 2) null,
    catalog_number       int            not null,
    security_certificate varchar(255)   not null,
    product_id           bigint         null,
    constraint FK7vg9g0ismnqrcogwv8svhli6h
        foreign key (product_id) references products (product_id)
);

create table component_materials
(
    component_component_id bigint       not null,
    name                   varchar(255) null,
    constraint FKr5qbslerrmft864ln98wqnqt
        foreign key (component_component_id) references components (component_id)
);

create table components_build_history
(
    main_component_id bigint      not null,
    sub_component_id  bigint      not null,
    created_at        datetime(6) not null,
    primary key (main_component_id, sub_component_id),
    constraint FK7hqj1o938knvqum9s0thk7keg
        foreign key (sub_component_id) references components (component_id),
    constraint FKoypf74w8v6yqnb0s6lo2j4a3r
        foreign key (main_component_id) references components (component_id)
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKh8ciramu9cc9q3qcqiv4ue8a6
        foreign key (role_id) references roles (role_id),
    constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id) references users (user_id)
);

