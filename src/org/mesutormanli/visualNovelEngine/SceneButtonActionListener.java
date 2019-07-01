package org.mesutormanli.visualNovelEngine;

import org.mesutormanli.visualNovelEngine.config.StoryConfigFactory;
import org.mesutormanli.visualNovelEngine.config.story.SceneConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneButtonActionListener implements ActionListener {
    private SceneConfig nextSceneConfig;

    SceneButtonActionListener(int nextSceneIndex) {
        this.nextSceneConfig = StoryConfigFactory.getPanelConfig(nextSceneIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (null != nextSceneConfig) {
            Scene nextPanel = new Scene(nextSceneConfig);
            System.out.println("Next scene will be " + nextSceneConfig.getIndex() + ".");
            MainFrame.getInstance().setScene(nextPanel);
        } else {
            System.out.println("Intended scene is not found in scene configuration. Exiting...");
            MainFrame.getInstance().dispose();
        }


    }


}
