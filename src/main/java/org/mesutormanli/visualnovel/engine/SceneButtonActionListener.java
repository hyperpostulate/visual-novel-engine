package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SceneButtonActionListener implements ActionListener {

    private final SceneConfig nextSceneConfig;
    private final SceneNavigationHandler navigationHandler;

    SceneButtonActionListener(SceneConfig nextSceneConfig, SceneNavigationHandler navigationHandler) {
        this.nextSceneConfig = nextSceneConfig;
        this.navigationHandler = navigationHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (nextSceneConfig != null) {
            navigationHandler.navigateToScene(nextSceneConfig);
        } else {
            navigationHandler.handleNavigationError();
        }
    }
}
