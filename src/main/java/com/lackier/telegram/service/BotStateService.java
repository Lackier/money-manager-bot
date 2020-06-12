package com.lackier.telegram.service;

import com.lackier.telegram.bot.BotState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;

@Service
public class BotStateService {
    private HashMap<User, BotState> stateHashMap = new HashMap<>();

    private static final BotState defaultBotState = BotState.MENU;

    public BotState getState(User user) {
        if (!stateHashMap.containsKey(user)) {
            return null;
        }

        return stateHashMap.get(user);
    }

    public BotState getStateOrDefault(User user) {
        if (!stateHashMap.containsKey(user)) {
            stateHashMap.put(user, defaultBotState);
            return stateHashMap.get(user);
        }

        return stateHashMap.get(user);
    }

    public void setState(User user, BotState botState) {
        stateHashMap.put(user, botState);
    }
}
