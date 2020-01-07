package davidtest.overworld.map;

import fight.FightModel;
import game.ShopModel;

public class WorldModel {

     public WorldModel() throws InterruptedException {
        OverWorldController overWorldController = new OverWorldController();
        if(overWorldController.Entrance[0]==1){
            FightModel fightModel = new FightModel();
            fightModel.startForestFight();
        }
        if (overWorldController.Entrance[0]==2) {
            ShopModel shopModel = new ShopModel();
            shopModel.startShopController();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new WorldModel();
    }
}
