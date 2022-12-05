package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;

public interface ICopyStrategy {
    void copy(Perspective perspective);
    void paste(ModificationController controller);
}
