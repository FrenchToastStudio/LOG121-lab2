package log121.lab2.view;

import log121.lab2.controller.Command;
import log121.lab2.controller.CommandManager;
import log121.lab2.controller.LoadImageCommand;
import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.ChooseImageCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class LoadImageFromImageFileItem extends JMenuItem implements View
{

    private List<Command> commands;
    public LoadImageFromImageFileItem(MainController mainController)
    {

        setText("Choose Image");
        this.commands = new ArrayList<>();

        ChooseImageCommand chooseImageCommand = new ChooseImageCommand(mainController);

        this.commands.add(chooseImageCommand);
        addActionListener((ActionEvent e) ->
        {
            chooseImageCommand.toggle();
        });

        activate();
    }

    @Override
    public void activate() {
        CommandManager.getInstance().attachCommand(this, commands);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }
}
