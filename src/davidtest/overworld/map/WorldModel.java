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
        //return int-values to MasterModel to identify which Controller should be used
        public int HandleOverWorld() {
            if (overWorldController.Entrance[0] == 1) {
                return 1;
            }
            if (overWorldController.Entrance[0] == 2) {
                return 2;
            }
            if (overWorldController.Entrance[0] == 3) {
                return 3;
            }
            if (overWorldController.Entrance[0] == 4) {
                return 4;
            }
            if (overWorldController.Entrance[0] == 5) {
                return 5;
            }
            if (overWorldController.Entrance[0] == 6) {
                return 6;
            }
            if (overWorldController.Entrance[0] == 7) {
                return 7;
            }
            return 0;
        }

    public static void main(String[] args) throws InterruptedException {
        new WorldModel("he");
    }
    }
