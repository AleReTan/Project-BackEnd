INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (3, 'address', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (8, 'orderCost', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require)
VALUES (9, 'orderStartTime', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require)
VALUES (10, 'orderEndTime', 6, 'String', FALSE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (11, 'statusOrder', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (12, 'number', 7, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (13, 'model', 7, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (14, 'color', 7, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (15, 'type', 7, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (2, 'lastName', 8, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (1, 'firstName', 8, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (18, 'driver', 6, 'Reference', FALSE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (4, 'phoneNumber', 8, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require)
VALUES (19, 'clientFirstName', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require)
VALUES (20, 'clientLastName', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require)
VALUES (21, 'clientPhoneNumber', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (16, 'car', 8, 'Reference', FALSE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require) VALUES (22, 'geoData', 6, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require)
VALUES (23, 'driverGeoData', 8, 'String', TRUE);
INSERT INTO eav.attribute (id, name, object_type_id, value_type, require)
VALUES (24, 'destinationGeoData', 6, 'String', TRUE);