-- Optionally seed data for quick demo
INSERT INTO department (id,name,facility) VALUES (1,'Nội tổng hợp','Cơ sở 1') ON DUPLICATE KEY UPDATE name=name;
INSERT INTO room (id, code, type, capacity, occupied, department_id) VALUES
(1,'PK-101','phong kham',5,0,1),
(2,'GB-201','giuong',2,0,1) ON DUPLICATE KEY UPDATE code=code;
