package game;

public class HubController {

    private HubView hf = new HubView();

    boolean tutorialDone = false;
    boolean gameDone = false;
    boolean exitDone = false;

    public void startHubScreen(){
        hf.hubFrame();

        hf.newRunButton.addActionListener(e -> newRunButtonClick());
        hf.startButton.addActionListener(e -> startGameClick());
        hf.tutorialButton.addActionListener(e -> tutorialButtonClick());
        hf.exitButton.addActionListener(e -> exitButtonClick());
    }

    //Call this method when newRunButton is clicked in hubPanel.
    private void newRunButtonClick(){
        hf.hubJFrame.add(hf.BackStoryPanel);
        hf.hubPanel.setVisible(false);
        MusicPick.musicStart("theme", "music");
    }

    //Call this method when exitButton is clicked in hubPanel.
    private void tutorialButtonClick(){
        hf.hubJFrame.dispose();
        tutorialDone = true;
    }

    //Call this method when exitButton is clicked in hubPanel.
    private void exitButtonClick(){
        hf.hubJFrame.dispose();
        exitDone = true;
    }

    //Call this method when startGame is clicked in BackStoryPanel.
    private void startGameClick(){
        hf.hubJFrame.dispose();
        gameDone = true;
    }
}