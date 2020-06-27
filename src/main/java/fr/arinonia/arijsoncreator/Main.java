package fr.arinonia.arijsoncreator;

import fr.arinonia.arijsoncreator.ui.FxApplication;
import javafx.application.Application;

import javax.swing.*;

/**
 * Created by Arinonia on 26/06/2020 inside the package - fr.arinonia.commandmanager
 */
public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("javafx.application.Application");
            Application.launch(FxApplication.class, args);
        } catch (ClassNotFoundException e) {
            //TODO Load javafx with custom java.
            JOptionPane.showMessageDialog(null, "Class " + e.getMessage() + " non trouvée", "Erreur JavaFX", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

}
