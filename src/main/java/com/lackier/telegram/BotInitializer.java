package com.lackier.telegram;

import com.lackier.telegram.bot.MoneyBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Slf4j
@Component
public final class BotInitializer {

    private static final String PROXY_HOST = "localhost";
    private static final int PROXY_PORT = 9150;
    private static final DefaultBotOptions.ProxyType PROXY_TYPE = DefaultBotOptions.ProxyType.SOCKS5;

    public static void run () {

        try {
            log.info("Initializing API context...");
            ApiContextInitializer.init();

            TelegramBotsApi botsApi = new TelegramBotsApi();

            log.info("Configuring bot options...");
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

            /*botOptions.setProxyType(PROXY_TYPE);
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);*/

            log.info("Registering Bot...");
            botsApi.registerBot(new MoneyBot(botOptions));

            log.info("Bot is ready for work!");

        } catch (TelegramApiRequestException e) {
            log.error("Error while initializing bot!", e);
        }
    }
}
