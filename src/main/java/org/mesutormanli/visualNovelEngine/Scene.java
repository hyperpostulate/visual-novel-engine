package org.mesutormanli.visualNovelEngine;

import lombok.Data;
import org.mesutormanli.visualNovelEngine.config.MainConfig;
import org.mesutormanli.visualNovelEngine.config.StoryConfigFactory;
import org.mesutormanli.visualNovelEngine.config.story.ButtonConfig;
import org.mesutormanli.visualNovelEngine.config.story.SceneConfig;
import org.mesutormanli.visualNovelEngine.util.StringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@Data
@SuppressWarnings("serial")
public class Scene extends JPanel {
    private SceneConfig sceneConfig;
    private JPanel textPanel;
    private JPanel imagePanel;
    private JPanel buttonPanel;

    public Scene(int sceneIndex) {
        this(StoryConfigFactory.getPanelConfig(sceneIndex));

    }

    public Scene(SceneConfig sceneConfig) {

        this.sceneConfig = sceneConfig;
        setLayout(MainConfig.SCENE_LAYOUT);
        setBorder(new TitledBorder(getSceneConfig().getHeadLine()));

        // Text Panel
        this.textPanel = new JPanel(MainConfig.TEXT_PANEL_LAYOUT);
        this.textPanel.add(new JLabel(StringUtils.wrapLabelString(getSceneConfig().getText())));

        // Image Panel
        this.imagePanel = new JPanel(MainConfig.IMAGE_PANEL_LAYOUT);
        this.imagePanel.add(new JLabel(new ImageIcon(
                MainConfig.SCENE_IMAGES_DIR_PATH + getSceneConfig().getHeadLine() + MainConfig.IMAGE_FILE_POSTFIX)));

        // Button Panel
        this.buttonPanel = new JPanel(MainConfig.BUTTON_PANEL_LAYOUT);

        for (ButtonConfig buttonConfig : getSceneConfig().getButtonConfigList()) {
            if (StringUtils.isNotEmpty(buttonConfig.getDescription())) {
                JButton button = new JButton(buttonConfig.getDescription());
                button.addActionListener(new SceneButtonActionListener(buttonConfig.getTargetSceneIndex()));
                this.buttonPanel.add(button);
            }
        }


        // Add subpanels to GamePanel
        add(this.textPanel, new Float(30));
        add(this.imagePanel, new Float(60));
        add(this.buttonPanel, new Float(10));

        // new MP3Player(new File(MainConfig.SCENE_AMBIENT_SOUND_PATH)).play();

    }

}
