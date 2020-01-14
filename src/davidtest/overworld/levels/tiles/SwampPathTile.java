package davidtest.overworld.levels.tiles;

//tile used to call SwampController
public class SwampPathTile extends BasicTile {
    public SwampPathTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.swampPath = true;
    }
}
