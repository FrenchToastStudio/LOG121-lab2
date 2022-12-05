package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;
import log121.lab2.model.Position;

public class CopyAllStrategy implements ICopyStrategy{

    private Position position;
    private int width;
    private int height;

    @Override
    public void copy(Perspective perspective) {
        this.position = new Position(perspective.getX(), perspective.getY());
        this.width = perspective.getWidth();
        this.height = perspective.getHeight();
    }

    @Override
    public void paste(ModificationController controller) {
        controller.paste(position, width, height);
    }
}
