package log121.lab2.view;

import log121.lab2.controller.*;
import log121.lab2.controller.commands.*;
import log121.lab2.model.Position;
import log121.lab2.view.components.listener.CustomMouseWheelListener;
import log121.lab2.view.components.handler.InputHandler;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the views for the modifiable images in the application
 */
public class ModificationImageView extends ImageView{

    private Point lastMousePos;
    private boolean isDragActive;

    ModificationController modificationController;

    public ModificationImageView(int width, int height) {
        super(new ArrayList<Command>() {});
        this.modificationController = new ModificationController(this, width, height);
        viewSetUp();
    }

    public ModificationImageView(ModificationController controller) {
        super(new ArrayList<Command>() {});
        this.modificationController = controller;
        modificationController.setView(this);
        viewSetUp();
    }

    /**
     * this method allows the view to be setup, it initiates the command
     */
    private void viewSetUp()
    {
        MoveImageCommand moveImageCommand = new MoveImageCommand(modificationController);
        ZoomImageCommand zoomImageCommand = new ZoomImageCommand(modificationController);
        CopyCommand copyCommand = new CopyCommand(modificationController);
        PasteCommand pasteCommand = new PasteCommand(modificationController);
        UndoModificationCommand undoModificationCommand = new UndoModificationCommand(modificationController);
        RedoModificationCommand redoModificationCommand = new RedoModificationCommand(modificationController);
        StopTranslateCommand stopTranslateCommand = new StopTranslateCommand(modificationController);
        StopZoomCommand stopZoomCommand = new StopZoomCommand(modificationController);

        addCommand(moveImageCommand);
        addCommand(zoomImageCommand);
        addCommand(copyCommand);
        addCommand(pasteCommand);
        addCommand(undoModificationCommand);
        addCommand(redoModificationCommand);
        addCommand(stopTranslateCommand);
        addCommand(stopZoomCommand);

        /**
         *  This method allows to check when the mouse is clicked and released
         */
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocus();
                if(isMouseOnImage(e.getPoint())){
                    lastMousePos = e.getPoint();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (isDragActive) {
                    stopTranslateCommand.toggle();
                    isDragActive = false;
                }
            }
        });

        /**
         *  This method allows to know when the mouse is being dragged and
         *  then calls the proper method to move the image
         */
        super.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(isDragActive){
                    moveImageCommand.moveToPosition(calculateNewPosition(e.getPoint()));
                }
            }
        });

        /**
         * This method allows the mousewheel to trigger the zoom image command
         */
        super.addMouseWheelListener(new CustomMouseWheelListener(){
            public void mouseWheelMoved(MouseWheelEvent event) {
                super.mouseWheelMoved(event);
                zoomImageCommand.changeZoom(event.getWheelRotation());
            }

            @Override
            public void mouseWheelStop() {
                super.mouseWheelStop();
                stopZoomCommand.toggle();
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

    /**
     * This method allows the program to check if the current mouse point is inside the image
     * @param point the mouse point location
     * @return boolean with the answer if it is or not
     */
    public boolean isMouseOnImage(Point point){
        Rectangle rectangle = getImageLabel().getBounds();
        boolean isOnImage = rectangle.contains(point);
        if (isOnImage) this.isDragActive = isOnImage;
        return isOnImage;
    }

    /**
     * This method allows the new position after a mouse drag on the images to be calculated
     * @param newMousePoint last know mouse location
     * @return the new position
     */
    public Position calculateNewPosition(Point newMousePoint){
        Point imagePositon = getImagePosition();

        int newPositionX = imagePositon.x + (newMousePoint.x - lastMousePos.x);
        int newPositionY = imagePositon.y + (newMousePoint.y - lastMousePos.y);
        lastMousePos = newMousePoint;


        /**
         *  There needs to be a check to make sure the image is not dragged too far outside
         */
        Dimension windowRectangle = super.getSize();
        if(newPositionX < 0){
            newPositionX = 0;
        }
        else if(newPositionX > windowRectangle.width){
            newPositionX = windowRectangle.width;
        }
        if(newPositionY < 0){
            newPositionY = 0;
        }
        else if(newPositionY > windowRectangle.height){
            newPositionY = windowRectangle.height;
        }

        return new Position(newPositionX,newPositionY);
    }



}
