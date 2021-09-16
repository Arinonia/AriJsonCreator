package fr.arinonia.arijsoncreator.ui.controls;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import fr.arinonia.arijsoncreator.Main;
import fr.arinonia.arijsoncreator.ui.UiManager;
import fr.arinonia.arijsoncreator.utils.Constants;
import fr.arinonia.arijsoncreator.utils.I18n;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Modality;

public class Alert {
    private final UiManager uiManager;
    private final Label title;
    private final Label bodyText;
    private final JFXDialogLayout layout;
    private final JFXAlert<Void> alert;
    private JFXButton jfxButton;

    public Alert(UiManager uiManager, String title, String bodyText) {
        this.uiManager = uiManager;
        this.title = new Label(title);
        this.bodyText = new Label(bodyText);
        this.layout = new JFXDialogLayout();
        this.title.setStyle("-fx-text-fill: #fff;");
        this.title.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 18));

        this.layout.setHeading(this.title);
        this.bodyText.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 14));
        this.layout.setBody(this.bodyText);
        this.alert = new JFXAlert<>(uiManager.getStage());
        this.alert.setOverlayClose(true);
        this.alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        this.alert.setContent( this.layout);
        this.alert.initModality(Modality.NONE);
        this.alert.getDialogPane().setStyle("-fx-background-color: rgba(12,12,12,0.3)");
        this.layout.setStyle("-fx-background-color: #333; -fx-border-color: #AA2523;-fx-text-fill: #fff;");

        this.addButton("close", this::close);
        this.show();

    }

    public Alert(UiManager uiManager, String bodyText) {
        this(uiManager, I18n.LANG.getTranslation("alert.title.text") + "-" + Constants.APP_NAME, bodyText);
    }

    public final void show(){
        this.alert.show();
    }

    public void addButton(String buttonText, IComponentAction action){
        this.jfxButton = new JFXButton(buttonText);
        this.jfxButton.setMinSize(70, 25);
        this.jfxButton.setFont(Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toString(), 12));
        this.jfxButton.setStyle("-fx-background-color: #2c2c2c; -fx-border-color: #881e1d; -fx-text-fill: #9a9a9a;");
        this.jfxButton.setOnMouseClicked(e -> action.onClick());
        this.layout.setActions(this.jfxButton);
    }

    public void close() {
        this.alert.close();
    }

    public UiManager getUiManager() {
        return this.uiManager;
    }

    public Label getTitle() {
        return this.title;
    }

    public Label getBodyText() {
        return this.bodyText;
    }

    public JFXDialogLayout getLayout() {
        return this.layout;
    }

    public JFXAlert<Void> getAlert() {
        return this.alert;
    }

    public JFXButton getJfxButton() {
        return this.jfxButton;
    }
}
