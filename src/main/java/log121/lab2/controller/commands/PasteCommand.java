package log121.lab2.controller.commands;

import log121.lab2.controller.Mediator.CopyImageMediator;
import log121.lab2.controller.ModificationController;

public class PasteCommand extends Command {
    private boolean needExectuion;
    private ModificationController modificationController;

    public PasteCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }

    /**

     Vérifie si la condition pour exécuter la commande est remplie.
     @return true si la commande peut être exécutée, false sinon.
     */
    @Override
    public void execute()
    {
        CopyImageMediator.getInstance().paste(modificationController);
        needExectuion = false;
    }

    @Override
    public boolean isConditionMet() {
        return needExectuion;
    }


    public void toggle()
    {
        this.needExectuion = true;
    }
}
