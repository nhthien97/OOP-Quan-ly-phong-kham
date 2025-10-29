
package com.clinic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String code;
    private String name;
    private String status; // READY / IN_USE / REPAIR
    @ManyToOne
    private Room room;
}
