package org.mesutormanli.visualNovelEngine.config.story;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ButtonConfig {

    private String description;
    private int targetSceneIndex;

}
