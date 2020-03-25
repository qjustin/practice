package com.coding.training.file;

import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Set<String> rabbitmq = FileUtil.readLineFromFile("d://rabbitmq.txt");
        Set<String> eureka = FileUtil.readLineFromFile("d://eureka.txt");

        for (String ip : rabbitmq) {
            for (String eip : eureka) {
                if (eip.contains(ip)) {
                    System.out.println(String.format("ip %s, eureka %s", ip, eip));
                }
            }
        }
    }
}
