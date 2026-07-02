package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.net.URL;
import java.util.stream.Collectors;

class MainFrame extends JFrame {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);
    private static MainFrame instance;
    private Scene scene;
    private MainConfig mainConfig;
    private StoryConfigFactory storyConfigFactory;

    private MainFrame() {
        LOGGER.info("MainFrame invoked.");
    }

    static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    void setScene(Scene scene) {
        if (null != this.scene) {
            remove(this.scene);
        }
        this.scene = scene;
        getContentPane().add(this.scene);
        setVisible(true);

        LOGGER.info("Scene " + scene.getSceneConfig().getIndex() + " is present.");

        String possibleStates = scene.getSceneConfig().getButtonConfigList()
                .stream()
                .map(buttonConfig -> String.valueOf(buttonConfig.getTargetSceneIndex()))
                .collect(Collectors.joining(", ", "Possible states are: ", "."));

        LOGGER.info(possibleStates);
    }

    void initialize(MainConfig mainConfig, StoryConfigFactory storyConfigFactory) {
        this.mainConfig = mainConfig;
        this.storyConfigFactory = storyConfigFactory;

        URL iconUrl = getClass().getResource(mainConfig.getIconPath());
        if (iconUrl != null) {
            setIconImage(new ImageIcon(iconUrl).getImage());
        }
        setTitle(mainConfig.getTitle());
        setSize(mainConfig.getMainFrameDimension());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setScene(new Scene(0, mainConfig, storyConfigFactory));
    }

}
