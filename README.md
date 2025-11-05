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

## 3. Sơ đồ hệ thống

### 3.1 Sơ đồ khối tổng thể hệ thống

Hệ thống được tổ chức theo các nhóm chức năng chính:
- Hệ thống đăng nhập / phân quyền: Quản lý người dùng, session, đăng nhập, đăng xuất.
- Nghiệp vụ phòng khám: Quản lý bệnh nhân, nhân viên, khoa, phòng, thiết bị và hồ sơ nhập viện.
- Luồng xử lý chính: Từ đăng nhập → Dashboard → chọn chức năng CRUD các module → nhập viện → theo dõi → đăng xuất.

📷 **Hình: Sơ đồ khối tổng quan**  
![Sơ đồ tổng thể](src/docs/img/08_overall_flow.png)

---

### 3.2 Sơ đồ lớp (Class Diagram / ERD)

#### 3.2.1 Đăng nhập / Đăng xuất hệ thống
- Mô tả quá trình xác thực người dùng với Spring Security khi truy cập vào hệ thống.
- Bao gồm các trường hợp:
  - Đăng nhập thành công → chuyển hướng về /home.
  - Đăng nhập thất bại → trả lỗi xác thực.
  - Đăng xuất → xóa session, chuyển về trang login.

📷 **Hình: Đăng nhập / Đăng xuất**  
![Đăng nhập / Đăng xuất](src/docs/img/01_login_logout.png)

---

#### 3.2.2 Toàn bộ luồng hoạt động người dùng trong hệ thống
- Tổng quan hành vi người dùng từ lúc đăng nhập → thao tác CRUD bệnh nhân → nhập viện → đăng xuất.
- Mô hình hóa các controller chính: Login, Patient, Admission, Logout.

📷 **Hình: Toàn bộ luồng hoạt động**  
![Toàn bộ luồng hoạt động](src/docs/img/08_overall_flow.png)

---

#### 3.2.3 CRUD Bệnh nhân (Patient)
- Thêm mới, xem danh sách, chỉnh sửa, xóa bệnh nhân.
- Tương tác giữa: `PatientController`, `PatientService`, `PatientRepo`, `DB`.

📷 **Hình: CRUD Bệnh nhân (Patient)**  
![CRUD Patient](src/docs/img/02_crud_patient.png)

---

#### 3.2.4 CRUD Nhân viên (Staff)
- Quản lý nhân sự trong phòng khám.
- Bao gồm thêm mới, chỉnh sửa, xóa nhân viên.

📷 **Hình: CRUD Nhân viên (Staff)**  
![CRUD Staff](src/docs/img/03_crud_staff.png)

---

#### 3.2.5 CRUD Phòng bệnh (Room)
- Quản lý thông tin phòng bệnh, số lượng giường trống, loại phòng.
- Khi nhập viện hệ thống sẽ cập nhật trạng thái phòng.

📷 **Hình: CRUD Phòng bệnh (Room)**  
![CRUD Room](src/docs/img/04_crud_room.png)

---

#### 3.2.6 CRUD Khoa (Department)
- Quản lý các khoa như nội trú, ngoại trú...
- Gắn trưởng khoa và danh sách phòng trực thuộc.

📷 **Hình: CRUD Khoa (Department)**  
![CRUD Department](src/docs/img/05_crud_department.png)

---

#### 3.2.7 CRUD Thiết bị (Equipment)
- Quản lý thiết bị trong từng phòng.
- Gắn trách nhiệm bảo trì cho từng nhân viên.

📷 **Hình: CRUD Thiết bị (Equipment)**  
![CRUD Equipment](src/docs/img/06_crud_equipment.png)

---

#### 3.2.8 Luồng nhập viện / xuất viện (Admission)
- Bệnh nhân được nhập viện (chọn phòng + ghi nhận thời gian).
- Khi xuất viện: cập nhật ngày ra và giảm số giường đã dùng.

📷 **Hình: Quy trình nhập viện / trả phòng**  
![Admission Process](src/docs/img/07_admission_process.png)
