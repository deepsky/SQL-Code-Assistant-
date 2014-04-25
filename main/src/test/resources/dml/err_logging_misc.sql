INSERT INTO dest
SELECT *
FROM   source s
LOG ERRORS INTO err$_dest ('INSERT') REJECT LIMIT UNLIMITED;


UPDATE dest
SET    code = DECODE(id, 9, NULL, 10, NULL, code)
WHERE  id BETWEEN 1 AND 10
LOG ERRORS INTO err$_dest ('UPDATE') REJECT LIMIT UNLIMITED;



MERGE INTO dest a
USING source b
ON (a.id = b.id)
WHEN MATCHED THEN
UPDATE SET a.code        = b.code,
a.description = b.description
WHEN NOT MATCHED THEN
INSERT (id, code, description)
VALUES (b.id, b.code, b.description)
LOG ERRORS INTO err$_dest ('MERGE') REJECT LIMIT UNLIMITED;


DELETE FROM dest  d
LOG ERRORS INTO err$_dest ('DELETE') REJECT LIMIT UNLIMITED;
