package log121.lab2.service;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

public class ImagePicker {

    public String pickImage()
    {
        String path = "";
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));

        int status = fileChooser.showSaveDialog(null);

        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Constructs a FileWriter given a file name, using the platform's default charset
                path = selectedFile.getCanonicalPath();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return path;
    }
}
