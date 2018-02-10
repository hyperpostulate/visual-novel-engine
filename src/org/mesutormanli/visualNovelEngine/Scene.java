package org.mesutormanli.visualNovelEngine;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StoryPane extends JPanel {
	private StoryPaneConfig panelConfig;
	private JPanel textPanel;
	private JPanel imagePanel;
	private JPanel buttonPanel;

	public StoryPane(int panelIndex) {
		this(StoryPaneConfigFactory.getPanelConfig(panelIndex));

	}

	public StoryPane(StoryPaneConfig panelConfig) {

		setPanelConfig(panelConfig);
		setLayout(new BorderLayout());

		// Text Panel
		setTextPanel(new JPanel(StoryPaneConfig.TEXT_PANEL_LAYOUT));
		getTextPanel().setBackground(Color.WHITE);
		getTextPanel().add(new JLabel(getPanelConfig().getText()));

		// Image Panel
		setImagePanel(new JPanel(StoryPaneConfig.IMAGE_PANEL_LAYOUT));
		getImagePanel().setBackground(Color.GRAY);
		getImagePanel().add(new JLabel("There will be image of Frame " + getPanelConfig().getPanelIndex() + "."));
		//getImagePanel().add(new JLabel(new ImageIcon(getPanelConfig().getImagePath())));

		// Button Panel
		setButtonPanel(new JPanel(StoryPaneConfig.BUTTON_PANEL_LAYOUT));
		getButtonPanel().setBackground(Color.BLACK);
		
		//TODO: Button Config Class
		JButton westButton = new JButton(getPanelConfig().getWestButtonText());
		westButton.addActionListener(new GameButtonActionListener(getPanelConfig().getWestButtonPanelIndex()));
		getButtonPanel().add(westButton);
		
		JButton centerButton = new JButton(getPanelConfig().getCenterButtonText());
		centerButton.addActionListener(new GameButtonActionListener(getPanelConfig().getCenterButtonPanelIndex()));
		getButtonPanel().add(centerButton);
		
		JButton eastButton = new JButton(getPanelConfig().getEastButtonText());
		eastButton.addActionListener(new GameButtonActionListener(getPanelConfig().getEastButtonPanelIndex()));
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

	public StoryPaneConfig getPanelConfig() {
		return panelConfig;
	}

	public void setPanelConfig(StoryPaneConfig panelConfig) {
		this.panelConfig = panelConfig;
	}

}
