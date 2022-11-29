package log121.lab2.view;

import log121.lab2.controller.Command;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StaticImageView extends ImageView {

    private List<Command> commands;

    public StaticImageView(Color color) {
        super(new ArrayList<Command>() {
        });
        setBackground(color);
    }
    public StaticImageView(List<Command> commands) {
        super(commands);
    }

    @Override
    public void update() {

    }
}
