package log121.lab2.view;

import log121.lab2.controller.Command;
import log121.lab2.controller.CommandManager;
import log121.lab2.controller.MainController;
import log121.lab2.controller.SaveImageCommand;
import log121.lab2.model.Store;
import log121.lab2.service.SaveState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SaveImageMenuItem extends JMenuItem implements View{

    private final Color unselected;
    private final Color selected;
    private List<Command> commands;
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
