package davidtest.overworld.map;

import game.FightModel;

import java.util.Arrays;

public class WorldModel {

    public WorldModel() throws InterruptedException {
        OverWorldController overWorldController = new OverWorldController();
        overWorldController.EnterWorld();
        if(overWorldController.Entrance[0]==1){
            FightModel fightModel = new FightModel();
            fightModel.startForestFight();
        }
    }
}
