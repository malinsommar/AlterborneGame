package davidtest.overworld.levels.tiles;

public class FieldPathTile extends BasicTile {
    public FieldPathTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.fieldPath = true;
    }
}
