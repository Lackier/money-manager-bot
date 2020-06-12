package com.lackier.telegram.bot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoneyBot extends TelegramWebhookBot {
    private String botToken;
    private String botUsername;
    private String botPath;

    public MoneyBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }
}
