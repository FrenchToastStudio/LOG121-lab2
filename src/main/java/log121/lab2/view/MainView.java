package log121.lab2.view;

import log121.lab2.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Laboratoire 2 LOG121";
    private static final Dimension DIMENSION = new Dimension(700, 700);

    private List<ImageView> imageViews;
    private ImageView activeImageView;
    SelectViewMenuBar selectViewMenuBar;
    public MainView()
    {
        OptionView optionView = new OptionView();
        MainController mainController = new MainController(this);
        imageViews = new ArrayList<ImageView>();
        imageViews.add(new StaticImageView(Color.BLUE));
        imageViews.add(new StaticImageView(Color.BLACK));

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

    public void ChangeImageView(int id)
    {
        if(activeImageView != null)
        {
            getContentPane().remove(activeImageView);
        }
        activeImageView = imageViews.get(id);
        getContentPane().add(activeImageView);
        selectViewMenuBar.select(id);
        revalidate();
        repaint();
    }
}
