package log121.lab2.service.imageStrategy;

import javafx.css.Size;
import log121.lab2.view.basicView.CustomRadioButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public interface IImageViewStrategy {
    void setImage(String path);
    void setConsumer(Consumer<BufferedImage> consumer);
    void show();
    int getWidth();
    int getHeight();
}
