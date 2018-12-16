package models;

import controlers.Controleur;
import javafx.scene.control.Label;
import sample.CommandeInt;

public class VueNbCoupIHMFX  {
    Controleur controleur;
    Label label = new Label("0");
    Label label2 = new Label("");
    CommandeInt commande;

    public VueNbCoupIHMFX(Controleur controleur) {
        this.controleur=controleur;
        commande = controleur.commandeNbCoup();
    }

    public void dessine() {
        label.setText("Nombre de coups : "+commande.exec()+"");
    }

    public void dessineNom(String s){
        label2.setText(s);
    }

}
