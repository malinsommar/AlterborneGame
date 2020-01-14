        package davidtest.overworld.levels.tiles;

        import davidtest.overworld.levels.Level;
        import davidtest.overworld.gfx.Screen;

        public class BasicTile extends Tile{
    int tileId;
    protected int tileColour;

    public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false,false, false, false, false, false, false, levelColour);
        this.tileId = x + y * 32;
        this.tileColour = tileColour;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x,y,tileId,tileColour, 0X00, 1);
    }
}