package com.frost.frog.state;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Loader {

    private static final String PARENT_PATH = "src/com/frost/frog/state/res/";

    public static BufferedImage load(String fileName) {
        try {
            return ImageIO.read(new File(PARENT_PATH + fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
