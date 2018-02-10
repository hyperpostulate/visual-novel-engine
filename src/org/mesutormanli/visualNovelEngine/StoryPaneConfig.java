package org.mesutormanli.visualNovelEngine;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class StoryPaneConfig {
	
	public static final BorderLayout TEXT_PANEL_LAYOUT = new BorderLayout(10, 10);
	public static final BorderLayout IMAGE_PANEL_LAYOUT = new BorderLayout(10, 10);
	public static final FlowLayout BUTTON_PANEL_LAYOUT = new FlowLayout(FlowLayout.CENTER, 10, 20);

	private int panelIndex;
	private String text;
	private String imagePath;
	private String westButtonText;
	private int westButtonPanelIndex;
	private String centerButtonText;
	private int centerButtonPanelIndex;
	private String eastButtonText;
	private int eastButtonPanelIndex;

	public StoryPaneConfig(int panelIndex, String text, String imagePath, String westButtonText, int westButtonPanelIndex,
			String centerButtonText, int centerButtonPanelIndex, String eastButtonText, int eastButtonPanelIndex) {
		this.panelIndex = panelIndex;
		this.text = text;
		this.imagePath = imagePath;
		this.westButtonText = westButtonText;
		this.westButtonPanelIndex = westButtonPanelIndex;
		this.centerButtonText = centerButtonText;
		this.centerButtonPanelIndex = centerButtonPanelIndex;
		this.eastButtonText = eastButtonText;
		this.eastButtonPanelIndex = eastButtonPanelIndex;
	}

	public int getPanelIndex() {
		return panelIndex;
	}

	public String getText() {
		return text;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getWestButtonText() {
		return westButtonText;
	}

	public int getWestButtonPanelIndex() {
		return westButtonPanelIndex;
	}

	public String getCenterButtonText() {
		return centerButtonText;
	}

	public int getCenterButtonPanelIndex() {
		return centerButtonPanelIndex;
	}

	public String getEastButtonText() {
		return eastButtonText;
	}

	public int getEastButtonPanelIndex() {
		return eastButtonPanelIndex;
	}

}
