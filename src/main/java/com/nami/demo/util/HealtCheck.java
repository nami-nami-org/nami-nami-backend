package com.nami.demo.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping("health")
public class HealtCheck {

    @GetMapping
    public String health() {
        return "OK";
    }

    @Scheduled(cron = "0 */14 * * * *")
    public void autoHealthCheck() {
        System.out.println("Running scheduled health check...");
        String status = health();
        System.out.println("Health status: " + status);
    }

}
