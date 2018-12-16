import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Menu {
    private ControleurIHMFX monControleur;
    @FXML
    VBox topNiveau;
    @FXML
    Button newG;
    @FXML
    Button level;


    public static Menu creerEtAfficher(ControleurIHMFX c, Stage laStageUnique) {
        URL location = Menu.class.getResource("/Menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Menu vue = fxmlLoader.getController();
        laStageUnique.setTitle("Menu");
        laStageUnique.setMaximized(true);
        laStageUnique.setScene(new Scene(root, laStageUnique.getWidth(), laStageUnique.getHeight()));
        laStageUnique.show();
        vue.setMonControleur(c);
        if(vue.monControleur.controleur.getNombreNiveaux()<1){
            vue.newG.setDisable(true);
            vue.level.setDisable(true);
        }
        else{
            vue.level.setDisable(false);
            vue.newG.setDisable(false);
        }
        return vue;
    }

    private void setMonControleur(ControleurIHMFX c) {
        monControleur=c;
    }

    public void goToNewGame(){
        monControleur.goToNewGame(0);
    }

    public void goToSelection(){
        monControleur.goToSelection();
    }

    public void load() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fc.showOpenDialog(monControleur.primaryStage);
        if (selectedFile != null) {
            monControleur.controleur.load(selectedFile);
            this.newG.setDisable(false);
            this.level.setDisable(false);
        }
    }
}
