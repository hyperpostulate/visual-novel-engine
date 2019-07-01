package org.mesutormanli.visualNovelEngine.config.story;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "story")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoryConfig {

    @XmlElement(name = "scene")
    private List<SceneConfig> sceneConfigList;

}
