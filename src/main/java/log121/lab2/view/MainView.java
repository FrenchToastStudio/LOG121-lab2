package log121.lab2.view;

import log121.lab2.controller.MainController;
import log121.lab2.controller.ModificationController;
import log121.lab2.model.Subject;
import log121.lab2.view.components.menuBar.SelectViewMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the main window panel
 */
public class MainView extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Laboratoire 2 LOG121";
    private static final Dimension DIMENSION = new Dimension(700, 700);
    private List<ImageView> imageViews;
    private ImageView activeImageView;
    SelectViewMenuBar selectViewMenuBar;

    /**
     * default constructor that will initiate the main window panel
     */
    public MainView()
    {

        imageViews = new ArrayList<ImageView>();

        //region JFrame Option
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        //endregion

        validate();
        // assign a main controller
        MainController mainController = new MainController(this);

        OptionView optionView = new OptionView(mainController);
        this.selectViewMenuBar = new SelectViewMenuBar(mainController);

        mainController.resetViews();

        setJMenuBar(optionView);


    }

    /**
     * this method permits us to add the different image views to the main window
     * @param controllers
     */
    public void setImageViews(List<ModificationController> controllers)
    {
        remove(selectViewMenuBar);

        int width = getWidth();
        int height = getHeight();

        this.imageViews.forEach(ImageView::destroy);
        this.imageViews = new ArrayList<>();
        this.imageViews.add(new StaticImageView());

        for (ModificationController controller: controllers) {
            this.imageViews.add(new ModificationImageView(controller));
        }
        this.imageViews.forEach(imageView ->
        {
            imageView.setMaxSize(width, height);
            imageView.pause();
            this.add(imageView);
        });
        selectViewMenuBar.setTabs(this.imageViews);

        add(selectViewMenuBar, BorderLayout.NORTH);
        ChangeImageView(0);
    }

    /**
     * this method permits to attach a view to a subject
     * @param subject
     */
    public void attachViewsToSubject(Subject subject)
    {
        this.imageViews.forEach(subject::attach);
    }

    /**
     * this method allows to change the imageView
     * @param id
     */
    public void ChangeImageView(int id)
    {
        if(activeImageView != null)
        {
            getContentPane().remove(activeImageView);
            activeImageView.pause();
        }
        activeImageView = imageViews.get(id);
        getContentPane().add(activeImageView);
        selectViewMenuBar.select(id);
        getContentPane().revalidate();
        getContentPane().repaint();
        activeImageView.requestFocus();
        activeImageView.resume();

        revalidate();
        repaint();
    }


    @Override
    public void update() {

    }

    @Override
    public void updatePosition(int x, int y) {

    }

    @Override
    public void updateZoom(int height, int width) {

    }

    @Override
    public void updateImage(BufferedImage image) {

    }


}
