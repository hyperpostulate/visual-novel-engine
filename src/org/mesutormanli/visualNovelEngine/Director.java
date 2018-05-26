package org.mesutormanli.visualNovelEngine;

import org.apache.log4j.Logger;
import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;

import javax.swing.*;

import static java.awt.EventQueue.*;

public class Director {
	public static final Logger LOGGER = Logger.getLogger(Director.class);

	public static void action() {
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
