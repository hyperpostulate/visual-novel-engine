package org.mesutormanli.visualnovel.engine.config.story;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ButtonConfig {

    private String description;
    private int targetSceneIndex;

    public ButtonConfig() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTargetSceneIndex() {
        return targetSceneIndex;
    }

    public void setTargetSceneIndex(int targetSceneIndex) {
        this.targetSceneIndex = targetSceneIndex;
    }

}
