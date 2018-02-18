package org.mesutormanli.visualNovelEngine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import org.mesutormanli.visualNovelEngine.config.MainConfig;
import org.mesutormanli.visualNovelEngine.config.SceneConfig;
import org.mesutormanli.visualNovelEngine.config.SceneConfigFactory;
import org.mesutormanli.visualNovelEngine.util.RelativeLayout;
import org.mesutormanli.visualNovelEngine.util.StringUtils;

@SuppressWarnings("serial")
public class Scene extends JPanel {
	private SceneConfig sceneConfig;
	private JPanel textPanel;
	private JPanel imagePanel;
	private JPanel buttonPanel;

	public Scene(int sceneIndex) {
		this(SceneConfigFactory.getPanelConfig(sceneIndex));

	}

	/**
	 * @wbp.parser.constructor
	 */
	public Scene(SceneConfig sceneConfig) {

		setSceneConfig(sceneConfig);
		setLayout(MainConfig.SCENE_LAYOUT);
		setBorder(new TitledBorder(getSceneConfig().getSceneHeadline()));

		// Text Panel
		setTextPanel(new JPanel(MainConfig.TEXT_PANEL_LAYOUT));
		getTextPanel().add(new JLabel(StringUtils.wrapLabelString(getSceneConfig().getSceneText())));

		// Image Panel
		setImagePanel(new JPanel(MainConfig.IMAGE_PANEL_LAYOUT));
		getImagePanel().add(new JLabel(new ImageIcon("./resources/images/" + MainConfig.IMAGE_FILE_PREFIX
				+ getSceneConfig().getSceneIndex() + MainConfig.IMAGE_FILE_POSTFIX)));

		// Button Panel
		setButtonPanel(new JPanel(MainConfig.BUTTON_PANEL_LAYOUT));

		if(StringUtils.isNotEmpty(getSceneConfig().getWestButtonText())) {
		JButton westButton = new JButton(getSceneConfig().getWestButtonText());
		westButton.addActionListener(new SceneButtonActionListener(getSceneConfig().getWestButtonSceneIndex()));
		getButtonPanel().add(westButton);
		}

		if(StringUtils.isNotEmpty(getSceneConfig().getCenterButtonText())) {
		JButton centerButton = new JButton(getSceneConfig().getCenterButtonText());
		centerButton.addActionListener(new SceneButtonActionListener(getSceneConfig().getCenterButtonSceneIndex()));
		getButtonPanel().add(centerButton);
		}

		if(StringUtils.isNotEmpty(getSceneConfig().getEastButtonText())) {
		JButton eastButton = new JButton(getSceneConfig().getEastButtonText());
		eastButton.addActionListener(new SceneButtonActionListener(getSceneConfig().getEastButtonSceneIndex()));
		getButtonPanel().add(eastButton);
		}

		// Add subpanels to GamePanel
		add(getTextPanel(), new Float(20));
		add(getImagePanel(), new Float(70));
		add(getButtonPanel(), new Float(10));

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
