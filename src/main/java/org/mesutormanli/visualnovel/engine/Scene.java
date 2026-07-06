package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.SceneLayoutFactory;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Scene extends JPanel {
    private final SceneConfig sceneConfig;

    public Scene(int sceneIndex, MainConfig mainConfig, SceneLayoutFactory sceneLayoutFactory, StoryConfigFactory storyConfigFactory, SceneNavigationHandler navigationHandler) {
        this(storyConfigFactory.getPanelConfig(sceneIndex), mainConfig, sceneLayoutFactory, storyConfigFactory, navigationHandler);
    }

    public Scene(SceneConfig sceneConfig, MainConfig mainConfig, SceneLayoutFactory sceneLayoutFactory, StoryConfigFactory storyConfigFactory, SceneNavigationHandler navigationHandler) {
        this.sceneConfig = sceneConfig;

        ScenePanelBuilder panelBuilder = new ScenePanelBuilder(mainConfig, sceneLayoutFactory, storyConfigFactory);

        setLayout(sceneLayoutFactory.createSceneLayout());
        setBorder(new TitledBorder(sceneConfig.getHeadLine()));

        add(panelBuilder.buildTextPanel(sceneConfig), new Float(30));
        add(panelBuilder.buildImagePanel(sceneConfig), new Float(60));
        add(panelBuilder.buildButtonPanel(sceneConfig, navigationHandler), new Float(10));
    }

    public SceneConfig getSceneConfig() {
        return sceneConfig;
    }
}
