INSERT INTO roles(role_id, name) value (1, 'DEFAULT');
INSERT INTO roles(role_id, name) value (2, 'CLIENT');
INSERT INTO roles(role_id, name) value (3, 'EMPLOYEE');
INSERT INTO roles(role_id, name) value (4, 'OWNER');

INSERT INTO users (name, surname, email) value ('SuperUser', 'SuperUser', 'SuperUser@superuser.com');
INSERT INTO user_roles(user_id, role_id) value (1, 1);
INSERT INTO user_roles(user_id, role_id) value (1, 2);
INSERT INTO user_roles(user_id, role_id) value (1, 3);
INSERT INTO user_roles(user_id, role_id) value (1, 4);

INSERT INTO users (name, surname, email) value ('ClientUser', 'ClientUser', 'ClientUser@ClientUser.com');
INSERT INTO user_roles(user_id, role_id) value (2, 1);
INSERT INTO user_roles(user_id, role_id) value (2, 2);

INSERT INTO users (name, surname, email) value ('EmployeeUser', 'EmployeeUser', 'EmployeeUser@EmployeeUser.com');
INSERT INTO user_roles(user_id, role_id) value (3, 1);
INSERT INTO user_roles(user_id, role_id) value (3, 3);

INSERT INTO users (name, surname, email) value ('EmployeeClientUser', 'EmployeeClientUser',
                                                'EmployeeClientUser@EmployeeClientUser.com');
INSERT INTO user_roles(user_id, role_id) value (4, 1);
INSERT INTO user_roles(user_id, role_id) value (4, 2);
INSERT INTO user_roles(user_id, role_id) value (4, 3);


insert into factories(city, building_number, flat_number, street, zip)
    VALUE ('warsaw', 1, 1, 'ul 1.ego maja', '01-123');

INSERT INTO functionalities (functionality_id, name)
VALUES (4, 'Adjustable Height');
INSERT INTO functionalities (functionality_id, name)
VALUES (2, 'Bread toasting');
INSERT INTO functionalities (functionality_id, name)
VALUES (1, 'Multi shape');
INSERT INTO functionalities (functionality_id, name)
VALUES (3, 'waffle iron');

INSERT INTO device_manufacture_db.functionality_properties (functionality_functionality_id, name, type)
VALUES (1, 'number of shapes', 'NUMBER');
INSERT INTO device_manufacture_db.functionality_properties (functionality_functionality_id, name, type)
VALUES (2, 'Capacity', 'NUMBER');
INSERT INTO device_manufacture_db.functionality_properties (functionality_functionality_id, name, type)
VALUES (3, 'number of wuffles', 'NUMBER');
INSERT INTO device_manufacture_db.functionality_properties (functionality_functionality_id, name, type)
VALUES (4, 'Max height', 'NUMBER');

INSERT INTO device_manufacture_db.device_types (device_type_id, crated_at, device_type_status, name, power_consumption)
VALUES (1, '2022-06-18 22:57:19.141398', 'DRAFT', 'Grill', 2000);

