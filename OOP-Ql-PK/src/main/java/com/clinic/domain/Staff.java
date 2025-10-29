
package com.clinic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Staff {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique = true)
    private String code; // Mã NV
    @Column(nullable=false)
    private String fullName;
    private String role; // Bac si / Y ta / Nhan vien
    private String phone;
    private String idCard;
    @ManyToOne
    private Department department;
}
