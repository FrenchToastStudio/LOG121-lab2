package log121.lab2.controller;

import log121.lab2.model.Perspective;
import log121.lab2.model.Position;
import log121.lab2.model.Store;
import log121.lab2.view.ImageView;

import java.util.Stack;

public class ModificationController extends ImageController{
    private Perspective perspective;
    private int undoRedoPointer = -1;
    private final Stack<Command> commandStack = new Stack<>();

    public ModificationController(ImageView imageView) {
        super(imageView);
        this.perspective = new Perspective();
        perspective.attach(imageView);
        Store.getInstance().addPerspective(perspective);
    }

    public void translate(Position position){
        this.perspective.setPosition(position);
    }

    public void zoom(int percentage){
        this.perspective.setZoom(percentage);
    }

    private void insertCommand()
    {
        deleteElementsAfterPointer(undoRedoPointer);
        PerspectiveMomento m = createMomento();
        Command command = new UndoModificationCommand(m, m.getPerspective());
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
