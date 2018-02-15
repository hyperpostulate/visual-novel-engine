package org.mesutormanli.visualNovelEngine.config;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sceneConfigContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class SceneConfigContainer {
	@XmlElement(name = "sceneConfig")
	private ArrayList<SceneConfig> sceneConfigList;

	public ArrayList<SceneConfig> getSceneConfigList() {
		return sceneConfigList;
	}

}
