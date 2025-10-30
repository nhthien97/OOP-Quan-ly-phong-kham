
package com.clinic.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Admission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) private Patient patient;
    @ManyToOne(optional=false) private Room room;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
