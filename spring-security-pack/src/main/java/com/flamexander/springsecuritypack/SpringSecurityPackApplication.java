package com.flamexander.springsecuritypack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityPackApplication {
	// Домашнее задание:
	// 1. Добавить в проект Dao Authentication Provider (
	// +все сопутствующие репозитории, сервисы, сущности, конфиги)
	// 2. Доступ к оформлению заказов и к самим заказам могут
	// иметь только вошедшие пользователи
	// 3. Заказ должен привязываться к пользователю

	// Комментарии:
	// 1. Можете форму логина стандартную использовать

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPackApplication.class, args);
	}
}
