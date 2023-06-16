package com.shahian.allergytracker;

import com.shahian.allergytracker.model.dataBaseInitializing.DatabaseInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AllergyTrackerApplication implements CommandLineRunner {
    private final DatabaseInitializer databaseInitializer;

    public AllergyTrackerApplication(DatabaseInitializer databaseInitializer) {
        this.databaseInitializer = databaseInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(AllergyTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        databaseInitializer.run(args);
    }
}
