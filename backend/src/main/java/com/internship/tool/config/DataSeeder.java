package com.internship.tool.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(CrisisRepository repo) {
        return args -> {

            // ⚠️ Prevent duplicate seeding
            if (repo.count() > 0) return;

            List<Crisis> data = Arrays.asList(
                new Crisis("Server Down", "Main server failure", "ongoing", "1st"),
                new Crisis("Database Issue", "Connection timeout", "ongoing", "2nd"),
                new Crisis("Login Bug", "Users cannot login", "closed", "1st"),
                new Crisis("Payment Error", "Transaction failed", "ongoing", "1st"),
                new Crisis("UI Crash", "Frontend crash", "closed", "3rd"),
                new Crisis("API Failure", "500 error", "ongoing", "2nd"),
                new Crisis("Security Alert", "Suspicious login", "ongoing", "1st"),
                new Crisis("Cache Issue", "Data not updating", "closed", "3rd"),
                new Crisis("Deployment Fail", "Build failed", "ongoing", "2nd"),
                new Crisis("Network Lag", "Slow response", "closed", "3rd"),
                new Crisis("Disk Full", "Storage issue", "ongoing", "1st"),
                new Crisis("Email Not Sending", "SMTP error", "closed", "2nd"),
                new Crisis("Auth Error", "Token invalid", "ongoing", "1st"),
                new Crisis("Memory Leak", "App slowing down", "ongoing", "1st"),
                new Crisis("Crash Report", "Unexpected crash", "closed", "2nd")
            );

            repo.saveAll(data);

            System.out.println("🔥 15 Demo Records Inserted!");
        };
    }
}