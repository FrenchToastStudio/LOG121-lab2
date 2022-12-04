package log121.lab2.view;

import log121.lab2.controller.MainController;
import log121.lab2.controller.ModificationController;
import log121.lab2.model.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame implements Observer{

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Laboratoire 2 LOG121";
    private static final Dimension DIMENSION = new Dimension(700, 700);

    private List<ImageView> imageViews;
    private ImageView activeImageView;
    SelectViewMenuBar selectViewMenuBar;
    public MainView()
    {

        imageViews = new ArrayList<ImageView>();

        imageViews.add(new StaticImageView());
        imageViews.add(new ModificationImageView());
        imageViews.add(new ModificationImageView());

        MainController mainController = new MainController(this);

        OptionView optionView = new OptionView(mainController);

        this.selectViewMenuBar = new SelectViewMenuBar(mainController,imageViews);

        setJMenuBar(optionView);

        add(selectViewMenuBar, BorderLayout.NORTH);

        //region JFrame Option
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        //endregion

        //THE DEFAULT IMAGE TO SHOW. ALWAYS THE FIRST ONE IN THE LIST
        ChangeImageView(0);

    }
    public void setImageViews(List<ModificationController> controllers)
    {
        imageViews = new ArrayList<>();
        imageViews.add(new StaticImageView());
        imageViews.addAll(controllers.stream().map(ModificationImageView::new).toList());
        imageViews.forEach(this::add);
        ChangeImageView(0);
    }
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
        revalidate();
        repaint();
        activeImageView.resume();
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
    public void updatePath(String string) {

    }

    public void attach(Subject subject)
    {
        for(ImageView v : imageViews) {
            subject.attach(v);
        }
    }


}
