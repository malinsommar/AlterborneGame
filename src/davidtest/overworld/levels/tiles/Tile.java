package davidtest.overworld.levels.tiles;

import davidtest.overworld.levels.Level;
import davidtest.overworld.gfx.Colours;
import davidtest.overworld.gfx.Screen;

//Create all the Tiles that will be used in the levels
public abstract class Tile {
    //Create all the tiles
    public static final Tile[] tiles = new Tile[256];
    public static final Tile VOID = new SolidTile /*What type if tile it is*/(0, 0, 0,/*position of the 8x8 tile on the sprite-sheet*/ Colours.get(000, -1, -1, -1) /*colours used in the frame*/, 0xFF000000 /*the colour that the tile is identified to on the map*/);
    public static final Tile STONE = new SolidTile(1, 1, 0, Colours.get(-1, 333, -1, -1), 0xFF555555);
    public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1), 0xFF00FF00);
    public static final Tile WATER = new AnimatedTile(3, new int[][] { {0, 5}, { 1, 5}, { 2, 5 }, { 1, 5 } },
            Colours.get(-1, 4, 115, -1), 0xFF0000FF, 1200);
    public static final Tile PATH = new BasicTile(4,3,0, Colours.get(-1,555,176,-1), 0xFFb7ae44);
    public static final Tile FOREST = new BasicTile(5,4,0, Colours.get(-1,16,1,2), 0xFF005000);
    public static final Tile MOUNTAIN = new BasicTile(6,5,0, Colours.get(-1, 333, 11, -1), 0xFF3f3f3f);
    public static final Tile FIELD = new BasicTile(7,6,0, Colours.get(-1, 176, 142, -1), 0xFFa3ff00);
    public static final Tile SWAMPWATER = new AnimatedTile(8, new int[][] { {0, 5}, { 1, 5}, { 2, 5 }, { 1, 5 } },
            Colours.get(-1, 12, 21, -1), 0xFF005c81, 1800);
    public static final Tile SWAMP = new BasicTile(9, 7, 0, Colours.get(-1, 18, 41, -1), 0xFF00a956);
    public static final Tile WOOD = new SolidTile(10, 8, 0, Colours.get(-1,220,221,-1), 0xFF8d6525);
    public static final Tile LEAF = new AnimatedSolidTile(11, new int[][] {{ 0, 2}, { 1, 2}},
            Colours.get(-1, 16, 141, -1), 0xFF008d00, 1000);
    public static final Tile LEFTBOTTOMHOUSE = new DoorTile(12, 0, 4, Colours.get(1,210,131, 141), 0xFF8d3026);
    public static final Tile RIGHTBOTTOMHOUSE = new DoorTile(13, 1, 4, Colours.get(1,210,131, 141), 0xFF8d3027);
    public static final Tile LEFTOPHOUSE = new DoorTile(14, 2, 4, Colours.get(1,22,131,141), 0xFF8d3028);
    public static final Tile RIGHTTOPHOUSE = new DoorTile(15, 3, 4, Colours.get(-1,22,131,141), 0xFF8d3029);
    public static final Tile CRACKS = new BasicTile(16, 0, 7, Colours.get(-1, 200, 1, -1), 0xFF565554);
    public static final Tile LEFTBOTTOMFORESTCAVE = new ForestPathTile(17, 0, 9, Colours.get(1,2,333, 16), 0xFF3db114);
    public static final Tile RIGHTBOTTOMFORESTCAVE = new ForestPathTile(18, 1, 9, Colours.get(1,2,333, 16), 0xFF3db115);
    public static final Tile LEFTOPFORESTCAVE = new ForestPathTile(19, 2, 9, Colours.get(1,2,333,16), 0xFF3db116);
    public static final Tile RIGHTTOPFORESTCAVE = new ForestPathTile(20, 3, 9, Colours.get(1,2,333,16), 0xFF3db117);
    public static final Tile LEFTTOPCASTLE = new SolidTile(21, 0, 11, Colours.get(-1,1,333, 16), 0xFF785554);
    public static final Tile RIGHTTOPCASTLE = new SolidTile(22, 1, 11, Colours.get(-1,1,333,-1), 0xFF785555);
    public static final Tile LEFTCASTLE = new SolidTile(23, 3, 11, Colours.get(-1,1,333,-1), 0xFF785556);
    public static final Tile RIGHTCASTLE = new SolidTile(24, 4, 11, Colours.get(-1,1,333,-1), 0xFF785557);
    public static final Tile MIDDLECASTLE = new CastlePathTile(25, 2, 11, Colours.get(-1,1,333,-1), 0xFF785558);
    public static final Tile FIRE = new AnimatedSolidTile(26, new int[][] {{ 0, 13}, { 1, 13},{2,13}},
            Colours.get(220, 200, 1, 332), 0xFFed8100, 500);
    public static final Tile SHEEPBODY = new SolidTile(27, 0, 15, Colours.get(176,334,333,1), 0xFF7a8b51);
    public static final Tile SHEEPHEAD = new AnimatedSolidTile(28, new int[][] {{ 1, 15}, { 2, 15},},
            Colours.get(176, 334, 333, 1), 0xFF7a8b53, 2000);
    public static final Tile SHEEPLEGS = new SolidTile(29, 3, 15, Colours.get(176,3,1,2), 0xFF7a8b52);
    public static final Tile SHEEPHEADLEFT = new AnimatedSolidTile(30, new int[][] {{ 4, 15}, { 5, 15},},
            Colours.get(176, 334, 333, 1), 0xFF7a8b54, 1900);
    public static final Tile SHEEPBODYLEFT = new SolidTile(31, 6, 15, Colours.get(-1,334,333,-1), 0xFF7a8b55);
    public static Tile FORESTCHEST = new SolidTile(32, 0, 17, Colours.get(16,210,178,332), 0xFFab6a1c);
    public static final Tile FIELDCHEST = new SolidTile(33, 1, 17, Colours.get(176,210,178,332), 0xFFab6a2c);
    public static final Tile MOUNTAINCHEST = new SolidTile(34, 2, 17, Colours.get(333,210,178,332), 0xFFab6a3c);
    public static final Tile SWAMPCHEST = new SolidTile(35, 3, 17, Colours.get(18,210,178,332), 0xFFab6a4c);
    public static final Tile LEFTBOTTOMMOUNTAINCAVE = new MountainPathTile(36, 0, 21, Colours.get(1,2,333, -1), 0xFF672321);
    public static final Tile RIGHTBOTTOMMOUNTAINCAVE = new MountainPathTile(37, 1, 21, Colours.get(1,2,333, -1), 0xFF672322);
    public static final Tile LEFTOPMOUNTAINCAVE = new MountainPathTile(38, 2, 21, Colours.get(1,2,333,-1), 0xFF672323);
    public static final Tile RIGHTTOPMOUNTAINCAVE = new MountainPathTile(39, 3, 21, Colours.get(1,2,333,-1), 0xFF672324);
    public static final Tile LEFTBOTTOMFIELDCAVE = new FieldPathTile(40, 0, 23, Colours.get(1,2,210, 176), 0xFF672325);
    public static final Tile RIGHTBOTTOMMFIELDCAVE = new FieldPathTile(41, 1, 23, Colours.get(1,2,211, 176), 0xFF672326);
    public static final Tile LEFTOPFIELDCAVE = new FieldPathTile(42, 2, 23, Colours.get(1,2,212,176), 0xFF672327);
    public static final Tile RIGHTTOPFIELDCAVE = new FieldPathTile(43, 3, 23, Colours.get(1,2,213,176), 0xFF672328);
    public static final Tile LEFTBOTTOMSWAMPCAVE = new SwampPathTile(44, 0, 25, Colours.get(1,2,333, 18), 0xFF672329);
    public static final Tile RIGHTBOTTOMSWAMPCAVE = new SwampPathTile(45, 1, 25, Colours.get(1,2,333, 18), 0xFF672330);
    public static final Tile LEFTOPSWAMPCAVE = new SwampPathTile(46, 2, 25, Colours.get(1,2,333,18), 0xFF672331);
    public static final Tile RIGHTTOPSWAMPCAVE = new SwampPathTile(47, 3, 25, Colours.get(1,2,333,18), 0xFF672332);
    public static final Tile SWAMPWOOD = new SolidTile(48, 8, 0, Colours.get(-1,222,228,-1), 0xFF785211);
    public static final Tile SWAMPLEAF = new AnimatedSolidTile(49, new int[][] {{ 0, 2}, { 1, 2}},
            Colours.get(-1, 11, 18, -1), 0xFF005200, 2000);
    public static final Tile FENCE = new SolidTile(50, 1, 19, Colours.get(176, -1, 201, -1), 0xFFa56c68);
    public static final Tile FENCESIDE = new SolidTile(51, 2, 19, Colours.get(176, -1, 201, -1), 0xFFa56c69);


    //create the boolean values that will be assigned to to tiles depending on purpose
    protected byte id;
    boolean solid;
    boolean forestPath;
    boolean mountainPath;
    boolean fieldPath;
    boolean swampPath;
    boolean castlePath;
    boolean door;
    private int levelColour;

    //create the parameters for all tiles
    Tile(int id, boolean isSolid, boolean isDoor, boolean isForestPath, boolean isMountainPath, boolean isFieldPath, boolean isSwampPath, boolean isCastlePath, int levelColour) {
        this.id = (byte) id;
        //if a tile-id has been used before: throw RuntimeException
        if (tiles[id] != null)
            throw new RuntimeException("Duplicate tile id on" + id);

        //Indicate that all the variables within the parameter are the same as these variables
        this.solid = isSolid;
        this.forestPath = isForestPath;
        this.mountainPath = isMountainPath;
        this.fieldPath = isFieldPath;
        this.swampPath = isSwampPath;
        this.castlePath = isCastlePath;
        this.door = isDoor;
        this.levelColour  = levelColour;
        tiles[id] = this;
    }

    //return theses booleans as true if the tile has been identified with it
    public byte getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public int getLevelColour() {
        return levelColour;
    }

    public boolean isForestPath() {
        return forestPath;
    }
    public boolean isMountainPath() {
        return mountainPath;
    }
    public boolean isFieldPath() {
        return fieldPath;
    }
    public boolean isSwampPath() {
        return swampPath;
    }
    public boolean isCastlePath() { return castlePath;
    }
    public boolean isDoor() {
        return door;
    }

        //update the values with every tick
    public abstract void tick();

    //render the the tiles onto the level
    public abstract void render(Screen screen, Level level, int x, int y);
}