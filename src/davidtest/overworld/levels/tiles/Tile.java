package davidtest.overworld.levels.tiles;

import davidtest.overworld.levels.Level;
import davidtest.overworld.gfx.Colours;
import davidtest.overworld.gfx.Screen;

//Create all the variables that will be used in the levels
public abstract class Tile {
//176 LIGHT yellow, 141 light green, 16 dark green, 4 blue, 333 gray, 332 dark yellow, 58 dark purple, 424 pink, 265 bright purple
    public static final Tile[] tiles = new Tile[256];
    public static final Tile VOID = new SolidTile(0, 0, 0,/*position of the 8x8 tile on the sprite-sheet*/ Colours.get(000, -1, -1, -1) /**/, 0xFF000000 /*the colour assigned to the map-sprite*/);
    public static final Tile STONE = new SolidTile(1, 1, 0, Colours.get(-1, 333, -1, -1), 0xFF555555);
    public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1), 0xFF00FF00);
    public static final Tile WATER = new AnimatedTile(3, new int[][] { {0, 5}, { 1, 5}, { 2, 5 }, { 1, 5 } },
            Colours.get(-1, 4, 115, -1), 0xFF0000FF, 800);
    public static final Tile PATH = new PathTile(4,3,0, Colours.get(-1,555,176,-1), 0xFFb7ae44);
    public static final Tile FOREST = new PathTile(5,4,0, Colours.get(-1,16,1,2), 0xFF005000);
    public static final Tile MOUNTAIN = new PathTile(6,5,0, Colours.get(-1, 333, 11, -1), 0xFF3f3f3f);
    public static final Tile FIELD = new PathTile(7,6,0, Colours.get(-1, 176, 142, -1), 0xFFa3ff00);
    public static final Tile SWAMPWATER = new AnimatedTile(8, new int[][] { {0, 5}, { 1, 5}, { 2, 5 }, { 1, 5 } },
            Colours.get(-1, 12, 21, -1), 0xFF005c81, 800);
    public static final Tile SWAMP = new PathTile(9, 7, 0, Colours.get(-1, 18, 41, -1), 0xFF00a956);
    public static final Tile WOOD = new SolidTile(10, 8, 0, Colours.get(-1,220,221,-1), 0xFF8d6525);
    public static final Tile LEAF = new AnimatedSolidTile(11, new int[][] {{ 0, 3}, { 1, 3}},
            Colours.get(-1, 16, 141, -1), 0xFF008d00, 800);
    public static final Tile LEFTBOTTOMHOUSE = new SolidTile(12, 0, 4, Colours.get(-1,210,211, 1), 0xFF8d3026);
    public static final Tile RIGHTBOTTOMHOUSE = new SolidTile(13, 1, 4, Colours.get(-1,210,211, 1), 0xFF8d3027);
    public static final Tile LEFTOPHOUSE = new SolidTile(14, 2, 4, Colours.get(-1,22,21,-1), 0xFF8d3028);
    public static final Tile RIGHTTOPHOUSE = new SolidTile(15, 3, 4, Colours.get(-1,22,21,-1), 0xFF8d3029);
    public static final Tile CRACKS = new BasicTile(16, 0, 7, Colours.get(-1, 200, 1, -1), 0xFF565554);
    public static final Tile LEFTBOTTOMCAVE = new SolidTile(17, 0, 9, Colours.get(-1,1,333, 16), 0xFF2d3c2b);
    public static final Tile RIGHTBOTTOMCAVE = new SolidTile(18, 1, 9, Colours.get(-1,1,333, 16), 0xFF2d3a2b);
    public static final Tile LEFTOPCAVE = new SolidTile(19, 2, 9, Colours.get(-1,1,333,16), 0xFF2d3c2b);
    public static final Tile RIGHTTOPCAVE = new SolidTile(20, 3, 9, Colours.get(-1,1,333,16), 0xFF2d3f2b);
    public static final Tile LEFTTOPCASTLE = new SolidTile(21, 0, 11, Colours.get(-1,1,333, 16), 0xFF785554);
    public static final Tile RIGHTTOPCASTLE = new SolidTile(22, 1, 11, Colours.get(-1,1,333,-1), 0xFF785555);
    public static final Tile LEFTCASTLE = new SolidTile(23, 3, 11, Colours.get(-1,1,333,-1), 0xFF785556);
    public static final Tile RIGHTCASTLE = new SolidTile(24, 4, 11, Colours.get(-1,1,333,-1), 0xFF785557);
    public static final Tile MIDDLECASTLE = new SolidTile(25, 2, 11, Colours.get(-1,1,333,-1), 0xFF785558);
    public static final Tile FIRE = new AnimatedSolidTile(26, new int[][] {{ 0, 13}, { 1, 13},{2,13}},
            Colours.get(220, 200, 1, 332), 0xFFed8100, 800);
    public static final Tile SHEEPBODY = new SolidTile(27, 0, 15, Colours.get(-1,334,333,-1), 0xFF7a8b51);
    public static final Tile SHEEPHEAD = new AnimatedSolidTile(28, new int[][] {{ 1, 15}, { 2, 15},},
            Colours.get(176, 334, 333, 1), 0xFF7a8b53, 2000);
    public static final Tile SHEEPLEGS = new SolidTile(29, 3, 15, Colours.get(176,3,1,2), 0xFF7a8b52);
    public static final Tile SHEEPHEADLEFT = new AnimatedSolidTile(30, new int[][] {{ 4, 15}, { 5, 15},},
            Colours.get(176, 334, 333, 1), 0xFF7a8b54, 1900);
    public static final Tile SHEEPBODYLEFT = new SolidTile(31, 6, 15, Colours.get(-1,334,333,-1), 0xFF7a8b55);
    public static final Tile FORESTCHEST = new SolidTile(32, 0, 17, Colours.get(16,210,178,332), 0xFFab6a1c);
    public static final Tile FIELDCHEST = new SolidTile(33, 1, 17, Colours.get(176,210,178,332), 0xFFab6a2c);
    public static final Tile MOUNTAINCHEST = new SolidTile(34, 2, 17, Colours.get(333,210,178,332), 0xFFab6a3c);
    public static final Tile SWAMPCHEST = new SolidTile(35, 3, 17, Colours.get(18,210,178,332), 0xFFab6a4c);


    protected byte id;
    protected boolean solid;
    protected boolean emitter;
    protected boolean path;
    private int levelColour;

    public Tile (int id, boolean isSolid, boolean isEmitter, boolean isPath, int levelColour) {
        this.id = (byte) id;
        if (tiles[id] != null)
            throw new RuntimeException("Duplicate tile id on" + id);
        this.solid = isSolid;
        this.emitter = isEmitter;
        this.path = isPath;
        this.levelColour  = levelColour;
        tiles[id] = this;
    }
    public byte getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isEmitter() {
        return emitter;
    }

    public int getLevelColour() {
        return levelColour;
    }

    public boolean isPath() {
        return path;
    }

    public abstract void tick();

    public abstract void render(Screen screen, Level level, int x, int y);
}