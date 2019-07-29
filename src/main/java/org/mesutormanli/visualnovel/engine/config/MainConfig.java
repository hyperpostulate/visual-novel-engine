package org.mesutormanli.visualnovel.engine.config;

import org.mesutormanli.visualnovel.engine.util.RelativeLayout;

import java.awt.*;


public class MainConfig {

    public static final String TITLE = "Visual Novel Engine";
    public static final Dimension MAIN_FRAME_DIMENSION = new Dimension(800, 600);
    public static final String MAIN_FRAME_ICON_PATH = "./resources/icon.png";
    public static final String STORY_CONFIG_FILE_PATH = "./resources/story.xml";
    public static final String SCENE_AMBIENT_SOUND_PATH = "./resources/sounds/ambient.mp3";
    public static final String SCENE_IMAGES_DIR_PATH = "./resources/images/";
    public static final RelativeLayout SCENE_LAYOUT = new RelativeLayout(RelativeLayout.Y_AXIS);
    public static final RelativeLayout TEXT_PANEL_LAYOUT = new RelativeLayout(RelativeLayout.Y_AXIS);
    public static final RelativeLayout IMAGE_PANEL_LAYOUT = new RelativeLayout(RelativeLayout.Y_AXIS);
    public static final String IMAGE_FILE_POSTFIX = ".gif";
    public static final FlowLayout BUTTON_PANEL_LAYOUT = new FlowLayout(FlowLayout.CENTER, 10, 20);

}
