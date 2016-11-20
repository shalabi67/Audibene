INSERT INTO Audiologists(first_name,last_name) VALUES('Mohammad', 'Shalabi');
INSERT INTO Audiologists(first_name,last_name) VALUES('Raluca', 'Moldovan');
INSERT INTO Audiologists(first_name,last_name) VALUES('Otto', 'Wesendonk');

DELETE FROM Audiologists WHERE first_name='Mohammad' and last_name = 'Shalabi' and id != 1;
DELETE FROM Audiologists WHERE first_name='Raluca' and last_name = 'Moldovan' and id != 2;
DELETE FROM Audiologists WHERE first_name='Otto' and last_name = 'Wesendonk' and id != 3;