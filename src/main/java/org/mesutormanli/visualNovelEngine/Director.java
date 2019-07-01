package org.mesutormanli.visualNovelEngine;

import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;

import javax.swing.*;

import static java.awt.EventQueue.invokeLater;

class Director {

    static void action() {
        invokeLater(Director::run);

    }

    private static void run() {
        try {
            UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
            MainFrame.getInstance().initialize();
        } catch (Exception e) {
            MainFrame.getInstance().dispose();
            System.out.println("MainFrame could not be initialized. Exiting...");
        }
    }
}
