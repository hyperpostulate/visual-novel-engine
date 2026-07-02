package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.mesutormanli.visualnovel.engine.util.StringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.net.URL;

public class Scene extends JPanel {
    private SceneConfig sceneConfig;
    private JPanel textPanel;
    private JPanel imagePanel;
    private JPanel buttonPanel;

    public Scene(int sceneIndex, MainConfig mainConfig, StoryConfigFactory storyConfigFactory) {
        this(storyConfigFactory.getPanelConfig(sceneIndex), mainConfig, storyConfigFactory);
    }

    public Scene(SceneConfig sceneConfig, MainConfig mainConfig, StoryConfigFactory storyConfigFactory) {

        this.sceneConfig = sceneConfig;
        setLayout(mainConfig.getSceneLayout());
        setBorder(new TitledBorder(sceneConfig.getHeadLine()));

        // Text Panel
        this.textPanel = new JPanel(mainConfig.getTextPanelLayout());
        this.textPanel.add(new JLabel(StringUtils.wrapLabelString(sceneConfig.getText())));

        // Image Panel - load from classpath
        this.imagePanel = new JPanel(mainConfig.getImagePanelLayout());
        URL imageUrl = getClass().getResource(
                mainConfig.getSceneImagesDirPath() + sceneConfig.getHeadLine() + mainConfig.getImageFilePostfix());
        ImageIcon imageIcon;
        if (imageUrl != null) {
            imageIcon = new ImageIcon(imageUrl);
        } else {
            imageIcon = new ImageIcon();
        }
        this.imagePanel.add(new JLabel(imageIcon));

        // Button Panel
        this.buttonPanel = new JPanel(mainConfig.getButtonPanelLayout());

        sceneConfig.getButtonConfigList()
                .stream()
                .filter(buttonConfig -> StringUtils.isNotEmpty(buttonConfig.getDescription()))
                .forEachOrdered(buttonConfig -> {
                    JButton button = new JButton(buttonConfig.getDescription());
                    button.addActionListener(new SceneButtonActionListener(
                            buttonConfig.getTargetSceneIndex(), mainConfig, storyConfigFactory));
                    this.buttonPanel.add(button);
                });

        // Add subpanels to GamePanel
        add(this.textPanel, new Float(30));
        add(this.imagePanel, new Float(60));
        add(this.buttonPanel, new Float(10));
    }

    public SceneConfig getSceneConfig() {
        return sceneConfig;
    }

}
