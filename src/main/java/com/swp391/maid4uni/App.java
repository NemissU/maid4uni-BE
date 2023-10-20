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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class App implements CommandLineRunner {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

    }
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void run(String... args) throws Exception {

        accountRepository.deleteAll();
        Account account = new Account();
        account = Account.builder()
                .role(Role.ADMIN)
                .email("email")
                .img("img")
                .address("address")
                .gender("gender")
                .address("address")
                .fullName("fullname")
                .username("admin")
                .password("admin")
                .phoneNumber("0123456789")
                .build();
        accountRepository.save(account);
        System.out.println(account);
    }
}
