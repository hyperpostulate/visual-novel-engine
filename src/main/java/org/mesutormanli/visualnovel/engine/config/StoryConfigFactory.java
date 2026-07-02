package org.mesutormanli.visualnovel.engine.config;

import org.mesutormanli.visualnovel.engine.config.story.SceneConfig;
import org.mesutormanli.visualnovel.engine.config.story.StoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class StoryConfigFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoryConfigFactory.class);

    private Map<Integer, SceneConfig> panelConfigMap;

    public StoryConfigFactory(MainConfig mainConfig) {
        panelConfigMap = new HashMap<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StoryConfig.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(mainConfig.getStoryConfigPath());
            if (inputStream == null) {
                throw new IllegalStateException(
                        "Story config file '" + mainConfig.getStoryConfigPath() + "' not found on classpath");
            }

            try (inputStream) {
                StoryConfig configContainer = (StoryConfig) jaxbUnmarshaller.unmarshal(inputStream);

                configContainer.getSceneConfigList()
                        .forEach(conf -> panelConfigMap.put(conf.getIndex(), conf));
            }

        } catch (JAXBException | IOException e) {
            throw new IllegalStateException("Could not read story configuration", e);
        }
    }

    public SceneConfig getPanelConfig(int panelIndex) {
        return panelConfigMap.get(panelIndex);
    }
}
