package com.flamexander.rabbitmq.console.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskProducerApp {
    private static final String TASK_QUEUE_NAME = "task_queue";
    private static final List<String> messages = new ArrayList<>(Arrays.asList("tests.programming.java",
                                                                      "tests.design.java",
                                                                      "articles.programming.java",
                                                                      "articles.design.java",
                                                                      "articles.best.java",
                                                                      "articles.programming.php",
                                                                      "articles.design.php",
                                                                      "articles.practice.java",
                                                                      "articles.programming.practice.java",
                                                                      "articles.design.c",
                                                                      "articles.programming.practice.best.java",
                                                                      "tests.best.java"));
    enum Topic{
        PROGRAMMING,
        DESIGN,
        BEST
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); 
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            Scanner sc = new Scanner(System.in);
            int number;
            ArrayList option = new ArrayList(Arrays.asList(1, 2, 3, 4));
            do {
                System.out.println("Введите число: " + "\n" +
                                                   "1 - рандомные статьи; " + "\n" +
                                                   "2 - статьи на тему PROGRAMMING; " + "\n" +
                                                   "3 - статьи на тему DESIGN; " + "\n" +
                                                   "4 - статьи на тему BEST; "+ "\n");
                number = sc.nextInt();
            }
            while (!option.contains(number));
        switch (number) {
            case 1:
                for (int i = 0; i < 20; i++) {
                    String message = messages.get((int) (Math.random() * messages.size()));
                    channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                    System.out.println(" [x] Sent '" + message + "'");
                    break;
                }
            case 2: sendMsg(Topic.PROGRAMMING.toString(), channel); break;
            case 3: sendMsg(Topic.DESIGN.toString(), channel); break;
            case 4: sendMsg(Topic.BEST.toString(), channel); break;
        }
        }
    }

    private static void sendMsg(String topicName, Channel channel) throws IOException {
        List<String> msgToSend = checkTopic(topicName);
        for (int i = 0; i < msgToSend.size(); i++){
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msgToSend.get(i).getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + msgToSend.get(i) + "'");
        }
    }
    
    private static List<String> checkTopic(String tp){
        System.out.println("Sending articles on the topic: " + tp);
        List<String> topicArticles = new ArrayList<>();
        for(int i = 0; i < messages.size(); i++){
           if (checkArticle(messages.get(i)).get(1).equalsIgnoreCase(tp)){
                topicArticles.add(messages.get(i));
           }
        }
    return topicArticles;
    }

    private static List<String> checkArticle(String str) {
        List<String> words = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                words.add(str.substring(start,i));
                start = i + 1;
                end = i+1;
            }
        }
        words.add(str.substring(end,(str.length())));
        return words;
    }
}
