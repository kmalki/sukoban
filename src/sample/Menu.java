package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Menu {
    private ControleurIHMFX monControleur;
    @FXML
    VBox topNiveau;


    public static Menu creerEtAfficher(ControleurIHMFX c, Stage laStageUnique) {
        URL location = Menu.class.getResource("/views/Menu.fxml");
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
        return vue;
    }

    private void setMonControleur(ControleurIHMFX c) {
        monControleur=c;
    }

    public void quitter(){
        monControleur.quitter();
    }

    public void goToNewGame() throws FileNotFoundException {
        monControleur.goToNewGame(0);
    }

    public void goToSelection() throws FileNotFoundException {
        monControleur.goToSelection();
    }
}
