# OOP-Ql-PK – Quản lý phòng khám

## Công nghệ
• Ngôn ngữ lập trình: Java 17 (jdk 17.0.17-amzn)  
• Framework: Spring Boot 3  
• Spring Web – xử lý HTTP requests theo mô hình MVC  
• Spring Data JPA – ORM, thao tác cơ sở dữ liệu  
• Spring Security – cấu hình đăng nhập, bảo vệ tài nguyên  
• Thymeleaf – hiển thị giao diện HTML động  
• Cơ sở dữ liệu: H2 Database (in-memory, dùng cho phát triển)  
• Build tool: Apache Maven  
• Kiểm thử: JUnit 5  
• Thiết kế UML: PlantUML (*.puml files)  
• Template engine: Thymeleaf  
• MVC: Controller (web), Service (business), Repository (data), Domain (model)  

## Chạy dự án
• Cấu hình `src/main/resources/application.properties` với thông tin MySQL (Aiven)  
• Chạy lệnh: `mvn spring-boot:run`  
• Mở trình duyệt tại: `http://localhost:8080/`  

## Đối tượng chính
Các entity đại diện cho các đối tượng quản lý trong hệ thống:

| Entity     | Mô tả                              |
|------------|-------------------------------------|
| Patient    | Thông tin bệnh nhân                 |
| Staff      | Nhân viên (bác sĩ, y tá, v.v.)      |
| Room       | Phòng bệnh                          |
| Department | Khoa phòng                          |
| Equipment  | Thiết bị y tế                       |
| Admission  | Hồ sơ nhập viện của bệnh nhân       |

## Chức năng CRUD
• Chức năng chính: `/admissions` – nhận bệnh nhân vào phòng/giường, theo dõi sử dụng  
• Mỗi entity đều có đầy đủ chức năng Create - Read - Update - Delete thông qua:  
  • Controller: xử lý yêu cầu từ người dùng (web layer)  
  • Service: xử lý nghiệp vụ  
  • Repository: truy xuất dữ liệu với JPA  
  • Giao diện Thymeleaf: các form và danh sách hiển thị  

| Đối tượng   | Chức năng |
|-------------|-----------|
| Bệnh nhân   | CRUD      |
| Nhân viên   | CRUD      |
| Phòng       | CRUD      |
| Khoa phòng  | CRUD      |
| Thiết bị    | CRUD      |
| Nhập viện   | CRUD      |

## Kiểm thử
• `AdmissionServiceTest` mô phỏng nhận & trả phòng, đảm bảo cập nhật số chỗ (occupied)  
• Sử dụng JUnit 5  
• Kiểm thử đơn vị (unit test) cho lớp `AdmissionService`  

## Bắt & xử lý lỗi
• Sử dụng `@ControllerAdvice` và `@ExceptionHandler` để xử lý lỗi toàn cục  
• Lỗi được hiển thị qua giao diện tùy chỉnh `templates/error/general.html`  
• Hiển thị thông báo lỗi thân thiện với người dùng bằng Thymeleaf  
• Khi xảy ra lỗi (ví dụ: RuntimeException, lỗi hệ thống), người dùng được chuyển đến trang thông báo lỗi  

### Cấu trúc xử lý lỗi:
• `GlobalExceptionHandler.java`: xử lý lỗi toàn cục  
• Template hiển thị lỗi: `general.html`  

### Test lỗi mẫu:
• Truy cập `/test-error` (nếu bật lại route test) sẽ ném lỗi và kích hoạt cơ chế xử lý  
• Người dùng thấy thông báo lỗi thay vì stack trace kỹ thuật  

## UML
• Xem thư mục `docs/uml` gồm:  
  • 01 Structural: `component.puml` (component/MVC)  
  • 05 Sequence: CRUD 4 đối tượng & luồng nhận phòng (core)  
• Có thể xem trực tiếp file `.puml` bằng VSCode extension hoặc qua plantuml online server  

## Giao diện
• Giao diện xây dựng bằng Thymeleaf và Bootstrap (CSS framework) để tạo bố cục rõ ràng, dễ dùng  

### Navbar (thanh menu trên đầu trang) mô phỏng các module quản lý chính:
• Bệnh nhân  
• Bác sĩ & Nhân viên  
• Khoa/Phòng  
• Phòng/Giường  
• Thiết bị  
• Nhận phòng  

### Mỗi module có:
• Form nhập liệu (dùng Bootstrap form)  
→ Cho phép Thêm mới và Chỉnh sửa thông tin  
• Bảng danh sách dữ liệu (sử dụng bảng Bootstrap)  
→ Hiển thị dữ liệu và có nút Sửa / Xóa  

## Thao tác hỗ trợ:
| Thao tác | Mô tả |
|----------|--------|
| Thêm     | Nhấn nút "Thêm mới", hiển thị form để nhập dữ liệu |
| Sửa      | Trong bảng có nút "Sửa" để chỉnh sửa thông tin     |
| Xóa      | Trong bảng có nút "Xóa", xác nhận và xóa bản ghi   |
