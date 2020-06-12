package com.lackier.telegram.controller;

import com.lackier.telegram.bot.BotState;
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
    private MenuBuilderService menuBuilderService;
    @Autowired
    private BotStateService botStateService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        User user = update.hasCallbackQuery()
                ? update.getCallbackQuery().getMessage().getFrom()
                : update.getMessage().getFrom();

        if (update.hasCallbackQuery()) {
            switch (update.getCallbackQuery().getData()) {
                case ("groups"):
                    botStateService.setState(user, BotState.GROUPS);
                    break;
                case ("to main menu"):
                    botStateService.setState(user, BotState.MENU);
                    break;
            }
        }

        SendMessage sendMessage = new SendMessage();

        switch (botStateService.getStateOrDefault(user)) {
            case MENU:
                sendMessage.setReplyMarkup(menuBuilderService.getMainMenu());
                break;
            case SETTINGS:
                break;
            case EXPENSES:
                break;
            case INCOMES:
                break;
            case GROUPS:
                sendMessage.setReplyMarkup(menuBuilderService.getGroupMenu());
                break;
            case STATISTICS:
                break;
        }

        if (update.hasCallbackQuery()) {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        } else {
            sendMessage.setChatId(update.getMessage().getChatId());
        }
        sendMessage.setText("Menu");

        return sendMessage;
    }
}
