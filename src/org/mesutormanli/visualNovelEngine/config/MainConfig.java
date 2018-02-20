package org.mesutormanli.visualNovelEngine.config;

import java.awt.Dimension;
import java.awt.FlowLayout;

import org.mesutormanli.visualNovelEngine.util.RelativeLayout;


public class MainConfig {

	public static final String TITLE = "Zombie Apocalypse";
	public static final Dimension MAIN_FRAME_DIMENSION = new Dimension(800, 600);
	public static final String SCENE_CONFIG_FILE_PATH = "./resources/sceneConfig.xml";
	public static final RelativeLayout SCENE_LAYOUT = new RelativeLayout(RelativeLayout.Y_AXIS);
	public static final RelativeLayout TEXT_PANEL_LAYOUT = new RelativeLayout(RelativeLayout.Y_AXIS);
	public static final RelativeLayout IMAGE_PANEL_LAYOUT = new RelativeLayout(RelativeLayout.Y_AXIS);
	public static final String IMAGE_FILE_POSTFIX = ".gif";
	public static final FlowLayout BUTTON_PANEL_LAYOUT = new FlowLayout(FlowLayout.CENTER, 10, 20);

}
