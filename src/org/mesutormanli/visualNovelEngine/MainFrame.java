package org.mesutormanli.visualNovelEngine;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.mesutormanli.visualNovelEngine.config.MainConfig;

public class MainFrame extends JFrame {
	public static final Logger LOGGER = Logger.getLogger(MainFrame.class);
	private static MainFrame instance;
	private Scene scene;

	private MainFrame() {
		LOGGER.info("MainFrame invoked.");

		setIconImage(new ImageIcon(MainConfig.MAIN_FRAME_ICON_PATH).getImage());
		setTitle(MainConfig.TITLE);
		setSize(MainConfig.MAIN_FRAME_DIMENSION);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		//setUndecorated(true);

		// JMenuBar bar = new JMenuBar();
		// setJMenuBar(bar);
		//
		// JMenu fileMenu=new JMenu("About");
		// fileMenu.setMnemonic('A');
		// bar.add(fileMenu);
		//
		// JMenu fileMenu2=new JMenu("License");
		// fileMenu2.setMnemonic('L');
		// bar.add(fileMenu2);

	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	public void setScene(Scene scene) {
		if (null != this.scene) {
			remove(this.scene);
		}
		this.scene = scene;
		getContentPane().add(this.scene);
		setVisible(true);
		LOGGER.info("Scene " + scene.getSceneConfig().getSceneIndex() + " is present.");
		LOGGER.info("Possible states are: " + scene.getSceneConfig().getWestButtonSceneIndex() + " " + scene.getSceneConfig().getCenterButtonSceneIndex() + " and " + scene.getSceneConfig().getEastButtonSceneIndex() + ".");
	}
	public void initialize() {
		setScene(new Scene(0));

	}

}
