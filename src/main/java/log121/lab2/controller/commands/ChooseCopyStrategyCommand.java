package log121.lab2.controller.commands;

import log121.lab2.controller.Mediator.CopyImageMediator;
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
