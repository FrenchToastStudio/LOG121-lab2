package log121.lab2.controller.commands;

import log121.lab2.controller.MainController;

/**
 Classe qui représente la commande de sélection de vue.
 Elle hérite de la classe abstraite {@link Command}.
 */
public class SelectViewCommand extends Command{

    private MainController mainController;
    private int viewId;
    private boolean hasToExecute;

    /**
     Constructeur de la commande SelectViewCommand.
     @param mainController Le contrôleur principal
     @param viewId L'ID de la vue à sélectionner
     */
    public SelectViewCommand(MainController mainController, int viewId)
    {
        this.mainController = mainController;
        this.viewId = viewId;
    }

    /**
     Méthode d'exécution de la commande SelectViewCommand.
     Change la vue courante en fonction de l'ID de la vue sélectionnée.
     */
    @Override
    public void execute() {
        mainController.changeView(viewId);
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

     Définit si la commande doit être exécutée ou non.
     @param bool true si la commande doit être exécutée, false sinon.
     */
    public void setCondition(boolean bool)
    {
        hasToExecute = bool;
    }
}
