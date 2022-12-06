package log121.lab2.view.components.menuItem;

import log121.lab2.controller.commands.Command;
import log121.lab2.controller.commands.CommandManager;
import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.SelectViewCommand;
import log121.lab2.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Now that i think of its all the menu item are the same we could of used the builder design pattern...
 * would of saved a loooot of time
 */
public class SelectImageMenuItem extends JMenuItem implements View {

    private int id;
    private final Color unselected;
    private final Color selected;

    private List<Command> commands;

    /**
     * Constructor
     * @param title title of the menuItem
     * @param id id of the view mostly added for testing purpose but also to select the right view
     * @param mainController controller that handles the event of this view
     */
    public SelectImageMenuItem(String title, int id,MainController mainController)
    {
        unselected = getBackground();
        selected = Color.gray;

        this.id = id;
        setText(title);
        SelectViewCommand selectViewCommand = new SelectViewCommand(mainController, id);
        addActionListener((ActionEvent e) ->
        {
            selectViewCommand.setCondition(true);
        });



        commands = new ArrayList<>();

        commands.add(selectViewCommand);

        activate();
    }

    /**
     * select this menuItem and changes its background
     */
    public void select()
    {
        setBackground(selected);
        Color color = getBackground();
    }

    /**
     *
     */
    public void unSelect()
    {
        setBackground(unselected);
    }

    /**
     *
     * @return the id of this view
     */
    public int getId() {
        return id;
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