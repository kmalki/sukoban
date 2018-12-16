import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class VueIHMFX {

    CommandeTabInt commandeGetEtat;
    CommandeInt commandeLineNumber;
    ImageView[] images;
    GridPane gridPane = new GridPane();
    Image character = new Image(getClass().getResource("/character.png").toString(),80,80,false,true);
    Image caissebrown = new Image(getClass().getResource("/caissebrown.png").toString(),80,80,false,true);
    Image wall = new Image(getClass().getResource("/wall.png").toString(),80,80,false,true);
    Image sand = new Image(getClass().getResource("/sand.png").toString(),80,80,false,true);
    Image empty = new Image(getClass().getResource("/empty.png").toString(),80,80,false,true);
    Image endbrown = new Image(getClass().getResource("/endbrown.png").toString(),80,80,false,true);


    public VueIHMFX(Controleur controleur){
        gridPane.setAlignment(Pos.CENTER);
        commandeGetEtat = controleur.commandeGetEtat();
        commandeLineNumber=controleur.getLineNumber();
        dessine();
    }

    public void dessine() {
        initMatrice();
        for (int i=0;i<commandeGetEtat.exec().length;i++) {
            if(commandeGetEtat.exec()[i]==1) { // 1 = perso
                images[i].imageProperty().set(character);
            }
            else if(commandeGetEtat.exec()[i]==2){
                images[i].imageProperty().set(caissebrown);
            }
            else if(commandeGetEtat.exec()[i]==3){
                images[i].imageProperty().set(wall);
            }
            else if(commandeGetEtat.exec()[i]==0){
                images[i].imageProperty().set(sand);
            }
            else if(commandeGetEtat.exec()[i]==9){
                images[i].imageProperty().set(empty);
            }
            else if(commandeGetEtat.exec()[i]==4){
                images[i].imageProperty().set(endbrown);
            }
        }
    }


    public void initMatrice(){
        gridPane.getChildren().remove(0, gridPane.getChildren().size());
        images = new ImageView[commandeGetEtat.exec().length];
        int col=0;
        for (int i=0;i<commandeGetEtat.exec().length;i++) {
            if(i%commandeLineNumber.exec()==0){
                col++;
            }
            images[i] = new ImageView(sand);
            gridPane.add(images[i],i%commandeLineNumber.exec(),col);
        }
    }
}
