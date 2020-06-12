package com.lackier.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuBuilderService {
    public InlineKeyboardMarkup getMainMenu() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Income");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Expense");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Groups");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Statistics");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Settings");

        button1.setCallbackData("Income".toLowerCase());
        button2.setCallbackData("Expense".toLowerCase());
        button3.setCallbackData("Groups".toLowerCase());
        button4.setCallbackData("Statistics".toLowerCase());
        button4.setCallbackData("Settings".toLowerCase());

        List<InlineKeyboardButton> keyboardRow1 = new ArrayList<>();
        keyboardRow1.add(button1);
        keyboardRow1.add(button2);
        keyboardRow1.add(button3);
        keyboardRow1.add(button4);
        keyboardRow1.add(button5);
        keyboard.add(keyboardRow1);

        return new InlineKeyboardMarkup(keyboard);
    }

    public ReplyKeyboard getGroupMenu() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Add");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Edit");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Delete");
        InlineKeyboardButton button4 = new InlineKeyboardButton("To main menu");

        button1.setCallbackData("Add".toLowerCase());
        button2.setCallbackData("Edit".toLowerCase());
        button3.setCallbackData("Delete".toLowerCase());
        button4.setCallbackData("To main menu".toLowerCase());

        List<InlineKeyboardButton> keyboardRow1 = new ArrayList<>();
        keyboardRow1.add(button1);
        keyboardRow1.add(button2);
        keyboardRow1.add(button3);
        keyboard.add(keyboardRow1);

        List<InlineKeyboardButton> keyboardRow2 = new ArrayList<>();
        keyboardRow2.add(button4);
        keyboard.add(keyboardRow2);

        return new InlineKeyboardMarkup(keyboard);
    }
}
