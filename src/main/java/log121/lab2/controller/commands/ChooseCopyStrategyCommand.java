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

    @Override
    public void execute()
    {
        CopyImageMediator.getInstance().setCopyStrategy(this.copyStrategy);
        needsExecution = false;
    }

    @Override
    public boolean isConditionMet() {
        return needsExecution;
    }

    public void buttonToggle(CopyStrategyEnum copyStrategy)
    {
        needsExecution = true;
        this.copyStrategy = copyStrategy;
    }
}
