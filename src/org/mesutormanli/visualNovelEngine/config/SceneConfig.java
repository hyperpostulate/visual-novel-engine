package org.mesutormanli.visualNovelEngine.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SceneConfig {

	private int sceneIndex;
	private String sceneHeadline;
	private String sceneText;
	private String westButtonText;
	private int westButtonSceneIndex;
	private String centerButtonText;
	private int centerButtonSceneIndex;
	private String eastButtonText;
	private int eastButtonSceneIndex;

	public SceneConfig() {
		//Unused no-arg constructor required by JAXB.
	}

	public SceneConfig(int sceneIndex, String sceneHeadline, String sceneText, String westButtonText, int westButtonSceneIndex,
			String centerButtonText, int centerButtonSceneIndex, String eastButtonText, int eastButtonSceneIndex) {
		this.sceneIndex = sceneIndex;
		this.sceneHeadline = sceneHeadline;
		this.sceneText = sceneText;
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

	public String getSceneText() {
		return sceneText;
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

	public String getSceneHeadline() {
		return sceneHeadline;
	}

	public void setSceneHeadline(String sceneHeadline) {
		this.sceneHeadline = sceneHeadline;
	}

}
