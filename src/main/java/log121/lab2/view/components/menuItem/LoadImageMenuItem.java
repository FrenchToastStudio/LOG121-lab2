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

public class LoadImageMenuItem extends JMenuItem implements View {

    private final Color unselected;
    private final Color selected;
    private List<Command> commands;

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

    public void select()
    {
        setBackground(selected);
    }

    public void unSelect()
    {
        setBackground(unselected);
    }

    public void activate()
    {
        CommandManager.getInstance().attachCommand(this, commands);
    }

    public void pause()
    {
        CommandManager.getInstance().detachCommand(this);
    }

    public void resume()
    {
        CommandManager.getInstance().attachCommand(this, commands);
    }

    public void destroy()
    {
        CommandManager.getInstance().detachCommand(this);
    }
}
