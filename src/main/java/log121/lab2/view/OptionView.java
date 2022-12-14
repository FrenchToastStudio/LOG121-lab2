package log121.lab2.view;

import log121.lab2.controller.MainController;
import log121.lab2.view.components.menuItem.LoadImageFromImageFileItem;
import log121.lab2.view.components.menuItem.LoadImageMenuItem;
import log121.lab2.view.components.menuItem.SaveImageMenuItem;
import log121.lab2.view.copyStrategyViews.CopyStrategyMenuItem;

import javax.swing.*;

/**
 * This class represents the option buttons in the application
 */
public class OptionView extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private static final String OPTION_SAVE = "Sauvegarder";
    private static final String OPTION_LOAD = "Charger";

    /**
     * The class constructor initiates the menu options
     * @param mainController the main controller that will use this View
     */
    public OptionView(MainController mainController)
    {
        SaveImageMenuItem menuSave = new SaveImageMenuItem(OPTION_SAVE, mainController);
        LoadImageMenuItem menuLoad = new LoadImageMenuItem(OPTION_LOAD, mainController);
        CopyStrategyMenuItem copyStrategyMenuItem = new CopyStrategyMenuItem(mainController);
        LoadImageFromImageFileItem loadImageFromImageFileItem = new LoadImageFromImageFileItem(mainController);

        add(menuSave);
        add(menuLoad);
        add(copyStrategyMenuItem);
        add(loadImageFromImageFileItem);
    }

}
