
package com.clinic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String code;
    private String type; // phong kham / phong dieu tri / giuong
    private Integer capacity;
    private Integer occupied;
    @ManyToOne
    private Department department;
}
