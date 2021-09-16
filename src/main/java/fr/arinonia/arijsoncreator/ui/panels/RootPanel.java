package fr.arinonia.arijsoncreator.ui.panels;

import fr.arinonia.arijsoncreator.ui.IPanel;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.ui.includes.TopPanel;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class RootPanel implements IPanel {

    private final UiManager uiManager;
    private final GridPane layout;

    public RootPanel(UiManager uiManager) {
        this.uiManager = uiManager;
        this.layout = new GridPane();

        GridPane.setHgrow(this.layout, Priority.ALWAYS);
        GridPane.setVgrow(this.layout, Priority.ALWAYS);

        final RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.TOP);
        rowConstraints.setMinHeight(30.0D);
        rowConstraints.setMaxHeight(30.0D);

        this.layout.getRowConstraints().addAll(rowConstraints, new RowConstraints());
        TopPanel topPanel = new TopPanel(uiManager);
        this.layout.add(topPanel.getLayout(), 0, 0);

        HomePanel homePanel = new HomePanel(uiManager);
        this.layout.add(homePanel.getLayout(), 0, 1);

        GridPane.setHgrow(homePanel.getLayout(), Priority.ALWAYS);
        GridPane.setVgrow(homePanel.getLayout(), Priority.ALWAYS);
    }

    @Override
    public UiManager getUiManager() {
        return this.uiManager;
    }
    @Override
    public GridPane getLayout() {
        return this.layout;
    }

}
