package application;

import controlers.Controleur;
import controlers.ControleurIHMFX;
import javafx.application.Application;
import javafx.stage.Stage;
import views.Menu;

public class IHMFX extends Application  {
    Controleur controleur;
    ControleurIHMFX controleurIHMFX;
    Menu vueMenu;
    Stage primaryStage;


    @Override
    public void start(Stage primaryStage){
        controleur = Controleur.getControleur();
        controleurIHMFX = new ControleurIHMFX(controleur,primaryStage);
        /* montage de la scene */
        this.primaryStage=primaryStage;
        this.goToMenu();

    }

    public void lance() {
        launch(new String[]{});
    }

    public void goToMenu(){
        vueMenu = Menu.creerEtAfficher(controleurIHMFX,primaryStage);
    }
}

