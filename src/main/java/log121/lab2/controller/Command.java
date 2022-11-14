package log121.lab2.controller;

import log121.lab2.view.View;

public abstract class Command {
   // private final String relatedClassName;

    public Command()
    {
        //this.relatedClassName = view.getClass().getName();
    }
    public abstract void Execute();
    public  abstract boolean isConditionMet();
/**
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Command)
            return this.relatedClassName.equals(((Command) obj).relatedClassName) && this.getClass().getName().equals(obj.getClass().getName());
        return super.equals(obj);
    }
    */
}
