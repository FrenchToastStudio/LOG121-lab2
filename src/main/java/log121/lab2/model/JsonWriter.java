package log121.lab2.model;

import log121.lab2.controller.MainController;
import log121.lab2.view.ImageView;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    private static FileWriter file;
    private MainController mainController;
    private Image image;
    private List<ImageView> views;
    private int activeView;

    public void saveState(Image image, List<ImageView> views, int activeView){
        JSONObject obj = new JSONObject();
        obj.put("Image", image);
        obj.put("Views", views);
        obj.put("Active View", activeView);

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("/Users/Shared/lab2.txt");
            file.write(obj.toJSONString());
            ImageLog("Successfully Copied JSON Object to File...");
            ImageLog("\nJSON Object: " + obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    static public void ImageLog(String str) {
        System.out.println("str");
    }

}
