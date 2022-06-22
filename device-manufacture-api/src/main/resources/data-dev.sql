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




