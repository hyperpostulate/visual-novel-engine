package org.mesutormanli.visualNovelEngine;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Scene extends JPanel {
	private SceneConfig sceneConfig;
	private JPanel textPanel;
	private JPanel imagePanel;
	private JPanel buttonPanel;

	public Scene(int sceneIndex) {
		this(SceneConfigFactory.getPanelConfig(sceneIndex));

	}

	public Scene(SceneConfig sceneConfig) {

		setSceneConfig(sceneConfig);
		setLayout(new BorderLayout());

		// Text Panel
		setTextPanel(new JPanel(SceneConfig.TEXT_PANEL_LAYOUT));
		getTextPanel().setBackground(Color.WHITE);
		getTextPanel().add(new JLabel(getSceneConfig().getText()));

		// Image Panel
		setImagePanel(new JPanel(SceneConfig.IMAGE_PANEL_LAYOUT));
		getImagePanel().setBackground(Color.GRAY);
		getImagePanel().add(new JLabel("There will be image of Frame " + getSceneConfig().getSceneIndex() + "."));
		//getImagePanel().add(new JLabel(new ImageIcon(getPanelConfig().getImagePath())));

		// Button Panel
		setButtonPanel(new JPanel(SceneConfig.BUTTON_PANEL_LAYOUT));
		getButtonPanel().setBackground(Color.BLACK);
		
		//TODO: Button Config Class
		JButton westButton = new JButton(getSceneConfig().getWestButtonText());
		westButton.addActionListener(new SceneButtonActionListener(getSceneConfig().getWestButtonSceneIndex()));
		getButtonPanel().add(westButton);
		
		JButton centerButton = new JButton(getSceneConfig().getCenterButtonText());
		centerButton.addActionListener(new SceneButtonActionListener(getSceneConfig().getCenterButtonSceneIndex()));
		getButtonPanel().add(centerButton);
		
		JButton eastButton = new JButton(getSceneConfig().getEastButtonText());
		eastButton.addActionListener(new SceneButtonActionListener(getSceneConfig().getEastButtonSceneIndex()));
		getButtonPanel().add(eastButton);

		// Add subpanels to GamePanel
		add(getTextPanel(), BorderLayout.NORTH);
		add(getImagePanel(), BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);

	}

	public JPanel getTextPanel() {
		return textPanel;
	}

	public void setTextPanel(JPanel textPanel) {
		this.textPanel = textPanel;
	}

	public JPanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(JPanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public SceneConfig getSceneConfig() {
		return sceneConfig;
	}

	public void setSceneConfig(SceneConfig panelConfig) {
		this.sceneConfig = panelConfig;
	}

}
