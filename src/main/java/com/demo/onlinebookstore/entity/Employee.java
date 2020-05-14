package com.demo.onlinebookstore.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tbl_employee")
@Getter @Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active=true")
public class Employee {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String firstName;

    private String lastName;

    private String emailId;

    private boolean active;
}
