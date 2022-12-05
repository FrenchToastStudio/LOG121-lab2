package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.commands.Command;
import log121.lab2.model.Position;

public class MoveImageCommand extends Command {
    private ModificationController modificationController;
    private boolean hasToExecute;

    private Position position;
    public MoveImageCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }

    @Override
    public void execute() {
        modificationController.translate(position);
        if(hasToExecute)
            hasToExecute = false;
    }

    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void moveToPosition(Position position){
        this.position = position;
        hasToExecute = true;
    }

}