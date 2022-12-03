package log121.lab2.view;

import log121.lab2.controller.*;
import log121.lab2.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ModificationImageView extends ImageView{

    private int xPosition, yPosition, zoom;
    private double scalingFactor = 1.2;
    public ModificationImageView() {
        super(new ArrayList<Command>() {});


        ModificationController modificationController = new ModificationController(this);

        this.xPosition = this.getWidth()/2;
        this.yPosition = this.getHeight()/2;
        this.zoom = -2;
        MoveImageCommand moveImageCommand = new MoveImageCommand(modificationController);
        ZoomImageCommand zoomImageCommand = new ZoomImageCommand(modificationController);
        addCommand(moveImageCommand);
        addCommand(zoomImageCommand);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int dx = e.getX();
                int dy = e.getY();
                System.out.println("clicked at"+dx + " "+dy);
                System.out.println(isMouseOnImage(e.getPoint()));
                //moveImageCommand.moveToPosition(new Position(e.getX(),e.getY()));
            }
        });

        super.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(isMouseOnImage(e.getPoint())){
                    moveImageCommand.moveToPosition(new Position(e.getX(),e.getY()));
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
