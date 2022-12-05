package log121.lab2.controller.commands;

import log121.lab2.controller.Command;

public class PasteCommand extends Command {
    private boolean needExectuion;
    @Override
    public void execute() {

    }

    @Override
    public void unExecute() {

    }

    @Override
    public boolean isConditionMet() {
        return false;
    }

    public void toggle()
    {
        this.needExectuion = true;
    }
}
