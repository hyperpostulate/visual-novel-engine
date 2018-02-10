package org.mesutormanli.visualNovelEngine;

import java.util.HashMap;
import java.util.Map;

public class SceneConfigFactory {

	private static SceneConfigFactory instance;
	private Map<Integer, SceneConfig> panelConfigMap;

	private SceneConfigFactory() {
		panelConfigMap = new HashMap<Integer, SceneConfig>();
		panelConfigMap.put(0, new SceneConfig(0, "There will be the text of panel 0.", null, "To panel 1", 1, "To panel 2", 2, "To panel 3", 3));
		panelConfigMap.put(1, new SceneConfig(1, "There will be the text of panel 1.", null, "To panel 2", 2, "To panel 3", 3, "To panel 4", 4));
		panelConfigMap.put(2, new SceneConfig(2, "There will be the text of panel 2.", null, "To panel 3", 3, "To panel 4", 4, "To panel 5", 5));
		panelConfigMap.put(3, new SceneConfig(3, "There will be the text of panel 3.", null, "To panel 4", 4, "To panel 5", 5, "To panel 6", 6));
		panelConfigMap.put(4, new SceneConfig(4, "There will be the text of panel 4.", null, "To panel 5", 5, "To panel 6", 6, "To panel 7", 7));
		panelConfigMap.put(5, new SceneConfig(5, "There will be the text of panel 5.", null, "To panel 6", 6, "To panel 7", 7, "To panel 8", 8));
		panelConfigMap.put(6, new SceneConfig(6, "There will be the text of panel 6.", null, "To panel 7", 7, "To panel 8", 8, "To panel 9", 9));
		panelConfigMap.put(7, new SceneConfig(7, "There will be the text of panel 7.", null, "To panel 8", 8, "To panel 9", 9, "To panel 0", 0));
		panelConfigMap.put(8, new SceneConfig(8, "There will be the text of panel 8.", null, "To panel 9", 9, "To panel 0", 0, "To panel 1", 1));
		panelConfigMap.put(9, new SceneConfig(9, "There will be the text of panel 9.", null, "To panel 0", 0, "To panel 1", 1, "To panel 12", 12));
	}

	private static SceneConfigFactory getInstance() {
		if (instance == null) {
			instance = new SceneConfigFactory();
		}
		return instance;
	}

	public static SceneConfig getPanelConfig(int panelIndex) {
		return getInstance().panelConfigMap.get(panelIndex);
	}

}
