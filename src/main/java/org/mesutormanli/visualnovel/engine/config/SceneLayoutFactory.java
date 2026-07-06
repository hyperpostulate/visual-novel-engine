package org.mesutormanli.visualnovel.engine.config;

import org.mesutormanli.visualnovel.engine.util.RelativeLayout;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SceneLayoutFactory {

    public RelativeLayout createSceneLayout() {
        return new RelativeLayout(RelativeLayout.Y_AXIS);
    }

    public RelativeLayout createTextPanelLayout() {
        return new RelativeLayout(RelativeLayout.Y_AXIS);
    }

    public RelativeLayout createImagePanelLayout() {
        return new RelativeLayout(RelativeLayout.Y_AXIS);
    }

    public FlowLayout createButtonPanelLayout() {
        return new FlowLayout(FlowLayout.CENTER, 10, 20);
    }
}
