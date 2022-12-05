package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;
import log121.lab2.model.Position;

public class PositionCopyStategy implements ICopyStrategy {

    private Position position;


    @Override
    public void copy(Perspective perspective) {
        this.position = new Position(perspective.getX(), perspective.getY());
    }

    @Override
    public void paste(ModificationController controller) {
        if(position != null)
        {
            controller.paste(position);
        }
    }
}
