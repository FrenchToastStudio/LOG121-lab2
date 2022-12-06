package log121.lab2.controller.strategy.copy;

import log121.lab2.controller.ModificationController;
import log121.lab2.model.Perspective;

/**

 Classe qui implémente l'interface ICopyStrategy pour effectuer une copie de la taille de la perspective.
 */
public class SizeCopyStategy implements ICopyStrategy {

    private int width;
    private int height;

    /**

     Méthode qui effectue une copie de la largeur et hauteur de la perspective donnée en paramètre.
     @param perspective perspective dont on veut copier la taille
     */
    @Override
    public void copy(Perspective perspective) {
        this.width = perspective.getWidth();
        this.height =  perspective.getHeight();
    }

    /**

     Méthode qui colle la largeur et hauteur copiées précédemment en utilisant le contrôleur de modification donné en paramètre.
     @param controller contrôleur de modification utilisé pour coller la taille copiée
     */
    @Override
    public void paste(ModificationController controller) {
        if(controller != null) {
            controller.paste(width, height);
        }
    }
}
