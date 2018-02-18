package org.mesutormanli.visualNovelEngine.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SceneConfigFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(SceneConfigFactory.class);

	private static SceneConfigFactory instance;
	private Map<Integer, SceneConfig> panelConfigMap;

	private SceneConfigFactory() {
		SceneConfigContainer configContainer = null;
		panelConfigMap = new HashMap<Integer, SceneConfig>();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(SceneConfigContainer.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			configContainer = (SceneConfigContainer) jaxbUnmarshaller
					.unmarshal(new File(MainConfig.SCENE_CONFIG_FILE_PATH));
		} catch (JAXBException e) {
			LOGGER.error("Configuration could not be read. Exiting...", e);
			System.exit(1);
		}

		for (SceneConfig conf : configContainer.getSceneConfigList()) {
			panelConfigMap.put(conf.getSceneIndex(), conf);
		}

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
