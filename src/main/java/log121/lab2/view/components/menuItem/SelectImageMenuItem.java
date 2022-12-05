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

public class SelectImageMenuItem extends JMenuItem implements View {

    private int id;
    private final Color unselected;
    private final Color selected;

    private List<Command> commands;
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

    public void select()
    {
        setBackground(selected);
        Color color = getBackground();
    }

    public void unSelect()
    {
        setBackground(unselected);
    }

    public int getId() {
        return id;
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