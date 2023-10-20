package com.swp391.maid4uni;

import com.swp391.maid4uni.entity.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * The type App.
 */
@SpringBootApplication
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
    }

}
