package com.lackier.telegram.controller;

import com.lackier.telegram.api.service.TelegramUserService;
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
    @Autowired
    private TelegramUserService telegramUserService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        User user = update.hasCallbackQuery()
                ? update.getCallbackQuery().getMessage().getFrom()
                : update.getMessage().getFrom();

        if (!telegramUserService.existsUser(user.getId())) {
            telegramUserService.addUser(user);
        }

        if (update.hasCallbackQuery()) {
            switch (update.getCallbackQuery().getData()) {
                case "/start":
                    botStateService.setState(user, BotState.START);
                    break;
                case "/income":
                    botStateService.setState(user, BotState.INCOMES);
                    break;
                case "/expense":
                    botStateService.setState(user, BotState.EXPENSES);
                    break;
                case "/groups":
                    botStateService.setState(user, BotState.GROUPS);
                    break;
                case "/toMainMenu":
                case "/menu":
                    botStateService.setState(user, BotState.MENU);
                    break;
            }
        } else {
            switch (update.getMessage().getText()) {
                case "/start":
                    botStateService.setState(user, BotState.START);
                    break;
                case "/menu":
                    botStateService.setState(user, BotState.MENU);
                    break;
            }
        }

        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(update.hasCallbackQuery()
                ? update.getCallbackQuery().getMessage().getChatId()
                : update.getMessage().getChatId());
        sendMessage.setText("Choose the action:");

        switch (botStateService.getState(user)) {
            case START:
                sendMessage.setText(
                        "Hello, " +  telegramUserService.getUserName(user.getId()) + ". " +
                        "Welcome to the app, to start using the bot, enter /menu");
                break;
            case MENU:
                sendMessage.setReplyMarkup(menuBuilderService.getMainMenu());
                break;
            case INCOMES:
                sendMessage.setReplyMarkup(menuBuilderService.getIncomeListMenu());
                break;
            case EXPENSES:
                sendMessage.setReplyMarkup(menuBuilderService.getExpenseListMenu());
                break;
            case GROUPS:
                sendMessage.setReplyMarkup(menuBuilderService.getGroupListMenu());
                break;
            case STATISTICS:
            case SETTINGS:
                break;
        }

        return sendMessage;
    }
}
