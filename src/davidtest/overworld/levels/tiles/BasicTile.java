        package davidtest.overworld.levels.tiles;

        import davidtest.overworld.levels.Level;
        import davidtest.overworld.gfx.Screen;
        /**
         *  base-tile used for functionless tiles, and also as blueprint for all other tiles
         */
        public class BasicTile extends Tile{
    int tileId;
    protected int tileColour;

            /**
             * Constructor for all the necessary variables
             * @param id of tile
             * @param x position on the SpriteSheet
             * @param y position on the SpriteSheet
             * @param tileColour within game
             * @param levelColour name of colour on map
             */
    public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false,false, false, false, false, false, false, levelColour);
        this.tileId = x + y * 32; //tileID represents every 8x8-tile on the tileFrame and assign it within the parameters
        this.tileColour = tileColour; //assign this tileColour to the parameters
    }

    /**
     * update value with every tick
     */
    @Override

    public void tick() {

    }

    /**
     *  render onto screen
     */
    @Override
    public void render(Screen screen, Level level, int x, int y) {
        screen.render(x,y,tileId,tileColour, 0X00, 1);
    }
}