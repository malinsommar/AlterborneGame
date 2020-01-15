package davidtest.overworld.levels.tiles;

//tile to enter MountainController
public class CavePathTile extends BasicTile {
    public CavePathTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.cavePath = true;
    }
}