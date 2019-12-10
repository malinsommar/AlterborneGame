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
    public static final Tile SWAMpWATER = new AnimatedTile(8, new int[][] { {0, 5}, { 1, 5}, { 2, 5 }, { 1, 5 } },
            Colours.get(-1, 15, 17, -1), 0xFF005c81, 800);
    public static final Tile SWAMP = new PathTile(9, 7, 0, Colours.get(-1, 18, 41, -1), 0xFF00a956);
    public static final Tile WOOD = new SolidTile(10, 8, 0, Colours.get(-1,220,221,-1), 0xFF8d6525);
    public static final Tile LEAF = new AnimatedTile(11, new int[][] {{ 1, 4}, { 2, 4}},
            Colours.get(-1, 16, 141, -1), 0xFF008d00, 800);




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