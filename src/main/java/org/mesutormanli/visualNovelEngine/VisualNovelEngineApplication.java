package org.mesutormanli.visualNovelEngine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class VisualNovelEngineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(VisualNovelEngineApplication.class);
        builder.headless(false);
        builder.run(args);
    }

    @Override
    public void run(String... args) {
        Director.action();
    }
}
