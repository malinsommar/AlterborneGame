package davidtest.overworld.map;

import davidtest.overworld.map.WorldController;

    public class WorldModel {
        private WorldController overWorldController = new WorldController();

        public WorldModel() throws InterruptedException {
            overWorldController.startWorldController();
            HandleOverWorld();
        }
        public int HandleOverWorld() throws InterruptedException{
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
            return 0;
        }

    }
