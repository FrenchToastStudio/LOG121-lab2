package log121.lab2.controller;

public abstract class Command {

    private String classId;

    public Command() {
    }

    public abstract void execute();

    public abstract boolean isConditionMet();

    public void setClassId(String id)
    {
        this.classId = id;
    }

    public String getClassId() {
        return classId;
    }
}
