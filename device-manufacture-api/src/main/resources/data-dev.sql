INSERT INTO roles(role_id, name) value (1, 'DEFAULT');
INSERT INTO roles(role_id, name) value (2, 'CLIENT');
INSERT INTO roles(role_id, name) value (3, 'EMPLOYEE');
INSERT INTO roles(role_id, name) value (4, 'OWNER');

INSERT INTO users (name, surname, email) value ('client', 'test', 'test@client.com');
INSERT INTO user_roles(user_id, role_id) value (1, 1);
INSERT INTO user_roles(user_id, role_id) value (1, 2);