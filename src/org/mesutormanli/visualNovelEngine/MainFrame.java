package org.mesutormanli.visualNovelEngine;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static MainFrame instance;
	private  StoryPane panel;
	
	private MainFrame() {
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
	
	public void setGamePanel(StoryPane panel) {
		if(null!= this.panel) {
			remove(this.panel);	
		}
		this.panel = panel;
		add(this.panel);
		setVisible(true);
	}


	public void initialize() {
		setGamePanel(new StoryPane(0));
		
	}
	

}
