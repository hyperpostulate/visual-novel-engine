package org.mesutormanli.visualNovelEngine;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class SceneConfig {
	
	public static final BorderLayout TEXT_PANEL_LAYOUT = new BorderLayout(10, 10);
	public static final BorderLayout IMAGE_PANEL_LAYOUT = new BorderLayout(10, 10);
	public static final FlowLayout BUTTON_PANEL_LAYOUT = new FlowLayout(FlowLayout.CENTER, 10, 20);

	private int sceneIndex;
	private String text;
	private String imagePath;
	private String westButtonText;
	private int westButtonSceneIndex;
	private String centerButtonText;
	private int centerButtonSceneIndex;
	private String eastButtonText;
	private int eastButtonSceneIndex;

	public SceneConfig(int panelIndex, String text, String imagePath, String westButtonText, int westButtonSceneIndex,
			String centerButtonText, int centerButtonSceneIndex, String eastButtonText, int eastButtonSceneIndex) {
		this.sceneIndex = panelIndex;
		this.text = text;
		this.imagePath = imagePath;
		this.westButtonText = westButtonText;
		this.westButtonSceneIndex = westButtonSceneIndex;
		this.centerButtonText = centerButtonText;
		this.centerButtonSceneIndex = centerButtonSceneIndex;
		this.eastButtonText = eastButtonText;
		this.eastButtonSceneIndex = eastButtonSceneIndex;
	}

	public int getSceneIndex() {
		return sceneIndex;
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

	public int getWestButtonSceneIndex() {
		return westButtonSceneIndex;
	}

	public String getCenterButtonText() {
		return centerButtonText;
	}

	public int getCenterButtonSceneIndex() {
		return centerButtonSceneIndex;
	}

	public String getEastButtonText() {
		return eastButtonText;
	}

	public int getEastButtonSceneIndex() {
		return eastButtonSceneIndex;
	}

}
