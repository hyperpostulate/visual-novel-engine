package org.mesutormanli.visualnovel.engine;

import com.formdev.flatlaf.FlatDarkLaf;
import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;

import static java.awt.EventQueue.invokeLater;

@Component
class Director {

    private static final Logger LOGGER = LoggerFactory.getLogger(Director.class);

    private final MainConfig mainConfig;
    private final StoryConfigFactory storyConfigFactory;

    Director(MainConfig mainConfig, StoryConfigFactory storyConfigFactory) {
        this.mainConfig = mainConfig;
        this.storyConfigFactory = storyConfigFactory;
    }

    void action() {
        invokeLater(this::run);
    }

    private void run() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            MainFrame.getInstance().initialize(mainConfig, storyConfigFactory);
        } catch (Exception e) {
            MainFrame.getInstance().dispose();
            LOGGER.error("MainFrame could not be initialized. Exiting...", e);
        }
    }
}
