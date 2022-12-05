package log121.lab2.controller;

import log121.lab2.view.View;

import java.util.*;
import java.util.List;

public class CommandManager {
    private static CommandManager instance;
    private List<Command> commands;
    private volatile boolean isExecuting;
    private List<Command> addList;
    private List<String> removeList;

    private CommandManager()
    {
        this.commands = new ArrayList<>();
        this.removeList = new ArrayList<>();
        this.addList = new ArrayList<>();
    }

    public static CommandManager getInstance()
    {
        if(instance == null)
            instance = new CommandManager();
        return  instance;
    }

    public void Execute()
    {
        this.commands.stream()
                .filter(Command::isConditionMet)
                .forEach(Command::execute);
    }

    public void attachCommand(View view, List<Command> commands) {
        commands.forEach(command -> command.setClassId(view.toString()));
        if(this.isExecuting)
        {
            this.addList.addAll(commands);
        }
        else {
            this.commands.addAll(commands);
        }
    }
    public void detachCommand(View view)
    {
        if(this.isExecuting)
        {
            removeList.add(view.toString());
        }
        else {
            this.commands.removeIf(command -> command.getClassId() == view.toString());
        }
    }

    private void executeRemoveQueue()
    {
        if(!removeList.isEmpty()) {
            this.removeList.forEach(view -> commands.removeIf(command -> Objects.equals(command.getClassId(), view)));
            removeList = new ArrayList<>();
        }
    }
    private void executeAddQueue()
    {
        if(!addList.isEmpty()) {
            commands.addAll(addList);
            addList = new ArrayList<>();
        }
    }

    public static void launch()
    {
        new Thread(new Runnable() {
            @Override public void run() {
                while (true)
                {
                    getInstance().executeAddQueue();
                    getInstance().executeRemoveQueue();
                    getInstance().isExecuting = true;
                    getInstance().Execute();
                    getInstance().isExecuting = false;
                }
            }
        }).start();
    }



}
