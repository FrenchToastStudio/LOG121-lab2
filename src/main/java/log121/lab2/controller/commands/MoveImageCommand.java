package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.commands.Command;
import log121.lab2.model.Position;

/**
 *  Classe qui représente la commande de déplacement d'une image.
 *  Elle hérite de la classe abstraite {@link Command}.
 */
public class MoveImageCommand extends Command{
    private ModificationController modificationController;
    private boolean hasToExecute;
    private Position position;

    /**

     Constructeur de la classe MoveImageCommand.
     @param modificationController Le contrôleur de modification associé à la commande.
     */
    public MoveImageCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }

    /**

     Méthode d'exécution de la commande MoveImage.
     */
    @Override
    public void execute() {
        modificationController.translate(position);
        if(hasToExecute)
            hasToExecute = false;
    }

    /**

     Vérifie si la condition pour exécuter la commande est remplie.
     @return true si la commande peut être exécutée, false sinon.
     */
    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    /**

     Déplace l'image à la position spécifiée.
     @param position La position vers laquelle l'image doit être déplacée.
     */
    public void moveToPosition(Position position){
        this.position = position;
        hasToExecute = true;
    }

}
