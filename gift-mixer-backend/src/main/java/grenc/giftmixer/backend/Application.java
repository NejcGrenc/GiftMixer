package grenc.giftmixer.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"grenc.giftmixer", "eu.grenc.secureeye.client"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}