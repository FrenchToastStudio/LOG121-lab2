package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;

public class SizeCopyStategy implements ICopyStrategy {

    private int width;
    private int height;


    @Override
    public void copy(Perspective perspective) {
        this.width = perspective.getWidth();
        this.height =  perspective.getHeight();
    }

    @Override
    public void paste(ModificationController controller) {
        controller.paste(width, height);
    }
}
