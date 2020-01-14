package davidtest.overworld.map;

import game.UserNameController;

public class WorldModel {
    //call OverWorldController
        private WorldController overWorldController = new WorldController();

        //set userName in parameters from the MasterModel
        public WorldModel(String userName) throws InterruptedException {
            overWorldController.startWorldController(userName);
            HandleOverWorld();
        }
        //return int-values to MasterModel to identify which Controller should be called
        public int HandleOverWorld() {
            if (overWorldController.Entrance[0] == 1) {
                return 1; //call shop
            }
            if (overWorldController.Entrance[0] == 2) {
                return 2; //call forest
            }
            if (overWorldController.Entrance[0] == 3) {
                return 3; //call mountain
            }
            if (overWorldController.Entrance[0] == 4) {
                return 4; //call field
            }
            if (overWorldController.Entrance[0] == 5) {
                return 5; //call swamp
            }
            if (overWorldController.Entrance[0] == 6) {
                return 6; //call castle
            }
            if (overWorldController.Entrance[0] == 7) {
                return 7; //call water
            }
            if (overWorldController.Entrance[0] == 8) {
                return 9; //call cave
            }
            return 0;
        }
    }
