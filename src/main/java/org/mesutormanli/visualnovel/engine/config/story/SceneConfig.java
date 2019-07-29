package org.mesutormanli.visualnovel.engine.config.story;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SceneConfig {

    private int index;
    private String headLine;
    private String text;

    @XmlElement(name = "button")
    private List<ButtonConfig> buttonConfigList;

}
