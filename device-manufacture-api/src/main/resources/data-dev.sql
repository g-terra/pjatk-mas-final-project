INSERT INTO roles(role_id, name) value (1, 'DEFAULT');
INSERT INTO roles(role_id, name) value (2, 'CLIENT');
INSERT INTO roles(role_id, name) value (3, 'EMPLOYEE');
INSERT INTO roles(role_id, name) value (4, 'OWNER');

INSERT INTO users (name, surname, email) value ('client', 'test', 'test@client.com');
INSERT INTO user_roles(user_id, role_id) value (1, 1);
INSERT INTO user_roles(user_id, role_id) value (1, 2);

insert into factories(city, building_number, flat_number, street, zip)
    VALUE ('warsaw', 1, 1, 'ul 1.ego maja', '01-123');


insert into device_types(device_type_status, name, power_consumption)
    value ('VERSIONED', 'test', '1000');

insert into device_type_versions(create_date_time, device_type_id)
    value (now(), 1);

insert into products(build_date, product_status, serial, device_type_version_id, factory_id, order_item_id)
value (now(),'SOLD','1233',1,1,NULL);