package log121.lab2.controller;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;
import log121.lab2.model.Store;
import log121.lab2.service.ImagePicker;
import log121.lab2.service.JSONReader;
import log121.lab2.service.JSONWriter;
import log121.lab2.service.SaveState;
import log121.lab2.view.*;

import java.util.ArrayList;
import java.util.List;

/**

 Classe principale contrôleur qui gère la vue principale et les actions de l'utilisateur.
 */
public class MainController {

    private static final int NUMBER_OF_MODIFICATION_VIEW = 2;
    private MainView mainView;
    private Image image;

    /**

     Constructeur qui initialise la vue principale et l'image à afficher.
     @param mainView La vue principale de l'application
     */
    public MainController(MainView mainView)
    {
        this.mainView = mainView;
        this.image = new Image();
        setImage(image);
        image.setPath("src/main/resources/log121/lab2/Image/IMG_0661.jpg");
    }

    /**

     Méthode qui change la vue actuellement affichée dans la fenêtre principale.
     @param viewId L'identifiant de la vue à afficher
     */
    public void changeView(int viewId)
    {
        mainView.ChangeImageView(viewId);
    }

    /**

     Méthode qui sauvegarde l'état actuel de l'application dans un fichier JSON.
     */
    public void save(){
        SaveState saveState = new SaveState(Store.getInstance().getPerspectives(), Store.getInstance().getImage());
        JSONWriter writer = new JSONWriter();
        writer.saveState(saveState);
    }

    /**

     Méthode qui charge un état précédemment sauvegardé de l'application à partir d'un fichier JSON.
     */
    public void load(){
        JSONReader jsonReader = new JSONReader();
        SaveState saveState = jsonReader.load();
        this.image = saveState.getImage();
        List<Perspective> perspectives = saveState.getPerspectiveList();

        setPerspective(perspectives);

        mainView.attachViewsToSubject(image);

        Store.getInstance().setPerspectives(perspectives);
        Store.getInstance().setImage(image);

    }

    /**

     Méthode qui définit l'image à afficher dans l'application.
     @param image L'image à afficher
     */
    private void setImage(Image image)
    {
        Store.getInstance().setImage(image);
    }

    /**

     Méthode qui définit les perspectives à afficher dans l'application.

     @param perspectives La liste des perspectives à afficher
     */
    private void setPerspective(List<Perspective> perspectives)
    {
        List<ModificationController> controllers = new ArrayList<>();
        for (Perspective perspective: perspectives) {
            controllers.add(new ModificationController(perspective));
        }

        mainView.setImageViews(controllers);
    }

    /**

     Crée un nouveau contrôleur de modification pour une perspective donnée.
     @param perspective La perspective pour laquelle le contrôleur sera créé
     @return Un nouveau contrôleur de modification
     */
    private ModificationController setPerspective(Perspective perspective)
    {
        return new ModificationController(perspective);
    }

    /**

     Réinitialise les vues de l'application.
     */
    public void resetViews()
    {

        List<ModificationController> modificationControllers = new ArrayList<>();
        Store.getInstance().resetPerspective();
        for(int i =0; i < NUMBER_OF_MODIFICATION_VIEW; i++)
        {
            modificationControllers.add(createNewModificationController());
        }
        mainView.setImageViews(modificationControllers);
        mainView.attachViewsToSubject(image);
    }

    /**

     Crée un nouveau contrôleur de modification avec une nouvelle perspective.
     @return Le contrôleur de modification créé
     */
    private ModificationController createNewModificationController()
    {
        Perspective perspective = new Perspective();
        image.addPerspective(perspective);
        perspective.setPosition(this.mainView.getWidth()/2, this.mainView.getHeight()/2);
        perspective.setPosition(this.mainView.getWidth()/2, this.mainView.getHeight()/2);
        return new ModificationController(perspective);
    }

    /**

     Ouvre une fenêtre de sélection d'image pour changer l'image actuelle.
     */
    public void changeImage()
    {
        ImagePicker imagePicker = new ImagePicker();
        var newPath = imagePicker.pickImage();
        if(newPath.trim().isEmpty())
        {
            return;
        }
        Store.getInstance().getImage().setPath(newPath);
    }
}
