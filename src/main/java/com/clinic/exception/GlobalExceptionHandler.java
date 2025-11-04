package com.clinic.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // ✅ Cho Spring biết đây là nơi xử lý lỗi toàn cục
public class GlobalExceptionHandler {

    // ✅ Phương thức xử lý lỗi chung (mọi loại lỗi)
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
        return "error/general"; // Trả về file templates/error/general.html
    }
}
