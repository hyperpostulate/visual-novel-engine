package org.mesutormanli.visualNovelEngine;

import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import static java.awt.EventQueue.invokeLater;

class Director {

    private static final Logger LOGGER = LoggerFactory.getLogger(Director.class);

    static void action() {
        invokeLater(Director::run);

    }

    private static void run() {
        try {
            UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
            MainFrame.getInstance().initialize();
        } catch (Exception e) {
            MainFrame.getInstance().dispose();
            LOGGER.error("MainFrame could not be initialized. Exiting...", e);
        }
    }
}
