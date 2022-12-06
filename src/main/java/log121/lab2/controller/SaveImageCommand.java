package log121.lab2.controller;

import log121.lab2.service.JSONWriter;
import log121.lab2.service.SaveState;

/**

 Classe qui représente la commande d'enregistrement d'une image.

 Elle hérite de la classe abstraite {@link Command}.
 */
public class SaveImageCommand extends Command{
    private MainController mainController;
    private boolean hasToExecute;

    public SaveImageCommand(MainController mainController){
        this.mainController = mainController;
    }

    /**

     Méthode d'exécution de la commande SaveImage
     */
    @Override
    public void execute() {
        mainController.save();
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

     Définit si la condition pour exécuter la commande est remplie.
     @param b true si la commande peut être exécutée, false sinon.
     */
    public void setCondition(boolean b) {
        hasToExecute = b;
    }
}
