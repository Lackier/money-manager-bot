package com.lackier.telegram.service;

import com.lackier.telegram.api.service.GroupService;
import com.lackier.telegram.bot.BotAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class MessageHandlerService {
    @Autowired
    private BotStateService botStateService;
    @Autowired
    private BotActionService botActionService;
    @Autowired
    private GroupService groupService;

    public void handleMessage(User user, String messageText) {
        switch (botStateService.getStateOrDefault(user)) {
            case GROUPS:
                if (botActionService.getActionOrDefault(user) == BotAction.ADD) {
                    if (!messageText.contains("/")) {
                        groupService.addGroup(user.getId(), messageText);
                        botActionService.clearAction(user);
                    }
                }
                break;
        }
    }
}
