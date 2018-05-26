package org.mesutormanli.visualNovelEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.mesutormanli.visualNovelEngine.config.SceneConfig;
import org.mesutormanli.visualNovelEngine.config.SceneConfigFactory;

public class SceneButtonActionListener implements ActionListener {
	public static final Logger LOGGER = Logger.getLogger(SceneButtonActionListener.class);
	private SceneConfig nextSceneConfig;

	public SceneButtonActionListener(int nextSceneIndex) {
		this.nextSceneConfig = SceneConfigFactory.getPanelConfig(nextSceneIndex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(null != nextSceneConfig) {
			Scene nextPanel = new Scene(nextSceneConfig);
			LOGGER.info("Next scene will be "+ nextSceneConfig.getSceneIndex() + ".");
			MainFrame.getInstance().setScene(nextPanel);
		}else {
			LOGGER.error("Intended scene is not found in scene configuration. Exiting...");
			MainFrame.getInstance().dispose();
		}

		
	}
	

}
