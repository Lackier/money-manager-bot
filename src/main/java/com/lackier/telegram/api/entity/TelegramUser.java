package com.lackier.telegram.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "telegram_user")
@Data
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "telegram_user_id")
    Integer userId;

    @Column(name = "name")
    String name;
}
