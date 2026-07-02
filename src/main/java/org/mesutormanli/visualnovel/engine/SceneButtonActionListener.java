package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneButtonActionListener implements ActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SceneButtonActionListener.class);

    private SceneConfig nextSceneConfig;
    private MainConfig mainConfig;
    private StoryConfigFactory storyConfigFactory;

    SceneButtonActionListener(int nextSceneIndex, MainConfig mainConfig, StoryConfigFactory storyConfigFactory) {
        this.nextSceneConfig = storyConfigFactory.getPanelConfig(nextSceneIndex);
        this.mainConfig = mainConfig;
        this.storyConfigFactory = storyConfigFactory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (null != nextSceneConfig) {
            Scene nextPanel = new Scene(nextSceneConfig, mainConfig, storyConfigFactory);
            LOGGER.info("Next scene will be " + nextSceneConfig.getIndex() + ".");
            MainFrame.getInstance().setScene(nextPanel);
        } else {
            LOGGER.error("Intended scene is not found in story. Exiting...");
            MainFrame.getInstance().dispose();
        }
    }

}
