package log121.lab2.controller;

public abstract class Command {

    private Object object;

    public Command() {
    }

    public abstract void execute();

    public abstract boolean isConditionMet();



    public void setClassId(Object object)
    {
        this.object = object;
    }

    public boolean checkIfCommandOfClass(Object object) {
        return this.object == object;
    }
}
