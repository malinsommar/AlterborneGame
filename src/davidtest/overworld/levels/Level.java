package davidtest.overworld.levels;

import davidtest.overworld.entities.Entity;
import davidtest.overworld.gfx.Screen;
import davidtest.overworld.levels.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Build the map around everything in the Tile-class
 */
public class Level {

    private byte[] tiles; //an ArrayList for where the tiles are
    public int width;
    public int height;
    public List<Entity> entities = new ArrayList<Entity>();
    private String imagePath;
    private BufferedImage image;


    /**
     *     Assign all the variables in the constructor
     * @param imagePath saved value of the map
     */
    public Level(String imagePath) {
        if (imagePath != null) {
            this.imagePath = imagePath;
            this.loadLevelFromFile();
        } else {
            this.width = 64;
            this.height = 64;
            tiles = new byte[width * height];
        }
    }


    /**
     *  load the map onto the screen
     */
    private void loadLevelFromFile() {
        try {
            this.image = ImageIO.read(Level.class.getResource(this.imagePath)); //read the map using an ImageIO
            this.width = image.getWidth(); //get width
            this.height = image.getHeight(); //get height
            tiles = new byte[width* height]; //multiply both to get the tiles
            this.loadTiles(); //Load tiles onto the map
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * loads the tiles from the image
     */
    private void loadTiles() {
        //translate the image data into an int-array
        int[] tileColours = this.image.getRGB(0,0,width,height,null,0,width);
        //loops through both the x and y-tiles until they reach the images max-value of 64
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //loop through all the possible tiles located in the "tiles"-variable
                for (Tile t : Tile.tiles) {
                    if (t != null && t.getLevelColour() == tileColours[x + y * width])
                    {
                        this.tiles[x + y * width] = t.getId();
                        break;
                    }
                }
            }
        }
    }


    /**
     * Update the level based on tick-count
     */
    public void tick() {
        for (Entity e : entities) {
            e.tick();
        }
        for (Tile t : Tile.tiles) {
            if (t == null) {
                break;
            }
            t.tick();
        }
    }


    /**
     * Render the tiles on the screen.
     * @param screen to be used
     * @param xOffset position on the screen
     * @param yOffset position on the screen
     */
    public void renderTiles(Screen screen, int xOffset, int yOffset) {
        if (xOffset < 0) xOffset = 0;
        if (xOffset > ((width << 3) - screen.width)) xOffset = ((width << 3) - screen.width);
        if (yOffset < 0) yOffset = 0;
        if (yOffset > ((height << 3) - screen.height)) yOffset = ((height << 3) - screen.height);

        screen.setOffset(xOffset, yOffset);

//a loop that goes through all the tiles rendering on the screen. as Long as they are less than height/width(or 64) the value will be add up
        for(int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
            for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
                getTile(x,y).render(screen,this,x << 3, y << 3);
            }
        }
    }


    /**
     * render the entities/mobs above the tiles so they'll stay visible
     * @param screen to be used
     */
    public void renderEntities(Screen screen) {
        for (Entity e : entities) {
            e.render(screen);
        }
    }

    /**
     * method to get the value of tile for further use
     * @param x position of tile
     * @param y position of tile
     * @return the value x and y
     */
    public Tile getTile (int x, int y) {
        //if no tile has been added call the Void-tile natively
        if (0 > x || x >= width || 0 > y || y >= height)
            return  Tile.VOID;
        //otherwise used to call wanted tiles
        return Tile.tiles[tiles[x + y * height]];
    }

    /**
     * Call the Entity-class into the Level-class by adding the Player-object
     * @param entity added to be used in getEntity
     */
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    /**
     * get Entity to use when need
     * @return the value of entity
     */
    public Entity getEntity() {
        return (Entity) entities;
    }
}