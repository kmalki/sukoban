package sample;

import javafx.scene.control.Label;

public class VueNbCoupIHMFX  {
    Controleur controleur;
    Label label = new Label("0");
    CommandeInt commande;

    public VueNbCoupIHMFX(Controleur controleur) {
        this.controleur=controleur;
        commande = controleur.commandeNbCoup();
    }

    public void dessine() {
        label.setText(commande.exec()+"");
    }

}
