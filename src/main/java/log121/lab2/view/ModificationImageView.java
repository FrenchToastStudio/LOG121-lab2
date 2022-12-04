package log121.lab2.view;

import log121.lab2.controller.*;
import log121.lab2.model.Position;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ModificationImageView extends ImageView{

    private int xPosition, yPosition, zoom;
    private double scalingFactor = 1.2;
    private Point lastMousePos;

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
        addCommand(moveImageCommand);
        addCommand(zoomImageCommand);

        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMousePos = e.getPoint();
            }
        });

        super.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(isMouseOnImage(e.getPoint())){
                    moveImageCommand.moveToPosition(calculateNewPosition(e.getPoint()));
                }
            }
        });

        super.addMouseWheelListener(new MouseWheelListener(){
            public void mouseWheelMoved(MouseWheelEvent event) {
                zoomImageCommand.changeZoom(event.getWheelRotation());
            }
        });

        activate();
    }

    public boolean isMouseOnImage(Point point){
        Rectangle rectangle = getImageLabel().getBounds();
        return rectangle.contains(point);
    }

    public Position calculateNewPosition(Point newMousePoint){
        int newPositionX = this.xPosition + (newMousePoint.x - lastMousePos.x);
        int newPositionY = this.yPosition + (newMousePoint.y - lastMousePos.y);
        lastMousePos = newMousePoint;
        updateImagePos(newPositionX,newPositionY);
        return new Position(newPositionX,newPositionY);
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

    public void updateImagePos(int x, int y) {
    this.xPosition = x;
    this.yPosition = y;
    }

    @Override
    public void updatePath(String string) {
        this.showImage(string);
    }
}
