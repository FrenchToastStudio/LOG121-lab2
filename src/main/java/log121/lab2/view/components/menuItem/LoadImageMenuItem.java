package log121.lab2.view.components.menuItem;

import log121.lab2.controller.commands.Command;
import log121.lab2.controller.commands.CommandManager;
import log121.lab2.controller.commands.LoadImageCommand;
import log121.lab2.controller.MainController;
import log121.lab2.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LoadImageMenuItem extends JMenuItem implements View {

    private final Color unselected;
    private final Color selected;
    private List<Command> commands;

    /**
     * Constructor
     * @param title title of the menuItem
     * @param mainController controlleur that handles the data of this COULD implement a interface for this so we can pass a more generic controller
     */
    public LoadImageMenuItem(String title,MainController mainController){
        unselected = getBackground();
        selected = Color.gray;

        setText(title);
        LoadImageCommand loadImageCommand = new LoadImageCommand(mainController);
        addActionListener((ActionEvent e) ->
        {
            loadImageCommand.setCondition(true);
        });

        commands = new ArrayList<>();

        commands.add(loadImageCommand);

        activate();
    }

    /**
     * Called when the view is started
     * set ups the command of the view
     */
    public void activate()
    {
        CommandManager.getInstance().attachCommand(this, commands);
    }

    /**
     * Called whe  the view is paused
     * removes the command of the view
     */
    public void pause()
    {
        CommandManager.getInstance().detachCommand(this);
    }

    /**
     * called when view is resumed
     * add the command of the view
     */
    public void resume()
    {
        CommandManager.getInstance().attachCommand(this, commands);
    }

    /**
     * called the view is destroyed
     * remove the command of the view
     */
    public void destroy()
    {
        CommandManager.getInstance().detachCommand(this);
    }
}
