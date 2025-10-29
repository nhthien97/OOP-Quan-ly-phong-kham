# OOP-Ql-PK – Quản lý phòng khám

## Công nghệ
- Spring Boot 3 (Web, Thymeleaf, Data JPA)
- MySQL (Cloud – ví dụ Aiven)
- JUnit 5 test
- MVC: Controller (web), Service (business), Repository (data), Domain (model)

## Chạy dự án
1. Cấu hình `src/main/resources/application.properties` với thông tin MySQL (Aiven).
2. `mvn spring-boot:run`
3. Mở `http://localhost:8080/`

## Đối tượng chính
- Patient, Staff, Department, Room, Equipment, Admission

## Chức năng CRUD
- `/patients`, `/staff`, `/departments`, `/rooms`, `/equipment`
- Chức năng chính: `/admissions` – nhận bệnh nhân vào phòng/giường, theo dõi sử dụng.

## Kiểm thử
- `AdmissionServiceTest` mô phỏng nhận & trả phòng, đảm bảo cập nhật số chỗ (occupied).

## Bắt & xử lý lỗi
- Service ném `IllegalArgumentException` / `IllegalStateException` cho các tình huống dữ liệu không hợp lệ hoặc phòng đầy.
- Giao dịch (`@Transactional`) đảm bảo nhất quán dữ liệu.

## UML
Xem thư mục `docs/uml` gồm:
- 01 Structural: `component.puml` (component/MVC)
- 05 Sequence: CRUD 4 đối tượng & luồng nhận phòng (core).

Có thể xem trực tiếp file `.puml` bằng VSCode extension hoặc plantuml online server.

## Giao diện
- Navbar mô phỏng menu: Bệnh nhân, Bác sĩ & Nhân viên, Khoa/Phòng, Phòng/Giường, Thiết bị, Nhận phòng.
- Form + bảng Bootstrap, thao tác **Thêm/Sửa/Xóa**.

