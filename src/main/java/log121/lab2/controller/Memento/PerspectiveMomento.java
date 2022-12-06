package log121.lab2.controller.Memento;

import log121.lab2.model.*;

/**

 Classe qui représente un momento de la perspective.
 Elle permet de sauvegarder l'état de la perspective (position, largeur, hauteur et zoom) pour pouvoir le restaurer ultérieurement.
 */
public class PerspectiveMomento {
    private Position position;
    private int width;
    private int height;
    private int zoom;

    /**

     Constructeur qui crée un momento de la perspective à partir de l'état actuel de la perspective.
     @param perspective La perspective à partir de laquelle créer le momento.
     */
    public PerspectiveMomento(Perspective perspective){
        this.position = new Position(perspective.getX(), perspective.getY());
        this.width = perspective.getWidth();
        this.height = perspective.getHeight();
        this.zoom = perspective.getZoom();
    }

    /**

     Restaure l'état de la perspective en fonction de l'état enregistré dans le momento.
     @param perspective La perspective à restaurer.
     */
    public void setPerspectiveToState(Perspective perspective){
        perspective.setPosition(this.position);
        perspective.setHeight(this.height);
        perspective.setWidth(this.width);
        perspective.setZoom(this.zoom);

    }

}
