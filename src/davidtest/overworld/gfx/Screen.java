package davidtest.overworld.gfx;


//The
public class Screen {
    public static final int MAP_WIDTH = 64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

    public static final byte BIT_MIRROR_X = 0x01;
    public static final byte BIT_MIRROR_Y = 0x02;

    public int[] pixels;
    //public int[] tiles = new int[MAP_WIDTH*MAP_WIDTH]; //designate tiles
    //public int[] colours = new int[MAP_WIDTH*MAP_WIDTH * 4]; //Holds information about the bits of data about which color is where

    //variables to give a "cameraPoint"
    public int xOffset = 0;
    public int yOffset = 0;

    public int width; //screen width
    public int height; //screen height

    public SpriteSheet sheet; //move the sheet to make it local with the screen. That way there can be multiple screens

    //setting the variables in a constructor
    public Screen(int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        this.sheet = sheet;

        pixels = new int[width * height];
    }
    //render the tiles and colours depending on
    public void render(int xPos, int yPos, int tile, int colour, int mirrorDir, int scale) {
        //control the position of the SpriteSheet within the frame
        xPos -= xOffset;
        yPos -= yOffset;


        boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
        boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;


        int scaleMap = scale - 1;
        int xTile = tile % 32;
        int yTile = tile / 32;
        int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;
        for (int y = 0; y < 8; y++) {
            int ySheet = y;
            if (mirrorY)
                ySheet = 7 - y;

            int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3) / 2);

            for (int x = 0; x < 8; x++) {
                int xSheet = x;
                if (mirrorX)
                    xSheet = 7 - x;
                int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) /2);
                int col = (colour >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;
                if (col < 255) {
                    for (int yScale = 0; yScale < scale; yScale++) {
                        if (yPixel + yScale < 0 || y + yPos >= height)
                            continue;
                        for (int xScale = 0; xScale < scale; xScale++) {
                            if (xPixel + xScale < 0 || xPixel + xScale >= width)
                                continue;
                            pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col;
                        }
                    }
                }
            }
        }
    }
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}

