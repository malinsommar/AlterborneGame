package game;

public class ConHub {

    private HubFrames hf = new HubFrames();

    //Need to use int array instead or regular int for ActionListeners to be able to change them.
    int[] choice = new int[1];

    private int exitCount = 1;
    private int newRunCount = 1;
    private int startCount = 1;


    public void test() {

        hf.hubFrame();

        while (choice[0] <= 0) {

                hf.newRunButton.addActionListener(e -> {
                    if (newRunCount == 1) {
                        newRunButtonClick();
                    }
                });

                hf.exitButton.addActionListener(e -> {
                    if (exitCount == 1) {
                        exitButtonClick();
                    }
                });

                if (choice[0] > 0) {
                break;
                 }

                hf.startButton.addActionListener(e -> {
                    if (startCount == 1) {
                        startGameClick();
                    }
                    });
        }
    }

    //Call this method when newRunButton is clicked in hubPanel.
    private void newRunButtonClick(){
        newRunCount++;
        hf.hubJFrame.add(hf.backstoryPanel);
        hf.hubPanel.setVisible(false);
        MusicPick.musicStart("theme", "music");
    }

    //Call this method when exitButton is clicked in hubPanel.
    private void exitButtonClick(){
        exitCount++;
        choice[0] = 2;
        hf.hubJFrame.dispose();
    }

    //Call this method when startGame is clicked in backstoryPanel.
    private void startGameClick(){
        startCount++;
        choice[0] = 1;
        hf.hubJFrame.dispose();
    }
}