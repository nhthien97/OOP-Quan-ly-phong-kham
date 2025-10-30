MERGE INTO department (id, name, facility)
KEY(id)
VALUES (1, 'Nội tổng hợp', 'Cơ sở 1');

MERGE INTO room (id, code, "type", capacity, occupied, department_id)
KEY(id)
VALUES
(1, 'PK-101', 'phong kham', 5, 0, 1),
(2, 'GB-201', 'giuong',     2, 0, 1);
