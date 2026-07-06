package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.SceneLayoutFactory;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.mesutormanli.visualnovel.engine.config.story.ButtonConfig;
import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.mesutormanli.visualnovel.engine.util.StringUtils;

import javax.swing.*;
import java.net.URL;

class ScenePanelBuilder {

    private final MainConfig mainConfig;
    private final SceneLayoutFactory sceneLayoutFactory;
    private final StoryConfigFactory storyConfigFactory;

    ScenePanelBuilder(MainConfig mainConfig, SceneLayoutFactory sceneLayoutFactory, StoryConfigFactory storyConfigFactory) {
        this.mainConfig = mainConfig;
        this.sceneLayoutFactory = sceneLayoutFactory;
        this.storyConfigFactory = storyConfigFactory;
    }

    JPanel buildTextPanel(SceneConfig sceneConfig) {
        JPanel textPanel = new JPanel(sceneLayoutFactory.createTextPanelLayout());
        textPanel.add(new JLabel(StringUtils.wrapLabelString(sceneConfig.getText())));
        return textPanel;
    }

    JPanel buildImagePanel(SceneConfig sceneConfig) {
        JPanel imagePanel = new JPanel(sceneLayoutFactory.createImagePanelLayout());
        URL imageUrl = getClass().getResource(
                mainConfig.getSceneImagesDirPath() + sceneConfig.getHeadLine() + mainConfig.getImageFilePostfix());
        ImageIcon imageIcon = (imageUrl != null) ? new ImageIcon(imageUrl) : new ImageIcon();
        imagePanel.add(new JLabel(imageIcon));
        return imagePanel;
    }

    JPanel buildButtonPanel(SceneConfig sceneConfig, SceneNavigationHandler navigationHandler) {
        JPanel buttonPanel = new JPanel(sceneLayoutFactory.createButtonPanelLayout());

        sceneConfig.getButtonConfigList()
                .stream()
                .filter(buttonConfig -> StringUtils.isNotEmpty(buttonConfig.getDescription()))
                .forEachOrdered(buttonConfig -> {
                    JButton button = new JButton(buttonConfig.getDescription());
                    SceneConfig nextSceneConfig = storyConfigFactory.getPanelConfig(buttonConfig.getTargetSceneIndex());
                    button.addActionListener(new SceneButtonActionListener(nextSceneConfig, navigationHandler));
                    buttonPanel.add(button);
                });

        return buttonPanel;
    }
}
