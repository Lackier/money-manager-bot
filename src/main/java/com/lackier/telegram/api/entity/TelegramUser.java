package com.lackier.telegram.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "telegram_user")
@Data
@NoArgsConstructor
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "telegram_user_id")
    Integer userId;

    @Column(name = "name")
    String name;

    public TelegramUser(Integer userId) {
        this.userId = userId;
    }

    public TelegramUser(Integer userId, String firstName) {
        this.userId = userId;
        this.name = firstName;
    }
}
