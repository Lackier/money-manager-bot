package com.lackier.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@Service
public class UpdateInfoService {
    public User getUser(Update update) {
        return update.hasCallbackQuery()
                ? update.getCallbackQuery().getFrom()
                : update.getMessage().getFrom();
    }

    public Optional<String> getCallbackData(Update update) {
        return update.hasCallbackQuery()
                ? Optional.ofNullable(update.getCallbackQuery().getData())
                : Optional.empty();
    }

    public Optional<String> getMessageText(Update update) {
        return !update.hasCallbackQuery()
                ? Optional.ofNullable(update.getMessage().getText())
                : Optional.empty();
    }

    public Long getChatId(Update update) {
        return update.hasCallbackQuery()
                ? update.getCallbackQuery().getMessage().getChatId()
                : update.getMessage().getChatId();
    }
}
