package controlers;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import views.Game;
import views.Menu;
import views.Selection;
import java.util.Optional;

public class ControleurIHMFX {
    public Controleur controleur;
    Menu vueMenu;
    public Stage primaryStage;
    Game vueGame;
    Selection vueSelection;


    public ControleurIHMFX(Controleur controleur,Stage primaryStage){
        this.controleur = controleur;
        this.primaryStage=primaryStage;
    }

    public void goToNewGame(int i){
        vueGame=Game.newGame(controleur,this,this.primaryStage,i);
    }

    public void goToMenu(){
        vueMenu=Menu.creerEtAfficher(this,this.primaryStage);
    }

    public void goToSelection(){
        vueSelection=Selection.selectionner(this.controleur,this,this.primaryStage);
    }

    public void jouer(int numLevel){
        goToNewGame(numLevel);
    }

    public void winornot(){
        if(controleur.facadeModele.winornot()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText(controleur.getNomNiveau()+" : Réussi !     VOTRE SCORE : "+ controleur.facadeModele.nbCoup());
            alert.setContentText("Que voulez vous faire ?");

            ButtonType buttonPrec = new ButtonType("Niveau Précédent");
            ButtonType buttonNext = new ButtonType("Niveau Suivant");
            ButtonType buttonReset = new ButtonType("Rejouer");
            ButtonType buttonMenu = new ButtonType("Menu principal");

            if(controleur.getNiveau()<1){
                alert.getButtonTypes().setAll(buttonNext, buttonReset, buttonMenu);
            }
            else{
                alert.getButtonTypes().setAll(buttonPrec, buttonNext, buttonReset, buttonMenu);
            }

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonReset)
            {
                controleur.reset();
            }
            else if(result.get() == buttonMenu)
            {
                goToMenu();
            }
            else if(result.get()==buttonNext){
                nextLevel();
            }
            else if(result.get()==buttonPrec){
                if(controleur.getNiveau()>0) {
                    controleur.facadeModele.precNiveau();
                    goToNewGame(controleur.getNiveau());
                }
            }
        }
    }

    public void nextLevel(){
        controleur.facadeModele.nextNiveau();
        goToNewGame(controleur.getNiveau());
    }
}