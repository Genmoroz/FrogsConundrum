package FrogsConundrum.state;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Loader {

    private static final String PATH = "src/FrogsConundrum/State/res/";

    public static BufferedImage load(String path){

        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(PATH + path));
        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }
}
