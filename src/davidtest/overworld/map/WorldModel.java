package davidtest.overworld.map;

import game.MasterModel;
import game.ShopModel;

public class WorldModel {
    private WorldController overWorldController = new WorldController();

     public WorldModel() throws InterruptedException {
         HandleOverWorld();
     }
     public int HandleOverWorld() throws InterruptedException{
         if (overWorldController.Entrance[0] == 1) {
             ShopModel shopModel = new ShopModel();
             shopModel.startShopController(1);
         }
         if (overWorldController.Entrance[0] == 2) {
             return 1;
         }
         if (overWorldController.Entrance[0] == 3) {
             return 2;
         }
         if (overWorldController.Entrance[0] == 4) {
             return 3;
         }
         if (overWorldController.Entrance[0] == 5) {
             return 4;
         }
         if (overWorldController.Entrance[0] == 6) {
             return 5;
         }
         return 0;
     }

    public static void main(String[] args) throws InterruptedException {
        new WorldModel();
    }
}
