package log121.lab2.view;

import javax.swing.*;

public class OptionView extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private static final String OPTION_SAVE = "Sauvegarder";
    private static final String OPTION_LOAD = "Charger";

    public OptionView()
    {
        JMenuItem menuSave = new JMenuItem(OPTION_SAVE);
        JMenuItem menuLoad = new JMenuItem(OPTION_LOAD);
        add(menuSave);
        add(menuLoad);
    }

}
