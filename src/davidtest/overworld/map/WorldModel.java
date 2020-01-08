package davidtest.overworld.map;

import fight.FightModel;
import game.ShopModel;

public class WorldModel {

     public WorldModel() throws InterruptedException {
        WorldController overWorldController = new WorldController();

        if (overWorldController.Entrance[0]==1) {
            ShopModel shopModel = new ShopModel();
            shopModel.startShopController();
        }
         if(overWorldController.Entrance[0]==2){
             FightModel fightModel = new FightModel();
             fightModel.startForestFight();
         }
         if(overWorldController.Entrance[0]==3){
             FightModel fightModel = new FightModel();
             fightModel.startCaveFight();
         }
         if(overWorldController.Entrance[0]==4){
             FightModel fightModel = new FightModel();
             fightModel.startFieldFight();
         }
         if (overWorldController.Entrance[0]==5) {
             FightModel fightModel = new FightModel();
             //fightModel.startSwampFight();
         }
         if (overWorldController.Entrance[0]==6) {
             FightModel fightModel = new FightModel();
             //fightModel.startCastleFight();
         }
    }

    public static void main(String[] args) throws InterruptedException {
        new WorldModel();
    }
}
