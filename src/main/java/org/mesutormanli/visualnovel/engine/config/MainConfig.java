package org.mesutormanli.visualnovel.engine.config;

import org.mesutormanli.visualnovel.engine.util.RelativeLayout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class MainConfig {

    private final String title;
    private final Dimension mainFrameDimension;
    private final String iconPath;
    private final String storyConfigPath;
    private final String sceneImagesDirPath;
    private final String imageFilePostfix;

    public MainConfig(
            @Value("${app.title:Visual Novel Engine}") String title,
            @Value("${app.width:800}") int width,
            @Value("${app.height:600}") int height,
            @Value("${app.icon-path:/icon.png}") String iconPath,
            @Value("${app.story-config-path:story.xml}") String storyConfigPath,
            @Value("${app.scene-images-dir:/images/}") String sceneImagesDirPath,
            @Value("${app.image-file-postfix:.gif}") String imageFilePostfix
    ) {
        this.title = title;
        this.mainFrameDimension = new Dimension(width, height);
        this.iconPath = iconPath;
        this.storyConfigPath = storyConfigPath;
        this.sceneImagesDirPath = sceneImagesDirPath;
        this.imageFilePostfix = imageFilePostfix;
    }

    public String getTitle() { return title; }
    public Dimension getMainFrameDimension() { return mainFrameDimension; }
    public String getIconPath() { return iconPath; }
    public String getStoryConfigPath() { return storyConfigPath; }
    public String getSceneImagesDirPath() { return sceneImagesDirPath; }
    public String getImageFilePostfix() { return imageFilePostfix; }

    public RelativeLayout getSceneLayout() { return new RelativeLayout(RelativeLayout.Y_AXIS); }
    public RelativeLayout getTextPanelLayout() { return new RelativeLayout(RelativeLayout.Y_AXIS); }
    public RelativeLayout getImagePanelLayout() { return new RelativeLayout(RelativeLayout.Y_AXIS); }
    public FlowLayout getButtonPanelLayout() { return new FlowLayout(FlowLayout.CENTER, 10, 20); }
}
