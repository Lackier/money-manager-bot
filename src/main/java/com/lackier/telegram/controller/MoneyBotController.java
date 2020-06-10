package com.lackier.telegram.controller;

import com.lackier.telegram.bot.BotState;
import com.lackier.telegram.bot.MoneyBot;
import com.lackier.telegram.service.BotStateService;
import com.lackier.telegram.service.MenuBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@RestController
public class MoneyBotController {
    @Autowired
    private MoneyBot moneyBot;
    @Autowired
    private MenuBuilderService menuBuilderService;
    @Autowired
    private BotStateService botStateService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        User user = update.getMessage().getFrom();
        if (botStateService.getState(user) == null) {
            botStateService.setState(user, BotState.MENU);
        }

        SendMessage sendMessage = (SendMessage) moneyBot.onWebhookUpdateReceived(update);
        sendMessage.setReplyMarkup(menuBuilderService.getMainMenu());
        return sendMessage;
    }
}
