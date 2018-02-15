package org.mesutormanli.visualNovelEngine.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SceneConfig {

	private int sceneIndex;
	private String text;
	private String westButtonText;
	private int westButtonSceneIndex;
	private String centerButtonText;
	private int centerButtonSceneIndex;
	private String eastButtonText;
	private int eastButtonSceneIndex;

	public SceneConfig() {
		//Unused no-arg constructor required by JAXB.
	}

	public SceneConfig(int panelIndex, String text, String westButtonText, int westButtonSceneIndex,
			String centerButtonText, int centerButtonSceneIndex, String eastButtonText, int eastButtonSceneIndex) {
		this.sceneIndex = panelIndex;
		this.text = text;
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
