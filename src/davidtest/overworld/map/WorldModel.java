package davidtest.overworld.map;

import game.UserNameController;

public class WorldModel {
        private WorldController overWorldController = new WorldController();

        public WorldModel(String userName) throws InterruptedException {
            overWorldController.startWorldController(userName);
            HandleOverWorld();
        }
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
    }
