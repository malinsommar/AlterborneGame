package davidtest.overworld.entities;

import davidtest.overworld.levels.Level;
import davidtest.overworld.gfx.Screen;

public abstract class Entity {
    public int x,y;
    protected Level level1;

    public Entity(Level level1) {
        init(level1);
    }
    public final void init(Level level1) {
        this.level1 = level1;
    }


    public abstract void tick();

    public abstract void render(Screen screen);
}
