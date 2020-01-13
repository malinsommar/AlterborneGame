package davidtest.overworld.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/*The spriteSheet is the image made to draw the sprites seen within the OverWorld. This class is made to call
them within the program*/
public class SpriteSheet {
    public String path; //path to the image
    public int width; //the width of the SpriteSheet
    public int height; //the height of the SpriteSheet

    public int[] pixels; //handle the pixelData within the SpriteSheet

    //set the "path" variable within the parenthesis so when the method is called it can be assigned the image
    public SpriteSheet(String path) {
        BufferedImage image = null; //a buffer to contain image-data
        InputStream myImage;
        try {
            myImage = SpriteSheet.class.getResourceAsStream(path); //Call the SpriteSheet class and assign it to "myImage"
            image = ImageIO.read(myImage); //Use the imageIO-class to read the image
        } catch (Exception e) {
            e.printStackTrace(); //create an image
        }

        //if image stays null and isn't loaded: return and reload the function until its loaded.
        if (image == null) {
            return;
        }

        //set the classVariables
        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();

        //makes the images colours be based around a red,green and blue spectrum
        pixels = image.getRGB(0,0,width,height,null,0,width);

        /*essentially when dealing with the colour references this for-loop will hide the alpha-channel part of the colour
        code (the "AA" within something like: oxRRGGBB) since the colours wont be transparent */
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff)/64;
        }
    }
}
