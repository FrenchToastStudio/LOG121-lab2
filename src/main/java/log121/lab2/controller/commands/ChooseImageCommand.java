package log121.lab2.controller.commands;

import log121.lab2.controller.Command;
import log121.lab2.controller.MainController;

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

    @Override
    public boolean isConditionMet() {
        return canExecute;
    }
    public void toggle()
    {
        canExecute = true;
    }
}
