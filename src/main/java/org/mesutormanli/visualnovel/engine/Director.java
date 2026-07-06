package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.SceneLayoutFactory;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.awt.EventQueue.invokeLater;

@Component
class Director {

    private static final Logger LOGGER = LoggerFactory.getLogger(Director.class);

    private final MainConfig mainConfig;
    private final SceneLayoutFactory sceneLayoutFactory;
    private final StoryConfigFactory storyConfigFactory;
    private final ThemeInitializer themeInitializer;
    private final SceneStateLogger sceneStateLogger;

    Director(MainConfig mainConfig, SceneLayoutFactory sceneLayoutFactory,
             StoryConfigFactory storyConfigFactory, ThemeInitializer themeInitializer,
             SceneStateLogger sceneStateLogger) {
        this.mainConfig = mainConfig;
        this.sceneLayoutFactory = sceneLayoutFactory;
        this.storyConfigFactory = storyConfigFactory;
        this.themeInitializer = themeInitializer;
        this.sceneStateLogger = sceneStateLogger;
    }

    void action() {
        invokeLater(this::run);
    }

    private void run() {
        try {
            themeInitializer.initialize();
            MainFrame.getInstance().initialize(mainConfig, sceneLayoutFactory, storyConfigFactory, sceneStateLogger);
        } catch (Exception e) {
            MainFrame.getInstance().dispose();
            LOGGER.error("MainFrame could not be initialized. Exiting...", e);
        }
    }
}
