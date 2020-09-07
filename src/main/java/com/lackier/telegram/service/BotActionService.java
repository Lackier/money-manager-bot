package com.lackier.telegram.service;

import com.lackier.telegram.bot.BotAction;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;

@Service
public class BotActionService {
    private HashMap<User, BotAction> actionHashMap = new HashMap<>();

    private static final BotAction defaultAction = BotAction.NONE;

    public BotAction getAction(User user) {
        if (!actionHashMap.containsKey(user)) {
            return null;
        }

        return actionHashMap.get(user);
    }

    public BotAction getActionOrDefault(User user) {
        if (!actionHashMap.containsKey(user)) {
            actionHashMap.put(user, defaultAction);
            return actionHashMap.get(user);
        }

        return actionHashMap.get(user);
    }

    public void setAction(User user, BotAction botAction) {
        actionHashMap.put(user, botAction);
    }

    public void clearAction(User user) {
        setAction(user, BotAction.NONE);
    }
}
