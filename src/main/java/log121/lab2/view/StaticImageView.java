package log121.lab2.view;

import log121.lab2.controller.Command;

import java.util.List;

public class StaticImageView extends ImageView {

    private List<Command> commands;

    public StaticImageView(List<Command> commands) {
        super(commands);
    }

    @Override
    public void update(int x, int y, int zoom) {

    }

    @Override
    public void update(String path) {

    }

    @Override
    public void update() {

    }
}
