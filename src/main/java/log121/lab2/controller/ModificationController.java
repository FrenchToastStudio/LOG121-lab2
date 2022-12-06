package log121.lab2.controller;

import log121.lab2.controller.Mediator.CopyImageMediator;
import log121.lab2.controller.Memento.PerspectiveMomento;
import log121.lab2.model.Perspective;
import log121.lab2.model.Position;
import log121.lab2.model.Store;
import log121.lab2.view.ImageView;
import log121.lab2.view.ModificationImageView;

import java.util.ArrayList;
import java.util.List;

/**

 Classe contrôleur pour les actions de modification d'une image.

 Elle hérite de la classe {@link ImageController}.
 */
public class ModificationController extends ImageController{
    private Perspective perspective;
    private List<PerspectiveMomento> perspectiveMomentos;
    private int pointer = 0;

    /**

     Constructeur prenant en paramètre l'image à modifier, la largeur et la hauteur de celle-ci.
     @param imageView L'image à modifier
     @param width La largeur de l'image
     @param height La hauteur de l'image
     */
    public ModificationController(ImageView imageView, int width, int height) {
        super(imageView);
        this.perspective = new Perspective();
        perspective.attach(imageView);
        this.perspective.setPosition(width/2, height/2);
        this.perspectiveMomentos = new ArrayList<>();
        Store.getInstance().addPerspective(perspective);
        addMomento();
    }

    /**

     Constructeur prenant en paramètre la perspective de l'image à modifier.
     @param perspective La perspective de l'image à modifier
     */
    public ModificationController(Perspective perspective)
    {
        super();
        this.perspective = perspective;
        this.perspectiveMomentos = new ArrayList<>();
        Store.getInstance().addPerspective(perspective);
        addMomento();
    }

    /**

     Ajoute un memento à la liste de mementos pour l'historique des actions.
     */
    private void addMomento(){
        if (pointer>0) {
            perspectiveMomentos = perspectiveMomentos.subList(0, pointer+1);
        }
        PerspectiveMomento memento = new PerspectiveMomento(this.perspective);
        perspectiveMomentos.add(memento);
        pointer = perspectiveMomentos.size()-1;

    }

    /**

     Définit la vue à utiliser pour afficher l'image.
     @param modificationImageView La vue à utiliser
     */
    public void setView(ModificationImageView modificationImageView)
    {
        super.setView(modificationImageView);
        perspective.attach(modificationImageView);
    }

    /**

     Translate l'image selon la position spécifiée.
     @param position La position de translation
     */
    public void translate(Position position){
        this.perspective.setPosition(position);
    }

    /**

     Arrête la translation de l'image et ajoute un memento pour l'historique des actions.
     */
    public void stopTranslate(){
        addMomento();
    }

    /**

     Zoom sur l'image selon le niveau de zoom spécifié.
     @param zoom Le niveau de zoom à appliquer
     */
    public void zoom(int zoom){
        this.perspective.setZoom(-zoom);
    }

    /**

     Arrête le zoom sur l'image.
     */
    public void stopZoom()
    {
        addMomento();
    }

    /**

     Annule la dernière modification apportée à l'image.
     */
    public void undo()
    {
        if (pointer>0) {
            pointer--;
            PerspectiveMomento momento = perspectiveMomentos.get(pointer);
            momento.setPerspectiveToState(this.perspective);

        }
    }

    /**

     Rétablit la dernière modification annulée sur l'image.
     */
    public void redo()
    {
        if (pointer<perspectiveMomentos.size()-1){
            pointer++;
            PerspectiveMomento momento = perspectiveMomentos.get(pointer);
            momento.setPerspectiveToState(this.perspective);
        }
    }

    /**

     Copie l'image dans le presse-papier.
     */
    public void copy() {
        CopyImageMediator.getInstance().copy(this.perspective);
    }

    /**

     Colle l'image copiée dans le presse-papier à la position et aux dimensions spécifiées.
     @param position La position où coller l'image.
     @param width La largeur de l'image collée.
     @param height La hauteur de l'image collée.
     */
    public void paste(Position position, int width, int height)
    {
        this.perspective.setPosition(position);
        this.perspective.setSize(width, height);
        addMomento();
    }

    /**

     Colle l'image copiée dans le presse-papier à la position spécifiée.
     @param position La position où coller l'image.
     */
    public void paste(Position position)
    {
        this.perspective.setPosition(position);
        addMomento();
    }

    /**

     Colle l'image copiée dans le presse-papier aux dimensions spécifiées.
     @param width La largeur de l'image collée.
     @param height La hauteur de l'image collée.
     */
    public void paste(int width, int height)
    {
        this.perspective.setSize(width, height);
        addMomento();
    }


}
