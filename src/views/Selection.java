package views;

import controlers.Controleur;
import controlers.ControleurIHMFX;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Selection {
    private Controleur monControleur;
    private ControleurIHMFX monControleurIHMFX;
    @FXML
    ComboBox<String> listeDeroulante;


    public static Selection selectionner(Controleur controleur, ControleurIHMFX c, Stage laStageUnique){
        URL location = Menu.class.getResource("/fxmlViews/Selection.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Selection vueS = fxmlLoader.getController();
        vueS.setMonControleurIHMFX(c);
        vueS.setMonControleur(controleur);
        laStageUnique.setTitle("Selection");
        laStageUnique.setScene(new Scene(root, laStageUnique.getWidth(), laStageUnique.getHeight()));
        laStageUnique.setMaximized(true);
        laStageUnique.show();
        vueS.remplirListeNiveau();
        return vueS;
    }

    private void remplirListeNiveau() {
        ArrayList<Niveau> l=this.monControleur.getNiveaux();
        for (Niveau n : l
        ) {
            listeDeroulante.getItems().add(n.getName());
        }
    }


    private void setMonControleurIHMFX(ControleurIHMFX c) {
        monControleurIHMFX=c;
    }
    private void setMonControleur(Controleur c) {
        monControleur=c;
    }

    public void goToMenu() {
        this.monControleurIHMFX.goToMenu();
    }

    public void jouer(){
        String levelName = listeDeroulante.getValue();
        if (levelName != null) {
            int numLevel = -1;
            for (int i = 0; i < this.monControleur.getNiveaux().size(); i++) {
                if (levelName == this.monControleur.getNiveaux().get(i).getName()) {
                    numLevel = i;
                }
            }
            this.monControleurIHMFX.jouer(numLevel);
        }
    }
}
