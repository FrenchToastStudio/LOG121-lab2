package log121.lab2.controller;

import log121.lab2.model.*;
import log121.lab2.view.ImageView;

/**

 Classe qui contrôle l'affichage d'une image.
 */
public class ImageController {
    private Image image;
    ImageView view;

    /**

     Constructeur qui prend en paramètre un objet {@link ImageView} pour initialiser la vue.
     @param imageView L'objet ImageView à afficher
     */
    public ImageController(ImageView imageView)
    {
        setView(imageView);
    }

    public ImageController()
    {

    }

    /**

     Méthode qui permet de définir la vue à afficher.
     @param imageView L'objet ImageView à afficher
     */
    public void setView(ImageView imageView)
    {
        this.view = imageView;
    }

}
