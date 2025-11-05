# OOP-QL-PK – Quản lý phòng khám

---

## 1. Giới thiệu chung

### 1.1 Thành viên dự án
- Nguyễn Hoàng Thiên *(người đóng góp chính – ví dụ từ metadata VSCode)*
- Các thành viên khác có thể bổ sung vào đây nếu có

### 1.2 Yêu cầu chính
- Xây dựng một hệ thống quản lý phòng khám theo kiến trúc **Spring Boot MVC**
- Hỗ trợ chức năng **CRUD đầy đủ** cho các thực thể: Bệnh nhân, Nhân viên, Phòng, Khoa, Thiết bị, Nhập viện
- Giao diện web dễ sử dụng (sử dụng Thymeleaf + Bootstrap)
- Xử lý lỗi toàn cục và kiểm thử nghiệp vụ
- Vẽ sơ đồ UML cho kiến trúc và các luồng xử lý chính

### 1.3 Mô tả cụ thể
- Ngôn ngữ: Java 17 (jdk 17.0.17-amzn)
- Framework: Spring Boot 3
- ORM: Spring Data JPA
- UI: Thymeleaf, Bootstrap
- Bảo mật: Spring Security
- Cơ sở dữ liệu: H2 (dev), MySQL (production - cấu hình thủ công)
- UML: PlantUML
- Kiểm thử: JUnit 5
- Build tool: Maven

---

## 2. Object (Đối tượng chính)

### 2.1 Patient (Bệnh nhân)
#### 2.1.1 Attribute (Thuộc tính)
- `id`: Long – mã định danh
- `fullName`: String – họ tên
- `dateOfBirth`: LocalDate – ngày sinh
- `gender`: String – giới tính
- `address`: String – địa chỉ
- `phone`: String – số điện thoại

#### 2.1.2 Methods (Phương thức)
- `getters / setters`
- `toString()`
- `equals()` / `hashCode()`

---

### 2.2 Staff (Nhân viên)
#### 2.2.1 Attribute
- `id`: Long
- `name`: String
- `role`: String (bác sĩ, y tá...)
- `phone`: String
- `email`: String

#### 2.2.2 Methods
- `getters / setters`
- `toString()`

---

### 2.3 Room (Phòng)
#### 2.3.1 Attribute
- `id`: Long
- `roomNumber`: String
- `capacity`: int – số giường
- `occupied`: int – số giường đã sử dụng
- `department`: Department – khoa liên kết

#### 2.3.2 Methods
- `assignPatient()`
- `releasePatient()`
- `getAvailableBeds()`

---

### 2.4 Department (Khoa)
#### 2.4.1 Attribute
- `id`: Long
- `name`: String
- `description`: String

#### 2.4.2 Methods
- `getters / setters`
- `toString()`

---

### 2.5 Equipment (Thiết bị)
#### 2.5.1 Attribute
- `id`: Long
- `name`: String
- `status`: String (còn sử dụng, đang sửa...)

#### 2.5.2 Methods
- `markAsInUse()`
- `markAsAvailable()`

---

### 2.6 Admission (Nhập viện)
#### 2.6.1 Attribute
- `id`: Long
- `patient`: Patient
- `room`: Room
- `admissionDate`: LocalDate
- `dischargeDate`: LocalDate (nullable)

#### 2.6.2 Methods
- `admitPatientToRoom()`
- `dischargePatient()`
- `calculateStayDuration()