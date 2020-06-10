package com.lackier.telegram.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "group")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    TelegramUser user;

    @Column(name = "name")
    String name;
}
