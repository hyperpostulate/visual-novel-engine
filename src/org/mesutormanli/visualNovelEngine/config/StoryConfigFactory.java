package org.mesutormanli.visualNovelEngine.config;

import org.mesutormanli.visualNovelEngine.config.story.SceneConfig;
import org.mesutormanli.visualNovelEngine.config.story.StoryConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StoryConfigFactory {

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
            System.out.println("Configuration could not be read. Exiting...");
            System.exit(1);
        }

        for (SceneConfig conf : configContainer.getSceneConfigList()) {
            panelConfigMap.put(conf.getIndex(), conf);
        }

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
