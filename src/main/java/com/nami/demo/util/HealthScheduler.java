package com.nami.demo.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HealthScheduler {

    @Scheduled(fixedRate = 840000)
    public void checkHealth() {
        System.out.println("Health automático: OK");
    }
}