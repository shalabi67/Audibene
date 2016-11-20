INSERT INTO Audiologists(id,first_name,last_name) --VALUES(1, 'Mohammad', 'Shalabi');
SELECT 1, 'Mohammad', 'Shalabi'
FROM Audiologists
WHERE NOT EXISTS (SELECT 1 FROM Audiologists WHERE id=1);

INSERT INTO Audiologists(id,first_name,last_name) --VALUES(2, 'Raluca', 'Moldovan');
SELECT 2, 'Raluca', 'Moldovan'
FROM Audiologists
WHERE NOT EXISTS (SELECT 1 FROM Audiologists WHERE id=2);


INSERT INTO Audiologists(id,first_name,last_name)  --VALUES(3, 'Otto', 'Wesendonk');
SELECT 3, 'Otto', 'Wesendonk'
FROM Audiologists
WHERE NOT EXISTS (SELECT 1 FROM Audiologists WHERE id=3);


