package com.lackier.telegram.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class MoneyBotController {
    @Autowired
    private MoneyBot moneyBot;
    @Autowired
    private MenuBuilderService menuBuilderService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        SendMessage sendMessage = (SendMessage) moneyBot.onWebhookUpdateReceived(update);
        sendMessage.setReplyMarkup(menuBuilderService.getMainMenu());
        return sendMessage;
    }
}
