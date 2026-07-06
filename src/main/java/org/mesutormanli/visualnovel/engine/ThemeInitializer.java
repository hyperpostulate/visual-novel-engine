package org.mesutormanli.visualnovel.engine;

import com.formdev.flatlaf.FlatDarkLaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
class ThemeInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThemeInitializer.class);

    void initialize() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            LOGGER.warn("Could not set FlatDarkLaf. Using default Look and Feel.", e);
        }
    }
}
