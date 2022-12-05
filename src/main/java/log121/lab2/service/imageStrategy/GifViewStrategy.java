package log121.lab2.service.imageStrategy;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class GifViewStrategy implements IImageViewStrategy{

    private static final int TIMER_DELAY = 200;
    private List<BufferedImage> bufferedImageList;
    private Consumer<BufferedImage> consumer;
    private Timer timer;
    private boolean execute;


    // INSPIRED BY:
    //   https://stackoverflow.com/questions/8933893/convert-each-animated-gif-frame-to-a-separate-bufferedimage
    // CONSULTEd ON 5/12/2022
    @Override
    public void setImage(String path)
    {
        this.bufferedImageList = new ArrayList<>();
        String[] imageatt = new String[]{
                "imageLeftPosition",
                "imageTopPosition",
                "imageWidth",
                "imageHeight"
        };

        ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
        try {
            ImageInputStream ciis = ImageIO.createImageInputStream(new File(path));
            reader.setInput(ciis, false);
            int noi = reader.getNumImages(true);
            BufferedImage master = null;

            for (int i = 0; i < noi; i++) {
                BufferedImage image = reader.read(i);
                IIOMetadata metadata = reader.getImageMetadata(i);

                Node tree = metadata.getAsTree("javax_imageio_gif_image_1.0");
                NodeList children = tree.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node nodeItem = children.item(j);

                    if(((org.w3c.dom.Node) nodeItem).getNodeName().equals("ImageDescriptor")){
                        Map<String, Integer> imageAttr = new HashMap<String, Integer>();

                        for (int k = 0; k < imageatt.length; k++) {
                            NamedNodeMap attr = nodeItem.getAttributes();
                            Node attnode = attr.getNamedItem(imageatt[k]);
                            imageAttr.put(imageatt[k], Integer.valueOf(attnode.getNodeValue()));
                        }
                        if(i==0){
                            master = new BufferedImage(imageAttr.get("imageWidth"), imageAttr.get("imageHeight"), BufferedImage.TYPE_INT_ARGB);
                        }
                        master.getGraphics().drawImage(image, imageAttr.get("imageLeftPosition"), imageAttr.get("imageTopPosition"), null);
                    }
                }
                BufferedImage frame = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
                frame.setData(master.getData());
                this.bufferedImageList.add(frame);

                //ImageIO.write(master, "GIF", new File( i + ".gif"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setConsumer(Consumer<BufferedImage> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void show()
    {
        final GifViewStrategy gifViewStrategy = this;


        gifViewStrategy.execute =true;
        new Thread(new Runnable() {
            @Override public void run() {
                int pointer = 0;
                while (gifViewStrategy.execute)
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    pointer ++;
                    if(pointer >= bufferedImageList.size() )
                    {
                        pointer = 0;
                    }

                    BufferedImage image = bufferedImageList.get(pointer);
                    gifViewStrategy.consumer.accept(image);
                }
            }
        }).start();
    }

    @Override
    public int getWidth() {
        if(this.bufferedImageList != null)
        {
            if(this.bufferedImageList.size()>0)
            {
              return bufferedImageList.get(0).getWidth();
            }
        }
        return 0;
    }

    @Override
    public int getHeight() {
        if(this.bufferedImageList != null)
        {
            if(this.bufferedImageList.size()>0)
            {
                return bufferedImageList.get(0).getHeight();
            }
        }
        return 0;
    }

    private void nextImage()
    {
        this.timer.cancel();
        this.timer = null;


    }
}
