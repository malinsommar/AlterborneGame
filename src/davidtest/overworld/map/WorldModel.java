package davidtest.overworld.map;

import game.MasterModel;
import game.ShopModel;

public class WorldModel {

     public WorldModel() throws InterruptedException {
        WorldController overWorldController = new WorldController();

        if (overWorldController.Entrance[0]==1) {
            ShopModel shopModel = new ShopModel();
            shopModel.startShopController();
        }
         if(overWorldController.Entrance[0]==2){
             MasterModel masterModel = new MasterModel();
             masterModel.startForestFight();

         }
         if(overWorldController.Entrance[0]==3){
             MasterModel masterModel = new MasterModel();
             masterModel.startCaveFight();
         }
         if(overWorldController.Entrance[0]==4){
             MasterModel masterModel = new MasterModel();
             masterModel.startFieldFight();
         }
         if (overWorldController.Entrance[0]==5) {
             MasterModel masterModel = new MasterModel();
             //fightModel.startSwampFight();
         }
         if (overWorldController.Entrance[0]==6) {
             MasterModel masterModel = new MasterModel();
             //fightModel.startCastleFight();
         }
    }

    public static void main(String[] args) throws InterruptedException {
        new WorldModel();
    }
}
