package org.mesutormanli.visualNovelEngine;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);
	private static MainFrame instance;
	private  Scene scene;
	
	private MainFrame() {
	
		LOGGER.info("MainFrame invoked.");
		setTitle(MainFrameConfig.TITLE);
		setSize(MainFrameConfig.DIMENSION);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        
        JMenu fileMenu=new JMenu("About");
        fileMenu.setMnemonic('A');
        bar.add(fileMenu);

        JMenu fileMenu2=new JMenu("License");
        fileMenu2.setMnemonic('L');
        bar.add(fileMenu2);

	}
	
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	public void setScene(Scene scene) {
		if(null!= this.scene) {
			remove(this.scene);	
		}
		this.scene = scene;
		getContentPane().add(this.scene);
		setVisible(true);
		LOGGER.info("Scene "+ scene.getSceneConfig().getSceneIndex() + " is present.");
	}


	public void initialize() {
		setScene(new Scene(0));
		
	}
	

}
