package org.mesutormanli.visualnovel.engine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class VisualNovelEngineApplication implements CommandLineRunner {

    private final Director director;

    public VisualNovelEngineApplication(Director director) {
        this.director = director;
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(VisualNovelEngineApplication.class);
        builder.headless(false);
        builder.run(args);
    }

    @Override
    public void run(String... args) {
        director.action();
    }
}
