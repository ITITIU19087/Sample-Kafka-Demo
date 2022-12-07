package com.antnest.kafka.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Bean
    public void testJob(){
        System.out.println("Job is running");
    }
}
