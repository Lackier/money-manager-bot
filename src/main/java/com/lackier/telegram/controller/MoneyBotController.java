package com.lackier.telegram.controller;

import com.lackier.telegram.api.service.TelegramUserService;
import com.lackier.telegram.service.MessageHandlerService;
import com.lackier.telegram.service.MessageSenderService;
import com.lackier.telegram.service.StateActionResolverService;
import com.lackier.telegram.service.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@RestController
public class MoneyBotController {
    @Autowired
    private TelegramUserService telegramUserService;
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private UpdateInfoService updateInfoService;
    @Autowired
    private StateActionResolverService stateActionResolverService;
    @Autowired
    private MessageHandlerService messageHandlerService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        User user = updateInfoService.getUser(update);

        if (!telegramUserService.existsUser(user.getId())) {
            telegramUserService.addUser(user);
        }//todo make some registration warning :)

        Optional<String> callBackData = updateInfoService.getCallbackData(update);
        callBackData.ifPresent(s -> stateActionResolverService.resolveAction(user, s));

        Optional<String> messageText = updateInfoService.getMessageText(update);
        messageText.ifPresent(s -> messageHandlerService.handleMessage(user, s));

        return messageSenderService.sendMessage(user, updateInfoService.getChatId(update));
    }
}
