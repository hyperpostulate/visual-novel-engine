package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;

interface SceneNavigationHandler {
    void navigateToScene(SceneConfig sceneConfig);
    void handleNavigationError();
}
