package fr.arinonia.arijsoncreator.ui;

import fr.arinonia.arijsoncreator.utils.Constants;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public abstract class Panel implements IPanel {

    protected final UiManager uiManager;
    protected final GridPane layout;

    public Panel(UiManager uiManager) {
        this.uiManager = uiManager;
        this.layout = new GridPane();
        GridPane.setHgrow(this.layout, Priority.ALWAYS);
        GridPane.setVgrow(this.layout, Priority.ALWAYS);
        this.layout.setStyle("-fx-background-color: " + Constants.BACKGROUND_COLOR);

        this.initPanel();
    }

    public abstract void initPanel();

    public void postInit(Object... objects) {}

    @Override
    public UiManager getUiManager() {
        return this.uiManager;
    }

    @Override
    public GridPane getLayout() {
        return this.layout;
    }
}
