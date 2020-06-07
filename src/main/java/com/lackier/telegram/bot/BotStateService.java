package com.lackier.telegram.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;

@Service
public class BotStateService {
    private HashMap<User, BotState> stateHashMap = new HashMap<>();

    public BotState getState(User user) {
        if (!stateHashMap.containsKey(user)) {
            return null;
        }

        return stateHashMap.get(user);
    }

    public void setState(User user, BotState botState) {
        stateHashMap.put(user, botState);
    }
}
