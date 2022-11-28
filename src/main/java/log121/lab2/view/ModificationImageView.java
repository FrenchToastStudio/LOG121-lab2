package log121.lab2.view;

import log121.lab2.controller.Command;
import log121.lab2.model.Perspective;
import log121.lab2.model.Store;

import java.util.List;

public class ModificationImageView extends ImageView {

    public ModificationImageView(List<Command> commands) {
        super(commands);
    }


    @Override
    public void update(int x, int y, int zoom) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
    }

    @Override
    public void update(String path) {
        this.path = path;
    }

    @Override
    public void update() {

        }

    }
}
