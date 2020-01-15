package davidtest.overworld.levels.tiles;


/**
 * Tile used to call the CastleController
 */
public class CastlePathTile extends BasicTile {
    public CastlePathTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.castlePath = true;
    }
}
