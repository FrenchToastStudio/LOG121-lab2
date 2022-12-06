package log121.lab2.controller.commands;

import log121.lab2.controller.MainController;
/**

 Classe qui représente la commande de choix d'une image.

 Elle hérite de la classe abstraite {@link Command}.
 */
public class ChooseImageCommand extends Command {
    public boolean canExecute;
    MainController controller;

    public ChooseImageCommand(MainController controller)
    {
        this.controller = controller;
    }

    @Override
    public void execute()
    {
        controller.changeImage();
        canExecute = false;
    }

    /**
     * Vérifie si la condition pour exécuter la commande est remplie.
     * @return true si la commande peut être exécutée, false sinon.
     */
    @Override
    public boolean isConditionMet() {
        return canExecute;
    }

    /**
     Active ou désactive la commande de choix d'image.
     */
    public void toggle()
    {
        canExecute = true;
    }
}
