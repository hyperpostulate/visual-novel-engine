package org.mesutormanli.visualnovel.engine;

import org.mesutormanli.visualnovel.engine.config.MainConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.stream.Collectors;

class MainFrame extends JFrame {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);
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

        LOGGER.info("Scene " + scene.getSceneConfig().getIndex() + " is present.");

        String possibleStates = scene.getSceneConfig().getButtonConfigList()
                .stream()
                .map(buttonConfig -> String.valueOf(buttonConfig.getTargetSceneIndex()))
                .collect(Collectors.joining(", ", "Possible states are: ", "."));

        LOGGER.info(possibleStates);
    }

    void initialize() {
        setScene(new Scene(0));

    }

}
