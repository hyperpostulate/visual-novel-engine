package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.SceneLayoutFactory;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.net.URL;

class MainFrame extends JFrame implements SceneNavigationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);
    private static MainFrame instance;

    private Scene scene;
    private MainConfig mainConfig;
    private SceneLayoutFactory sceneLayoutFactory;
    private StoryConfigFactory storyConfigFactory;
    private SceneStateLogger sceneStateLogger;

    private MainFrame() {
        LOGGER.info("MainFrame invoked.");
    }

    static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    @Override
    public void navigateToScene(SceneConfig sceneConfig) {
        LOGGER.info("Next scene will be {}.", sceneConfig.getIndex());
        Scene nextPanel = new Scene(sceneConfig, mainConfig, sceneLayoutFactory, storyConfigFactory, this);
        setScene(nextPanel);
    }

    @Override
    public void handleNavigationError() {
        sceneStateLogger.logNavigationError();
        dispose();
    }

    void setScene(Scene scene) {
        if (this.scene != null) {
            remove(this.scene);
        }
        this.scene = scene;
        getContentPane().add(this.scene);
        setVisible(true);
        sceneStateLogger.logSceneEntered(scene.getSceneConfig());
    }

    void initialize(MainConfig mainConfig, SceneLayoutFactory sceneLayoutFactory, StoryConfigFactory storyConfigFactory, SceneStateLogger sceneStateLogger) {
        this.mainConfig = mainConfig;
        this.sceneLayoutFactory = sceneLayoutFactory;
        this.storyConfigFactory = storyConfigFactory;
        this.sceneStateLogger = sceneStateLogger;

        URL iconUrl = getClass().getResource(mainConfig.getIconPath());
        if (iconUrl != null) {
            setIconImage(new ImageIcon(iconUrl).getImage());
        }
        setTitle(mainConfig.getTitle());
        setSize(mainConfig.getMainFrameDimension());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setScene(new Scene(0, mainConfig, sceneLayoutFactory, storyConfigFactory, this));
    }
}
