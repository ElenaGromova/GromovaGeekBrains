package com.geekbrains.geek.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class GeekMarketApplication {
	// Домашнее задание:
	// 1. Добавить выгрузку всех продуктов через SOAP
	// 2. * Добавить выгрузку заказов пользователя по его имени (защиту не ставим пока)

	// Планы на следующие занятия:
	// - Вернуться к вопросу об изменении цены товара перед оформлением заказа


	public static void main(String[] args) {
		SpringApplication.run(GeekMarketApplication.class, args);
	}
}
