package fr.arinonia.arijsoncreator.ui.panel;

import fr.arinonia.arijsoncreator.ui.PanelManager;
import javafx.scene.layout.GridPane;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui.panel
 */
public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
}
