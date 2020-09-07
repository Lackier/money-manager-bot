package com.lackier.telegram.api.service;

import com.lackier.telegram.api.entity.TelegramUser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TelegramUserService {
    @PersistenceContext
    private EntityManager entityManager;

    public Boolean existsUser(Integer userId) {
        List<TelegramUser> telegramUsers = entityManager.createQuery(
                "select tu from TelegramUser tu " +
                        "where tu.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return !telegramUsers.isEmpty();
    }

    public Integer addUser(User user) {
        TelegramUser telegramUser = new TelegramUser(user.getId(), user.getFirstName());
        entityManager.persist(telegramUser);
        return telegramUser.getId();
    }

    public String getUserName(Integer userId) {
        List<String> userNames = entityManager.createQuery(
                "select tu.name from TelegramUser tu " +
                "where tu.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return userNames.isEmpty() ? null : userNames.get(0);
    }

    public TelegramUser getTelegramUserByUserId(Integer userId) {
        List<Integer> users = entityManager.createQuery(
                "select tu.id from TelegramUser tu " +
                        "where tu.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return users.isEmpty() ? null : entityManager.getReference(TelegramUser.class, users.get(0));
    }
}
