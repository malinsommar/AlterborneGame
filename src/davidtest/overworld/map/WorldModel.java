package davidtest.overworld.map;

import fight.FightModel;
import game.MasterModel;
import game.ShopModel;

public class WorldModel {
    WorldController overWorldController = new WorldController();

     public WorldModel() throws InterruptedException {
     }
     public int HandleOverWorld() throws InterruptedException{
         if (overWorldController.Entrance[0] == 1) {
             ShopModel shopModel = new ShopModel();
             shopModel.startShopController();
         }
         if (overWorldController.Entrance[0] == 2) {
             return 1;
         }
         if (overWorldController.Entrance[0] == 3) {
             MasterModel masterModel = new MasterModel();
             masterModel.startCaveFight();
         }
         if (overWorldController.Entrance[0] == 4) {
             MasterModel masterModel = new MasterModel();
             masterModel.startFieldFight();
         }
         if (overWorldController.Entrance[0] == 5) {
             MasterModel masterModel = new MasterModel();
             //fightModel.startSwampFight();
         }
         if (overWorldController.Entrance[0] == 6) {
             MasterModel masterModel = new MasterModel();
             //fightModel.startCastleFight();
         }
         return 0;
     }

    public static void main(String[] args) throws InterruptedException {
        new WorldModel();
    }
}
