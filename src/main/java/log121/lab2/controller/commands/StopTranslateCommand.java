package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;

/**

 Classe qui représente la commande d'arrêt de la traduction.

 Elle hérite de la classe abstraite {@link Command}.
 */
public class StopTranslateCommand extends Command {
    private ModificationController modificationController;
    private boolean hasToExecute;

    /**

     Constructeur de la classe StopTranslateCommand
     @param modificationController Le contrôleur de modification associé à la commande.
     */
    public StopTranslateCommand(ModificationController modificationController){
        this.modificationController=modificationController;
    }

    /**

     Méthode d'exécution de la commande StopTranslate.
     */
    @Override
    public void execute() {
        modificationController.stopTranslate();
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

     Active ou désactive la commande.
     */
    public void toggle() {
        hasToExecute = true;
    }
}
