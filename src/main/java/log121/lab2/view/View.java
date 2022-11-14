package log121.lab2.view;

import log121.lab2.controller.Command;

import java.util.ArrayList;

public abstract class View {
    private ArrayList<Command> commands;

    public abstract void activate();

    public abstract void pause();

    public abstract void resume();

    public abstract void destroy();
}
