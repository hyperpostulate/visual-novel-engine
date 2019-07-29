package org.mesutormanli.visualnovel.engine.config.story;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ButtonConfig {

    private String description;
    private int targetSceneIndex;

}
