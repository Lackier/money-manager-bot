package com.lackier.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoneyManagerTelegramBotApp {

	public static void main(String[] args) {
		BotInitializer.run();
		SpringApplication.run(MoneyManagerTelegramBotApp.class, args);
	}
}
