package com.swp391.maid4uni;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Role;
import com.swp391.maid4uni.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Date;

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
