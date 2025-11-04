-- Department
INSERT INTO DEPARTMENT (NAME, FACILITY) VALUES ('Khoa Nội', 'Tầng 1');
INSERT INTO DEPARTMENT (NAME, FACILITY) VALUES ('Khoa Ngoại', 'Tầng 2');

-- Staff (sửa tên)
INSERT INTO STAFF (CODE, FULL_NAME, ID_CARD, PHONE, ROLE) VALUES
('NV001', 'Nguyễn Hoàng Thiên', '0123456789', '0901234567', 'Bác sĩ'),
('NV002', 'Nguyễn Lệ Thu', '9876543210', '0912345678', 'Y tá');

-- Patient (sửa tên)
INSERT INTO PATIENT (CODE, FULL_NAME, PHONE, ADDRESS) VALUES
('BN001', 'Nguyễn Hoàng Thiên', '0901112222', 'Hà Nội'),
('BN002', 'Phạm Ngọc Vũ', '0901649222', 'Hà Nội'),
('BN003', 'Phạm Tuấn Anh', '090155222', 'Hà Nội'),
('BN004', 'Nguyễn Lệ Thu', '0913334444', 'Đà Nẵng');

-- Room (giữ nguyên)
INSERT INTO ROOM (CODE, TYPE, CAPACITY, OCCUPIED) VALUES
('P101', 'VIP', 2, FALSE),
('P102', 'Thường', 4, TRUE);

-- Equipment (giữ nguyên)
INSERT INTO EQUIPMENT (CODE, NAME, STATUS) VALUES
('TB001', 'Máy đo huyết áp', 'Tốt'),
('TB002', 'Giường bệnh', 'Đang bảo trì');
