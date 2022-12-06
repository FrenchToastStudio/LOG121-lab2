package log121.lab2.controller.commands;

import log121.lab2.controller.Command;
import log121.lab2.controller.ModificationController;

/**

 Classe qui représente la commande d'arrêt du zoom.

 Elle hérite de la classe abstraite {@link Command} et utilise un objet {@link ModificationController} pour exécuter l'action d'arrêt du zoom.
 */
public class StopZoomCommand extends Command {
    private final ModificationController modificationController;
    private boolean hasToExecute = false;

    /**

     Constructeur de la commande StopZoom
     @param modificationController L'objet ModificationController utilisé pour exécuter l'action d'arrêt du zoom
     */
    public StopZoomCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }

    /**

     Exécute l'action d'arrêt du zoom en utilisant l'objet ModificationController.
     */
    @Override
    public void execute() {
        modificationController.stopZoom();
        hasToExecute= false;
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

     Active ou désactive l'exécution de la commande.
     */
    public void toggle()
    {
        hasToExecute = true;
    }
}
