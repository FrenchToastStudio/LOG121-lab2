package log121.lab2.view.components.menuItem;

import log121.lab2.controller.commands.Command;
import log121.lab2.controller.commands.CommandManager;
import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.SaveImageCommand;
import log121.lab2.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * menu item that launches save image
 */
public class SaveImageMenuItem extends JMenuItem implements View {

    private final Color unselected;
    private final Color selected;
    private List<Command> commands;

    /**
     *
     * @param title title of the menuItem
     * @param mainController controller that handles the data of this
     */
    public SaveImageMenuItem(String title, MainController mainController){
        unselected = getBackground();
        selected = Color.gray;

        setText(title);
        SaveImageCommand saveImageCommand = new SaveImageCommand(mainController);
        addActionListener((ActionEvent e) ->
        {
            saveImageCommand.setCondition(true);
        });

        commands = new ArrayList<>();

        commands.add(saveImageCommand);

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
