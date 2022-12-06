package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.commands.Command;

/**
 *  Classe qui représente la commande de redo des modifications.
 *  Elle hérite de la classe abstraite {@link Command}.
 */
public class RedoModificationCommand extends Command{

    private ModificationController modificationController;
    private boolean hasToExecute;

    /**
     Constructeur de la commande RedoModification
     @param modificationController Le contrôleur de modification
     */
    public RedoModificationCommand(ModificationController modificationController){
        this.modificationController = modificationController;
    }

    /**
     Méthode d'exécution de la commande RedoModification
     */
    @Override
    public void execute() {
        modificationController.redo();
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
     Active ou désactive la possibilité d'exécuter la commande.
     */
    public void toggle(){hasToExecute = true;}
}
