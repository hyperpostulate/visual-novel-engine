package org.mesutormanli.visualNovelEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneButtonActionListener implements ActionListener {
	private SceneConfig nextSceneConfig;

	public SceneButtonActionListener(int nextSceneIndex) {
		this.nextSceneConfig = SceneConfigFactory.getPanelConfig(nextSceneIndex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(null != nextSceneConfig) {
			Scene nextPanel = new Scene(nextSceneConfig);
			MainFrame.getInstance().setScene(nextPanel);
		}else {
			MainFrame.getInstance().dispose();
		}

		
	}
	

}
