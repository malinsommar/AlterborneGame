package davidtest.overworld.gfx;


//The
public class Screen {

    private static final byte BIT_MIRROR_X = 0x01;
    private static final byte BIT_MIRROR_Y = 0x02;

    public int[] pixels; //store the pixel-data

    //variables to give a "cameraPoint"
    private int xOffset = 0;
    private int yOffset = 0;

    public int width; //screen width
    public int height; //screen height

    private SpriteSheet sheet; //move the sheet to make it local with the screen.

    //Assign "these" variables into the constructors parameters and then call them in WorldView with its values
    public Screen(int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        this.sheet = sheet;

        pixels = new int[width * height]; //make the pixel-array hold the values of the spriteSheets width and height
    }

        //render the tiles and colours depending on the screen.
    public void render(int xPos, int yPos, int tile, int colour, int mirrorDir, int scale) {
        //control the position of the SpriteSheet within the frame.
        xPos -= xOffset;
        yPos -= yOffset;


        boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0; //boolean used for flipping the y-pixels
        boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0; //boolean used for flipping the x-pixels


        int scaleMap = (scale - 1);
        int xTile = tile % 32; //give the the whole x-position row on the SpriteSheet
        int yTile = tile / 32; //give the the whole y-position row on the SpriteSheet
        int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width; //gives the distance between the start and end of the spriteSheet


        for (int y = 0; y < 8; y++) {
            int ySheet = y; //ySheet gets the same value as y
            if (mirrorY) //if mirrorY is true
                ySheet = 7 - y;// variable to flip the y-pixels by subtracting 7 by the y:value


            int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3) / 2);//identify the pixels on the y-row of the tiles


            for (int x = 0; x < 8; x++) {
                int xSheet = x; //xSheet gets the same value as x
                if (mirrorX) //if mirrorX is true
                    xSheet = 7 - x; //variable to flip the x-pixels, same as with the y-pixel

                int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) /2);//identify the pixels on the x-row of the tiles


                /*create a colour variable that will be used to check within the 8x8 tile what colours are being used
                as long as the colours are within the spectrum of 255*/
                int col = (colour >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;


                if (col < 255) //verify the colours aren't outside the spectrum and therefor transparent
                    for (int yScale = 0; yScale < scale; yScale++)
                    {
                        //if colour is higher or lower positioned than the tile height: ignore
                        if (yPixel + yScale < 0 || y + yPos >= height)
                            continue;
                        for (int xScale = 0; xScale < scale; xScale++) {
                            //if colour is higher or lower than the tiles width: ignore
                            if (xPixel + xScale < 0 || xPixel + xScale >= width)
                                continue;
                            pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col; //setting the pixel-data
                        }
                    }
                }
            }
        }
        //"camera positioning" set in parameters
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}

