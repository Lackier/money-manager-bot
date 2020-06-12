package com.lackier.telegram.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "operation_type")
@Data
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;
}
