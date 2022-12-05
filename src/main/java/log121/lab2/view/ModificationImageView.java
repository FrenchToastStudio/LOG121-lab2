package log121.lab2.view;

import log121.lab2.controller.*;
import log121.lab2.controller.commands.CopyCommand;
import log121.lab2.controller.commands.PasteCommand;
import log121.lab2.controller.commands.StopTranslateCommand;
import log121.lab2.model.Position;
import log121.lab2.model.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ModificationImageView extends ImageView{

    private boolean isDragging;
    private int xPosition, yPosition, zoom;
    private double scalingFactor = 1.2;
    ModificationController modificationController;

    public ModificationImageView() {
        super(new ArrayList<Command>() {});
        this.modificationController = new ModificationController(this);
        viewSetUp();
    }

    public ModificationImageView(ModificationController controller) {
        super(new ArrayList<Command>() {});
        this.modificationController = controller;
        modificationController.setView(this);
        viewSetUp();
    }

    private void viewSetUp()
    {
        this.xPosition = this.getWidth()/2;
        this.yPosition = this.getHeight()/2;
        this.zoom = -2;
        MoveImageCommand moveImageCommand = new MoveImageCommand(modificationController);
        ZoomImageCommand zoomImageCommand = new ZoomImageCommand(modificationController);
        CopyCommand copyCommand = new CopyCommand(modificationController);
        PasteCommand pasteCommand = new PasteCommand();
        UndoModificationCommand undoModificationCommand = new UndoModificationCommand(modificationController);
        RedoModificationCommand redoModificationCommand = new RedoModificationCommand(modificationController);
        StopTranslateCommand stopTranslateCommand = new StopTranslateCommand(modificationController);

        addCommand(moveImageCommand);
        addCommand(zoomImageCommand);
        addCommand(copyCommand);
        addCommand(pasteCommand);
        addCommand(undoModificationCommand);
        addCommand(redoModificationCommand);
        addCommand(stopTranslateCommand);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int dx = e.getX();
                int dy = e.getY();
                System.out.println("clicked at"+dx + " "+dy);
                System.out.println(isMouseOnImage(e.getPoint()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (isDragging) {
                    stopTranslateCommand.toggle();
                    isDragging = false;
                }
            }
        });


        super.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                requestFocusInWindow();
                if(isMouseOnImage(e.getPoint())){
                    isDragging = true;
                    moveImageCommand.moveToPosition(new Position(e.getX(),e.getY()));
                }
            }
        });

        super.addMouseWheelListener(new MouseWheelListener(){
            public void mouseWheelMoved(MouseWheelEvent event) {
                zoomImageCommand.changeZoom(event.getWheelRotation());
            }
        });

        InputHandler inputHandler = new InputHandler();

        super.addKeyListener(inputHandler);

        Set<Integer> copyKeyList = new HashSet<>();
        copyKeyList.add(KeyEvent.VK_CONTROL);
        copyKeyList.add(KeyEvent.VK_C);

        Set<Integer> pasteKeyList = new HashSet<>();
        pasteKeyList.add(KeyEvent.VK_CONTROL);
        pasteKeyList.add(KeyEvent.VK_V);

        Set<Integer> undoKeyList = new HashSet<>();
        undoKeyList.add(KeyEvent.VK_CONTROL);
        undoKeyList.add(KeyEvent.VK_Z);

        Set<Integer> redoKeyList = new HashSet<>();
        redoKeyList.add(KeyEvent.VK_CONTROL);
        redoKeyList.add(KeyEvent.VK_Z);
        redoKeyList.add(KeyEvent.VK_SHIFT);


        inputHandler.addEvent(copyKeyList, (KeyEvent) ->
        {
            copyCommand.toggle();
        });
        inputHandler.addEvent(pasteKeyList, (KeyEvent) ->
        {
            pasteCommand.toggle();
        });
        inputHandler.addEvent(undoKeyList, (KeyEvent) ->
        {
            undoModificationCommand.toggle();
        });
        inputHandler.addEvent(redoKeyList, (KeyEvent) ->
        {
            redoModificationCommand.toggle();
        });


        activate();
    }

    public boolean isMouseOnImage(Point point){
        Rectangle rectangle = getImageLabel().getBounds();
        return rectangle.contains(point);
    }

    public ModificationImageView(Color bg)
    {
        this();
        this.setBackground(bg);
    }

    public void showImage(String imagePath) {
        super.showImage(imagePath, xPosition, yPosition, zoom);
    }

    public void showImage(int zoom){
        super.showImage(imagePath, xPosition, yPosition, zoom);
    }

    public ModificationImageView(List<Command> commands) {
        super(commands);
    }




    @Override
    public void updatePath(String string) {
        this.showImage(string);
    }
}
