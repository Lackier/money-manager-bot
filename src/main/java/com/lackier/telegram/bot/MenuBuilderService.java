package com.lackier.telegram.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuBuilderService {
    public BotApiMethod<?> getMainMenu(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();

        List<String> mainMenu = new ArrayList<>();
        mainMenu.add("Income");
        mainMenu.add("Expense");
        mainMenu.add("Groups");
        mainMenu.add("Statistics");
        mainMenu.add("Settings");

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        mainMenu.forEach(row -> {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(row);
            keyboardRows.add(keyboardRow);
        });

        replyKeyboard.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboard);
        return sendMessage;
    }
}
