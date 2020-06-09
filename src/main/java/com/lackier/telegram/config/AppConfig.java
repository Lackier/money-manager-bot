package com.lackier.telegram.config;

import com.lackier.telegram.bot.MoneyBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.webhook.path}")
    private String botPath;

    @Bean
    public MoneyBot MoneyBot() {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);

        MoneyBot moneyBot = new MoneyBot(options);
        moneyBot.setBotUsername(botUsername);
        moneyBot.setBotToken(botToken);
        moneyBot.setBotPath(botPath);

        return moneyBot;
    }
}
