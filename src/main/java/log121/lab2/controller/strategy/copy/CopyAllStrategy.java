package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;
import log121.lab2.model.Position;

/**
 Classe CopyAllStrategy qui implémente l'interface ICopyStrategy.
 Cette classe permet de copier toutes les modifications d'une perspective
 et de les coller dans un contrôleur de modification.
 */
public class CopyAllStrategy implements ICopyStrategy{

    private Position position;
    private int width;
    private int height;

    /**

     Copie la perspective en cours.
     @param perspective La perspective à copier.
     */
    @Override
    public void copy(Perspective perspective) {
        this.position = new Position(perspective.getX(), perspective.getY());
        this.width = perspective.getWidth();
        this.height = perspective.getHeight();
    }

    /**
     Colle la perspective copiée sur le contrôleur de modifications.
     @param controller Le contrôleur de modifications où coller la perspective.
     */
    @Override
    public void paste(ModificationController controller) {
        if(position != null ) {
            controller.paste(position, width, height);
        }
    }
}
