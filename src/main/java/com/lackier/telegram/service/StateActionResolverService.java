package com.lackier.telegram.service;

import com.lackier.telegram.bot.BotAction;
import com.lackier.telegram.bot.BotState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class StateActionResolverService {
    @Autowired
    private BotStateService botStateService;
    @Autowired
    private BotActionService botActionService;

    public void resolveAction(User user, String callBackData) {
        if (callBackData.startsWith("/start")) {
            botStateService.setState(user, BotState.START);
        } else if (callBackData.startsWith("/income")) {
            botStateService.setState(user, BotState.INCOMES);
        } else if (callBackData.startsWith("/expense")) {
            botStateService.setState(user, BotState.EXPENSES);
        } else if (callBackData.startsWith("/groups")) {
            botStateService.setState(user, BotState.GROUPS);
            if (callBackData.split("/").length > 2) {
                switch (callBackData.split("/")[2]) {
                    case "add":
                        botActionService.setAction(user, BotAction.ADD);
                        break;
                    case "edit":
                        botActionService.setAction(user, BotAction.EDIT);
                        break;
                    case "delete":
                        botActionService.setAction(user, BotAction.DELETE);
                        break;
                }
            }
        } else if (callBackData.startsWith("/toMainMenu") || callBackData.startsWith("/menu")) {
            botStateService.setState(user, BotState.MENU);
        }
    }
}
