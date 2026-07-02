package org.mesutormanli.visualnovel.engine.config.story;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "story")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoryConfig {

    @XmlElement(name = "scene")
    private List<SceneConfig> sceneConfigList;

    public StoryConfig() {
    }

    public List<SceneConfig> getSceneConfigList() {
        return sceneConfigList;
    }

    public void setSceneConfigList(List<SceneConfig> sceneConfigList) {
        this.sceneConfigList = sceneConfigList;
    }

}
