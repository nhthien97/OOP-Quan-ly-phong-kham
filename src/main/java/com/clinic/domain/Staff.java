package com.clinic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // Mã nhân viên

    @Column(nullable = false)
    private String fullName; // Họ tên

    private String role;     // Chức vụ
    private String phone;    // Điện thoại
    private String idCard;   // CMND/CCCD

    @ManyToOne
    private Department department; // Khoa
}
