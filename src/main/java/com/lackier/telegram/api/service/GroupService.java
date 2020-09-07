package com.lackier.telegram.api.service;

import com.lackier.telegram.api.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class GroupService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TelegramUserService telegramUserService;

    public Integer addGroup(Integer userId, String groupName) {
        Group group = new Group();
        group.setUser(telegramUserService.getTelegramUserByUserId(userId));
        group.setName(groupName);
        entityManager.persist(group);
        return group.getId();
    }
}
