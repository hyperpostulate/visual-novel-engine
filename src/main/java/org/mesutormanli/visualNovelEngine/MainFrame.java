package org.mesutormanli.visualNovelEngine;

import org.mesutormanli.visualNovelEngine.config.MainConfig;
import org.mesutormanli.visualNovelEngine.config.story.ButtonConfig;

import javax.swing.*;

class MainFrame extends JFrame {
    private static MainFrame instance;
    private Scene scene;

    private MainFrame() {
        System.out.println("MainFrame invoked.");

        setIconImage(new ImageIcon(MainConfig.MAIN_FRAME_ICON_PATH).getImage());
        setTitle(MainConfig.TITLE);
        setSize(MainConfig.MAIN_FRAME_DIMENSION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    void setScene(Scene scene) {
        if (null != this.scene) {
            remove(this.scene);
        }
        this.scene = scene;
        getContentPane().add(this.scene);
        setVisible(true);
        System.out.println("Scene " + scene.getSceneConfig().getIndex() + " is present.");
        System.out.println("Possible states are: ");
        for (ButtonConfig buttonConfig : scene.getSceneConfig().getButtonConfigList()) {
            System.out.println(buttonConfig.getTargetSceneIndex());
        }
    }

    void initialize() {
        setScene(new Scene(0));

    }

}
