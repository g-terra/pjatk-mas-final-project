INSERT INTO device_manufacture_db_staging.functionalities (functionality_id, name) VALUES (6, 'A secrete functionality');
INSERT INTO device_manufacture_db_staging.functionalities (functionality_id, name) VALUES (1, 'Adjustable Height');
INSERT INTO device_manufacture_db_staging.functionalities (functionality_id, name) VALUES (3, 'cable modification');
INSERT INTO device_manufacture_db_staging.functionalities (functionality_id, name) VALUES (2, 'LCD screen');
INSERT INTO device_manufacture_db_staging.functionalities (functionality_id, name) VALUES (5, 'Multi-capacity');
INSERT INTO device_manufacture_db_staging.functionalities (functionality_id, name) VALUES (4, 'Multi-shape');

INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (1, 'Max Height', 'NUMBER');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (1, 'Min Height', 'NUMBER');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (2, 'Pixel count', 'NUMBER');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (2, 'remote controlled', 'YES_NO');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (3, 'includes adapter', 'YES_NO');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (4, 'Shape count', 'NUMBER');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (4, 'Shape description', 'TEXT');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (5, 'max capacity', 'NUMBER');
INSERT INTO device_manufacture_db_staging.functionality_properties (functionality_functionality_id, name, type) VALUES (6, 'can you tell me?', 'YES_NO');

INSERT INTO device_manufacture_db_staging.device_types (device_type_id, crated_at, device_type_status, name, power_consumption) VALUES (1, '2022-06-28 21:34:40.241494', 'VERSIONED', 'Grill', 1234);
INSERT INTO device_manufacture_db_staging.device_types (device_type_id, crated_at, device_type_status, name, power_consumption) VALUES (2, '2022-06-28 21:44:59.015875', 'DRAFT', 'Wuffle maker', 4);
INSERT INTO device_manufacture_db_staging.device_types (device_type_id, crated_at, device_type_status, name, power_consumption) VALUES (3, '2022-06-28 21:45:42.566239', 'VERSIONED', 'The MAS secrete machine', 2000);

INSERT INTO device_manufacture_db_staging.device_type_versions (device_type_version_id, create_date_time, device_type_version_status, version_number, device_type_id) VALUES (2, '2022-06-28 21:44:16.758664', 'AVAILABLE', 1, 1);
INSERT INTO device_manufacture_db_staging.device_type_versions (device_type_version_id, create_date_time, device_type_version_status, version_number, device_type_id) VALUES (3, '2022-06-28 21:46:13.573583', 'DEPRECATED', 1, 3);

INSERT INTO device_manufacture_db_staging.device_type_versions_functionalities (device_type_version_id, functionality_id) VALUES (2, 1);
INSERT INTO device_manufacture_db_staging.device_type_versions_functionalities (device_type_version_id, functionality_id) VALUES (2, 3);
INSERT INTO device_manufacture_db_staging.device_type_versions_functionalities (device_type_version_id, functionality_id) VALUES (2, 2);
INSERT INTO device_manufacture_db_staging.device_type_versions_functionalities (device_type_version_id, functionality_id) VALUES (2, 5);
INSERT INTO device_manufacture_db_staging.device_type_versions_functionalities (device_type_version_id, functionality_id) VALUES (2, 4);
INSERT INTO device_manufacture_db_staging.device_type_versions_functionalities (device_type_version_id, functionality_id) VALUES (3, 6);


INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'Max Height', 1, '10');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'Min Height', 1, '2');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'includes adapter', 3, 'yes');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'Pixel count', 2, '198');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'remote controlled', 2, 'yes');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'max capacity', 5, '3');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'Shape count', 4, '2');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (2, 'Shape description', 4, 'rectangle and triangle');
INSERT INTO device_manufacture_db_staging.device_type_version_property_values (device_type_version_device_type_version_id, name, parent_functionality_id, value) VALUES (3, 'can you tell me?', 6, 'no');


INSERT INTO device_manufacture_db_staging.roles(role_id, name) value (1, 'DEFAULT');
INSERT INTO device_manufacture_db_staging.roles(role_id, name) value (2, 'CLIENT');
INSERT INTO device_manufacture_db_staging.roles(role_id, name) value (3, 'EMPLOYEE');
INSERT INTO device_manufacture_db_staging.roles(role_id, name) value (4, 'OWNER');

INSERT INTO device_manufacture_db_staging.users (name, surname, email) value ('SuperUser', 'SuperUser', 'SuperUser@superuser.com');
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (1, 1);
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (1, 2);
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (1, 3);
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (1, 4);

INSERT INTO device_manufacture_db_staging.users (name, surname, email) value ('ClientUser', 'ClientUser', 'ClientUser@ClientUser.com');
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (2, 1);
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (2, 2);

INSERT INTO device_manufacture_db_staging.users (name, surname, email) value ('EmployeeUser', 'EmployeeUser', 'EmployeeUser@EmployeeUser.com');
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (3, 1);
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (3, 3);

INSERT INTO device_manufacture_db_staging.users (name, surname, email) value ('EmployeeClientUser', 'EmployeeClientUser',
                                                'EmployeeClientUser@EmployeeClientUser.com');
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (4, 1);
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (4, 2);
INSERT INTO device_manufacture_db_staging.user_roles(user_id, role_id) value (4, 3);


insert into device_manufacture_db_staging.factories(city, building_number, flat_number, street, zip)
    VALUE ('warsaw', 1, 1, 'ul 1.ego maja', '01-123');

