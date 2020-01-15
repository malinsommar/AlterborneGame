package davidtest.overworld.gfx;

/**
 *assign the four basic shade colours an int-value that can be called within the Tile, fonts or Mob-class to give specified colour
 * @author David Furby
 * @version 1
 */
public class Colours {
    /**
     * get ColourValues that can then be assigned to either Entities or
     * @param colour1 the first colour on the tile
     * @param colour2 the second colour on the tile
     * @param colour3 the third colour on the tile
     * @param colour4 the fourth colour on the tile
     * @return the colour values used within the SpriteSheet
     */
    public static int get(int colour1, int colour2, int colour3, int colour4) {
        return (get(colour4) << 24)  //white
                + (get(colour3) << 16) //light gray
                + (get(colour2) << 8) //dark gray
                + get(colour1); //black
    }

    /**
     * assign the colour-scale within the 255 number-parameter
     * @param colour the combined values for the colours
     * @return the colour within the correct scale
     */
    private static int get(int colour) {
        if (colour < 0) return 255; //if a negative number is assigned during the process of choosing colors its assigned to won't render
        int r = colour / 100%10; //colours divided by
        int g = colour / 10%10; //colours divided by
        int b = colour % 10;
        return r * 36 + g * 6 + b;
    }
}
