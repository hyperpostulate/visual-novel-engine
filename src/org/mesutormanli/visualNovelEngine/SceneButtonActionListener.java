package org.mesutormanli.visualNovelEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonActionListener implements ActionListener {
	private StoryPaneConfig nextPanelConfig;

	public GameButtonActionListener(int nextPanelIndex) {
		this.nextPanelConfig = StoryPaneConfigFactory.getPanelConfig(nextPanelIndex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(null != nextPanelConfig) {
			StoryPane nextPanel = new StoryPane(nextPanelConfig);
			MainFrame.getInstance().setGamePanel(nextPanel);
		}else {
			MainFrame.getInstance().dispose();
		}

		
	}
	

}
