package davidtest.overworld.levels.tiles;

public class MountainPathTile extends BasicTile {
    public MountainPathTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.mountainPath = true;
    }
}