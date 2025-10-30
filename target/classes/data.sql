-- Department
INSERT INTO DEPARTMENT (NAME, FACILITY) VALUES ('Khoa Nội', 'Tầng 1');
INSERT INTO DEPARTMENT (NAME, FACILITY) VALUES ('Khoa Ngoại', 'Tầng 2');

-- Staff
INSERT INTO STAFF (CODE, FULL_NAME, ID_CARD, PHONE, ROLE) VALUES
('NV001', 'Nguyễn Văn A', '0123456789', '0901234567', 'Bác sĩ'),
('NV002', 'Trần Thị B', '9876543210', '0912345678', 'Y tá');

-- Patient
INSERT INTO PATIENT (CODE, FULL_NAME, PHONE, ADDRESS) VALUES
('BN001', 'Phạm Văn C', '0901112222', 'Hà Nội'),
('BN002', 'Lê Thị D', '0913334444', 'Đà Nẵng');

-- Room
INSERT INTO ROOM (CODE, TYPE, CAPACITY, OCCUPIED) VALUES
('P101', 'VIP', 2, FALSE),
('P102', 'Thường', 4, TRUE);

-- Equipment
INSERT INTO EQUIPMENT (CODE, NAME, STATUS) VALUES
('TB001', 'Máy đo huyết áp', 'Tốt'),
('TB002', 'Giường bệnh', 'Đang bảo trì');
