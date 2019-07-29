package org.mesutormanli.visualnovel.engine.config;

import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.mesutormanli.visualnovel.engine.config.story.StoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StoryConfigFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoryConfigFactory.class);

    private static StoryConfigFactory instance;
    private Map<Integer, SceneConfig> panelConfigMap;

    private StoryConfigFactory() {
        StoryConfig configContainer = null;
        panelConfigMap = new HashMap<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StoryConfig.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            configContainer = (StoryConfig) jaxbUnmarshaller
                    .unmarshal(new File(MainConfig.STORY_CONFIG_FILE_PATH));
        } catch (JAXBException e) {
            LOGGER.error("Configuration could not be read. Exiting...", e);
            System.exit(1);
        }

        configContainer.getSceneConfigList()
                .forEach(conf -> panelConfigMap.put(conf.getIndex(), conf));

    }

    private static StoryConfigFactory getInstance() {
        if (instance == null) {
            instance = new StoryConfigFactory();
        }
        return instance;
    }

    public static SceneConfig getPanelConfig(int panelIndex) {
        return getInstance().panelConfigMap.get(panelIndex);
    }

}
