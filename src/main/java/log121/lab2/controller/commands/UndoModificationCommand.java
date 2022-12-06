package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.commands.Command;

/**

 Classe qui représente la commande d'annulation de modification.
 Elle hérite de la classe abstraite {@link Command}.
 */
public class UndoModificationCommand extends Command {
    private ModificationController modificationController;
    private boolean needExectuion;

    /**

     Constructeur de la classe UndoModificationCommand.
     @param modificationController Le contrôleur de modification à utiliser
     */
    public UndoModificationCommand(ModificationController modificationController){
        this.modificationController = modificationController;
    }

    /**

     Méthode d'exécution de la commande UndoModification
     */
    @Override
    public void execute() {
        modificationController.undo();
        needExectuion = false;
    }

    /**

     Vérifie si la condition pour exécuter la commande est remplie.
     @return true si la commande peut être exécutée, false sinon.
     */
    @Override
    public boolean isConditionMet() {
        return needExectuion;
    }

    /**

     Active ou désactive la commande d'annulation de modification.
     */
    public void toggle()
    {
        needExectuion = true;
    }
}
