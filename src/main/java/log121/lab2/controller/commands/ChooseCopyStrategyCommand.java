package log121.lab2.controller.commands;

import log121.lab2.controller.Command;
import log121.lab2.controller.CopyImageMediator;
import log121.lab2.controller.MainController;
import log121.lab2.controller.strategy.copy.ICopyStrategy;
import log121.lab2.view.copyStrategyViews.CopyStrategyEnum;

public class ChooseCopyStrategyCommand extends Command {
    private boolean needsExecution;
    private CopyStrategyEnum copyStrategy;

    public ChooseCopyStrategyCommand()
    {
    }

    /**
     * Méthode d'exécution de la commande ChooseCopyStrategy
     */
    @Override
    public void execute()
    {
        CopyImageMediator.getInstance().setCopyStrategy(this.copyStrategy);
        needsExecution = false;
    }

    /**
     * Vérifie si la condition pour exécuter la commande est remplie.
     * @return true si la commande peut être exécutée, false sinon.
     */
    @Override
    public boolean isConditionMet() {
        return needsExecution;
    }

    /**
     * Active ou désactive le bouton en fonction de la stratégie de copie sélectionnée.
     * @param copyStrategy La stratégie de copie sélectionnée
     */
    public void buttonToggle(CopyStrategyEnum copyStrategy)
    {
        needsExecution = true;
        this.copyStrategy = copyStrategy;
    }
}
