package log121.lab2.controller;

import log121.lab2.model.Position;

/**
 *
 * Classe représentant la commande de zoom d'une image.
 * @author nom_du_développeur
 */
public class ZoomImageCommand extends Command{

    private ModificationController modificationController;
    private boolean hasToExecute;

    private int zoom;

    /**
     * Constructeur prenant en paramètre le contrôleur de modification.
     *
     * @param modificationController le contrôleur de modification
     */
    public ZoomImageCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }

    /**
     * Méthode permettant de zoomer l'image.
     */
    @Override
    public void execute() {
        modificationController.zoom(zoom);
        if(hasToExecute)
            hasToExecute = false;
    }

    /**
     * Méthode indiquant si la condition pour exécuter la commande est remplie.
     *
     * @return true si la condition est remplie, false sinon
     */
    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    /**
     * Méthode permettant de changer le niveau de zoom.
     *
     * @param zoom le nouveau niveau de zoom
     */
    public void changeZoom(int zoom){
        this.zoom = zoom;
        hasToExecute = true;
    }
}
