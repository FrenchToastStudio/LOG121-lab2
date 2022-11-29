package log121.lab2.controller;

import log121.lab2.view.View;

import java.util.HashMap;
import java.util.List;

public class CommandManager {
    private static CommandManager instance;
    private HashMap<String, List<Command>> commands;

    private CommandManager()
    {
        this.commands = new HashMap<>();
    }

    public static CommandManager getInstance()
    {
        if(instance == null)
            instance = new CommandManager();
        return  instance;
    }

    public void Execute()
    {
        for (String key: commands.keySet()) {
            for (Command command: commands.get(key)) {
                if (command.isConditionMet())
                    command.execute();
            }
        }
    }

    public void attachCommand(View view, List<Command> commands)
    {
        this.commands.put(view.toString(), commands);
    }
    public void detachCommand(View view)
    {
        this.commands.remove(view.toString());
    }

    public static void launch()
    {
        new Thread(new Runnable() {
            @Override public void run() {
                while (true)
                {
                    getInstance().Execute();
                }
            }
        }).start();
    }
}
