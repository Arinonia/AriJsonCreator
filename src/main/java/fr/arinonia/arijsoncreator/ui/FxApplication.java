package fr.arinonia.arijsoncreator.ui;

import fr.arinonia.arijsoncreator.AriJsonCreator;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.arijsoncreator.ui
 */
public class FxApplication extends Application {

    @Override
    public void start(Stage stage){
        new AriJsonCreator().init(stage);
    }
}
