package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneButtonActionListener implements ActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SceneButtonActionListener.class);


    private SceneConfig nextSceneConfig;

    SceneButtonActionListener(int nextSceneIndex) {
        this.nextSceneConfig = StoryConfigFactory.getPanelConfig(nextSceneIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (null != nextSceneConfig) {
            Scene nextPanel = new Scene(nextSceneConfig);
            LOGGER.info("Next scene will be " + nextSceneConfig.getIndex() + ".");
            MainFrame.getInstance().setScene(nextPanel);
        } else {
            LOGGER.error("Intended scene is not found in story. Exiting...");
            MainFrame.getInstance().dispose();
        }


    }


}
