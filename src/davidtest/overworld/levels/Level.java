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

//Build the map around everything in the Tile-class
public class Level {

    private byte[] tiles; //an ArrayList for where the tiles are
    public int width;
    public int height;
    public List<Entity> entities = new ArrayList<Entity>();
    private String imagePath;
    private BufferedImage image;

    //Assign all the variables in the constructor
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

    private void loadLevelFromFile() {
        try {
            this.image = ImageIO.read(Level.class.getResource(this.imagePath));
            this.width = image.getWidth();
            this.height = image.getHeight();
            tiles = new byte[width* height];
            this.loadTiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //loads the tiles from the image
    private void loadTiles() {
        //translate the image data into an int-array
        int[] tileColours = this.image.getRGB(0,0,width,height,null,0,width);
        //loops through both the x and y-tiles until they reach the images max-value of 64
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //loop through all the possible tiles located in the "tiles"-variable
                tileCheck: for (Tile t : Tile.tiles) {
                    if(t != null && t.getLevelColour() == tileColours[x + y * width]) {
                        this.tiles[x + y * width] = t.getId();
                        break tileCheck;
                    }
                }
            }
        }
    }

    private void saveLevelToFile() {
        try {
            ImageIO.write(image, "png", new File(java.util.logging.Level.class.getResource(this.imagePath).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void alterTile(int x, int y, Tile newTile) {
        this.tiles[x+y*width] = newTile.getId();
        image.setRGB(x,y,newTile.getLevelColour());
    }

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

    //render the tiles on the screen.
    public void renderTiles(Screen screen, int xOffset, int yOffset) {
        if (xOffset < 0) xOffset = 0;
        if (xOffset > ((width << 3) - screen.width)) xOffset = ((width << 3) - screen.width);
        if (yOffset < 0) yOffset = 0;
        if (yOffset > ((height << 3) - screen.height)) yOffset = ((height << 3) - screen.height);

        screen.setOffset(xOffset, yOffset);
/*a loop that goes through all the tiles render on the screen. the base-value of y and x needs to be 0, otherwise
 parts of the screen will be blacked out. as Long as they are less than height/width(or 64) the value will be add up*/
        for(int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
            for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
                getTile(x,y).render(screen,this,x << 3, y << 3);
            }
        }
    }

    //render the entities/mobs above the tiles so they'll stay visible
    public void renderEntities(Screen screen) {
        for (Entity e : entities) {
            e.render(screen);
        }
    }

    public Tile getTile (int x, int y) {
        //if no tile has been added call the Void-tile natively
        if (0 > x || x >= width || 0 > y || y >= height)
            return  Tile.VOID;
        //otherwise used to call wanted tiles
        return Tile.tiles[tiles[x + y * height]];
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity); //call the Entity-class into the Level-class by adding the Player-object
    }
}