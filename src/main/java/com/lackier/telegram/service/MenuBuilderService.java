package com.lackier.telegram.service;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuBuilderService {
    private static final String mainMenuTemplateFile = "templates/main_menu.template.json";
    private static final String groupListMenuTemplateFile = "templates/groupList_menu.template.json";
    private static final String incomeListMenuTemplateFile = "templates/incomeList_menu.template.json";
    private static final String expenseListMenuTemplateFile = "templates/expenseList_menu.template.json";

    public InlineKeyboardMarkup getMainMenu() {
        return getKeyboard(getMenu(mainMenuTemplateFile));
    }

    public InlineKeyboardMarkup getIncomeListMenu() {
        return getKeyboard(getMenu(incomeListMenuTemplateFile));
    }

    public InlineKeyboardMarkup getExpenseListMenu() {
        return getKeyboard(getMenu(expenseListMenuTemplateFile));
    }

    public InlineKeyboardMarkup getGroupListMenu() {
        return getKeyboard(getMenu(groupListMenuTemplateFile));
    }

    private InlineKeyboardMarkup getKeyboard(JSONObject jsonObject) {
        JSONArray rows = jsonObject.getJSONArray("rows");
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (int i = 0; i < rows.length(); i++) {
            JSONObject rowObj = rows.getJSONObject(i);
            keyboard.add(getKeyboardRow(rowObj));
        }

        return new InlineKeyboardMarkup(keyboard);
    }

    private List<InlineKeyboardButton> getKeyboardRow(JSONObject rowObj) {
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

        JSONArray row = rowObj.getJSONArray("buttons");

        for (int i = 0; i < row.length(); i++) {
            keyboardRow.add(getKeyboardButton(row.getJSONObject(i)));
        }

        return keyboardRow;
    }

    private InlineKeyboardButton getKeyboardButton(JSONObject buttonObj) {
        InlineKeyboardButton keyboardButton = new InlineKeyboardButton();

        keyboardButton.setText(buttonObj.getString("text"));
        keyboardButton.setCallbackData(buttonObj.getString("callbackData"));

        return keyboardButton;
    }

    private JSONObject getMenu(String templateFileName) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(templateFileName);
            assert inputStream != null;
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            return new JSONObject(result);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error during parsing " + templateFileName + " file", e);
        }
    }
}
