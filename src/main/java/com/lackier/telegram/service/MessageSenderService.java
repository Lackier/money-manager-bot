package com.lackier.telegram.service;

import com.lackier.telegram.api.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class MessageSenderService {
    @Autowired
    private MenuBuilderService menuBuilderService;
    @Autowired
    private BotStateService botStateService;
    @Autowired
    private BotActionService botActionService;
    @Autowired
    private TelegramUserService telegramUserService;

    public SendMessage sendMessage(User user, Long chatId) {

        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);

        switch (botStateService.getStateOrDefault(user)) {
            case START:
                sendMessage.setText(
                        "Hello, " +  telegramUserService.getUserName(user.getId()) + ". " +
                                "Welcome to the app, to start using the bot, enter /menu");
                break;
            case MENU:
                sendMessage.setText("Choose the action:");
                sendMessage.setReplyMarkup(menuBuilderService.getMainMenu());
                break;
            case INCOMES:
                sendMessage.setText("Choose the action:");
                sendMessage.setReplyMarkup(menuBuilderService.getIncomeListMenu());
                break;
            case EXPENSES:
                sendMessage.setText("Choose the action:");
                sendMessage.setReplyMarkup(menuBuilderService.getExpenseListMenu());
                break;
            case GROUPS:
                switch (botActionService.getActionOrDefault(user)) {
                    case ADD:
                        sendMessage.setText("Type name of the new group you want to add:");
                        break;
                    case EDIT:

                        break;
                    case DELETE:

                        break;
                    default:
                        sendMessage.setText("Choose the action:");
                        sendMessage.setReplyMarkup(menuBuilderService.getGroupListMenu());
                }
                break;
            case STATISTICS:
            case SETTINGS:
                break;
        }

        return sendMessage;
    }

}
