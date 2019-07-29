package org.mesutormanli.visualnovel.engine;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.mesutormanli.visualnovel.engine.config.StoryConfigFactory;
import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.mesutormanli.visualnovel.engine.util.StringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@Data
@EqualsAndHashCode(callSuper = true)
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

        this.sceneConfig.getButtonConfigList()
                .stream()
                .filter(buttonConfig -> StringUtils.isNotEmpty(buttonConfig.getDescription()))
                .forEachOrdered(buttonConfig -> {
                    JButton button = new JButton(buttonConfig.getDescription());
                    button.addActionListener(new SceneButtonActionListener(buttonConfig.getTargetSceneIndex()));
                    this.buttonPanel.add(button);
                });


        // Add subpanels to GamePanel
        add(this.textPanel, new Float(30));
        add(this.imagePanel, new Float(60));
        add(this.buttonPanel, new Float(10));

        // new MP3Player(new File(MainConfig.SCENE_AMBIENT_SOUND_PATH)).play();

    }

}
