package game;

public class ConHub {

    HubFrames hf = new HubFrames();

    //Need to use int array instead or regular int for ActionListeners to be able to change them.
    private int[] choice = new int[1];

    //Show HubFrame and return choice to MasterModel.
    public int startHub() {

        hf.hubFrame();

        while (choice[0] <= 0) {

            hf.startButton.addActionListener(e -> choice[0] = 1);
            hf.startButton.addActionListener(e -> hf.hubJFrame.dispose());

            if (choice[0]>0){
                break;
            }
            hf.exitButton.addActionListener(e -> choice[0] = 2);
            hf.exitButton.addActionListener(e -> hf.hubJFrame.dispose());
        }
            return choice[0];
    }
}