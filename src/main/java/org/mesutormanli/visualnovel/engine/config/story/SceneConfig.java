package org.mesutormanli.visualnovel.engine.config.story;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class SceneConfig {

    private int index;
    private String headLine;
    private String text;

    @XmlElement(name = "button")
    private List<ButtonConfig> buttonConfigList;

    public SceneConfig() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ButtonConfig> getButtonConfigList() {
        return buttonConfigList;
    }

    public void setButtonConfigList(List<ButtonConfig> buttonConfigList) {
        this.buttonConfigList = buttonConfigList;
    }

}
