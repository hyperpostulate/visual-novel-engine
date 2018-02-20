package org.mesutormanli.visualNovelEngine;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Director {
	private static final Logger LOGGER = LoggerFactory.getLogger(Director.class);

	public static void action() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					//UIManager.setLookAndFeel(new NimbusLookAndFeel());
					UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
					MainFrame.getInstance().initialize();
				} catch (Exception e) {
					MainFrame.getInstance().dispose();
					LOGGER.error("MainFrame could not be initialized. Exiting...", e);
				}
			}

		});

	}

}
