package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;

/**

 Interface représentant une stratégie de copie.

 @author Nom de l'auteur
 */
public interface ICopyStrategy {
    /**

     Méthode permettant de copier une perspective.
     @param perspective la perspective à copier
     */
    void copy(Perspective perspective);

    /**

     Méthode permettant de coller une perspective copiée.
     @param controller le contrôleur de modification à utiliser pour coller la perspective
     */
    void paste(ModificationController controller);
}
