package log121.lab2.view;

import log121.lab2.controller.Command;
import log121.lab2.controller.CommandManager;

import javax.swing.*;
import java.util.List;

public abstract class ImageView extends JPanel implements View{

    private List<Command> commands;

    public ImageView(List<Command> commands) {
        this .commands = commands;
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
