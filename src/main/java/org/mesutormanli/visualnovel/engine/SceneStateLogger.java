package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
class SceneStateLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(SceneStateLogger.class);

    void logSceneEntered(SceneConfig sceneConfig) {
        LOGGER.info("Scene {} is present.", sceneConfig.getIndex());
        logPossibleStates(sceneConfig);
    }

    void logSceneTransition(int fromSceneIndex, int toSceneIndex) {
        LOGGER.info("Transitioning from scene {} to scene {}.", fromSceneIndex, toSceneIndex);
    }

    void logNavigationError() {
        LOGGER.error("Intended scene is not found in story. Exiting...");
    }

    private void logPossibleStates(SceneConfig sceneConfig) {
        String possibleStates = sceneConfig.getButtonConfigList()
                .stream()
                .map(buttonConfig -> String.valueOf(buttonConfig.getTargetSceneIndex()))
                .collect(Collectors.joining(", ", "Possible states are: ", "."));
        LOGGER.info(possibleStates);
    }
}
