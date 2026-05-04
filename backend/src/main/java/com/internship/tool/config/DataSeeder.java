package com.internship.tool.config;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private CrisisRepository repo;

    @Override
    public void run(String... args) {

        if (repo.count() == 0) {

            for (int i = 1; i <= 30; i++) {
                Crisis c = new Crisis();
                c.setTitle("Crisis " + i);
                c.setDescription("Sample description " + i);
                c.setSeverity("MEDIUM");

                repo.save(c);
            }

            System.out.println("✅ 30 Crisis records inserted");
        }
    }
}