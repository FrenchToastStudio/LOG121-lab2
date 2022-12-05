package log121.lab2.service.imageStrategy;

import log121.lab2.view.basicView.CustomRadioButton;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class PNGViewStrategy implements IImageViewStrategy {

    private BufferedImage image;
    private Consumer<BufferedImage> consumer;

    @Override
    public void setImage(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setConsumer(Consumer<BufferedImage> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void show() {
        consumer.accept(image);
    }

    @Override
    public int getWidth()
    {
        if(this.image != null)
            return this.image.getWidth();
        return 0;
    }

    @Override
    public int getHeight()
    {
        if(this.image != null)
            return this.image.getHeight();
        return 0;
    }
}
