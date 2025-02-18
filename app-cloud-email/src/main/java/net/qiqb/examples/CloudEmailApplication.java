package net.qiqb.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.function.Consumer;

@SpringBootApplication
public class CloudEmailApplication {
    public static void main(String[] args) {

        SpringApplication.run(CloudEmailApplication.class, args);
    }


}
