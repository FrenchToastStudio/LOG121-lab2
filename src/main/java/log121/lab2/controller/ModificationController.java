package log121.lab2.controller;

import log121.lab2.model.Perspective;

import java.util.Stack;

public class ModificationController extends ImageController{
    private Perspective perspective;
    private int undoRedoPointer = -1;
    private final Stack<Command> commandStack = new Stack<>();

    public void translate(int x, int y){

    }

    public void zoom(int percentage){

    }

    private void insertCommand()
    {
        deleteElementsAfterPointer(undoRedoPointer);
        PerspectiveMomento m = createMomento();
        Command command = new UndoModificationCommand(m, perspective);
        command.execute();
        commandStack.push(command);
        undoRedoPointer++;
    }

    private void deleteElementsAfterPointer(int undoRedoPointer)
    {
        if (commandStack.size()<1)return;
        if (commandStack.size() > undoRedoPointer + 1) {
            commandStack.subList(undoRedoPointer + 1, commandStack.size()).clear();
        }
    }

    private void undo()
    {
        Command command = commandStack.get(undoRedoPointer);
        command.unExecute();
        undoRedoPointer--;
    }

    private void redo()
    {
        if(undoRedoPointer == commandStack.size() - 1)
            return;
        undoRedoPointer++;
        Command command = commandStack.get(undoRedoPointer);
        command.execute();
    }

    private PerspectiveMomento createMomento(){
        PerspectiveMomento m = new PerspectiveMomento();
        m.setPerspective(this.perspective);
        return m;
    }

    private void setMomento(PerspectiveMomento m){
        this.perspective = m.getPerspective();
    }
}
