package log121.lab2.controller.commands;

import log121.lab2.controller.Command;
import log121.lab2.controller.ModificationController;

/**

 Classe qui représente la commande de copie.

 Elle hérite de la classe abstraite {@link Command}.
 */
public class CopyCommand extends Command {


    private boolean needExectuion;
    private ModificationController modificationController;

    /**

     * Constructeur de la classe CopyCommand.
     * @param modificationController Le contrôleur de modification associé à la commande de copie.
     */
    public CopyCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }

    /**

     Exécute la commande de copie en appelant la méthode de copie du contrôleur de modification.
     */
    @Override
    public void execute()
    {
        modificationController.copy();
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

     Active ou désactive la commande de copie.
     */
    public void toggle()
    {
        this.needExectuion = true;
    }
}
