package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;
import log121.lab2.model.Position;

/**

 Classe PositionCopyStrategy implémentant l'interface ICopyStrategy
 Cette classe permet de copier une position dans une perspective
 et de la coller dans un contrôleur de modification
 */
public class PositionCopyStategy implements ICopyStrategy {

    private Position position;

    /**

     Méthode pour copier la position dans la perspective spécifiée
     @param perspective la perspective dans laquelle la position sera copiée
     */
    @Override
    public void copy(Perspective perspective) {
        this.position = new Position(perspective.getX(), perspective.getY());
    }

    /**

     Méthode pour coller la position dans le contrôleur de modification spécifié
     @param controller le contrôleur de modification dans lequel la position sera collée
     */
    @Override
    public void paste(ModificationController controller) {
        if(position != null)
        {
            controller.paste(position);
        }
    }
}
