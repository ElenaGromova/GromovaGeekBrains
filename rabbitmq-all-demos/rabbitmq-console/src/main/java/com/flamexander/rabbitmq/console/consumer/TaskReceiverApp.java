package com.flamexander.rabbitmq.console.consumer;

import com.rabbitmq.client.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskReceiverApp {
    private static final String TASK_QUEUE_NAME = "task_queue";
    private static final  List<String> subscription = new ArrayList<>(Arrays.asList("*.*.java",
                                                                      "articles.programming.java",
                                                                      "articles.*.java",
                                                                      "#.java",
                                                                      "articles.design.*",
                                                                      "articles.*.java"));
    private static final String s = subscription.get((int) (Math.random()*subscription.size()));

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            if (doWork(checkArticle(message), checkArticle(s))){
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };

            System.out.println(" [x] Done  " + "s   = "+ s + "  msg  = " +message + "  !!!!! " + doWork(checkArticle(message), checkArticle(s)));
        };

        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }


    private static boolean doWork(List<String> msg,  List<String> sample) {

        if (sample.get(0).equals("#")) {
            return (sample.get(sample.size()-1)).equals(msg.get(msg.size()-1));
        }
        if (sample.size() != msg.size()){
            return false;
        }
        for(int i = 0; i < sample.size(); i++){
            if (sample.get(i).equals("*")) {
                continue;
            }
            else
                if (!(sample.get(i).equals(msg.get(i))) ){
                return false;
            }
        }
    return true;
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
