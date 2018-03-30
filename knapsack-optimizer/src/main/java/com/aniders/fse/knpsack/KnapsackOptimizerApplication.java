package com.aniders.fse.knpsack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KnapsackOptimizerApplication {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(KnapsackOptimizerApplication.class, args);
    }
}
