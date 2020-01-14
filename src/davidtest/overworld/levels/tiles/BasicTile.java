        package davidtest.overworld.levels.tiles;

        import davidtest.overworld.levels.Level;
        import davidtest.overworld.gfx.Screen;
        //base-tile used as a blueprint for further tiles
        public class BasicTile extends Tile{
    int tileId;
    protected int tileColour;

    public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false,false, false, false, false, false, false, levelColour);
        this.tileId = x + y * 32; //tileID represents every 8x8-tile on the tileFrame and assign it within the parameters
        this.tileColour = tileColour; //assign this tileColour to the parameters
    }

    @Override
    //update value with every tick
    public void tick() {

    }

    @Override
    //render onto screen
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x,y,tileId,tileColour, 0X00, 1);
    }
}