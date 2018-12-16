package models.classMetiers;

public interface Sujet {
    void abonne(Observateur observateur);
    void notifie();
}
