package davidtest.overworld.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

//set variables
public class SpriteSheet {
    public String path;
    public int width;
    public int height;

    public int[] pixels;

    public SpriteSheet(String path) {
        BufferedImage image = null;
        InputStream myImage;
        try {
            myImage = SpriteSheet.class.getResourceAsStream(path);

            image = ImageIO.read(myImage);
        } catch (IOException e) {
            e.printStackTrace(); //create an image
        }  catch (Exception e) {
            e.printStackTrace();
        }
        if (image == null) {
            return;
        }
        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();

        pixels = image.getRGB(0,0,width,height,null,0,width);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff)/64;
        }
        for (int i = 0; i<8;i++) {
            System.out.println(pixels[i]);
        }
    }
}
