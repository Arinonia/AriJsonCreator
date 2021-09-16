package fr.arinonia.arijsoncreator.ui.controls;

import fr.arinonia.arijsoncreator.utils.I18n;

public class IgnoredComboBox extends MaterialComboBox {

    public IgnoredComboBox() {
        super();
        if (this.getItems().isEmpty()) {
            this.getItems().add(I18n.LANG.getTranslation("config.emptyignoredlist.text"));
            this.getSelectionModel().select(0);
        }
    }


    public boolean isEmptyOrContainDefault() {
        return  this.getItems().isEmpty() || this.getItems().contains(I18n.LANG.getTranslation("config.emptyignoredlist.text"));
    }
}
