package davidtest.overworld.map.Functionality;

import davidtest.overworld.map.WorldController;
import davidtest.overworld.map.WorldView;
import game.UserNameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//implement the KeyListener library to read keyboard inputs
public class InputHandler implements KeyListener {

    public InputHandler(WorldView game) {
        game.addKeyListener(this); //calls the InputHandler whenever a key is pressed
    }

    public static class Key {
        private int numTimesPressed = 0; //total times inputs pressed
        boolean pressed = false; //sets default value of "pressed" to false

        public int getNumTimesPressed() {
            return numTimesPressed;
        }
        //create a "isPressed" method that will be called as an object in game
        public boolean isPressed() {
            return pressed;
        }

        //create a method that sets the value of pressed to isPressed, which will be affected by KeyInputs
        public void toggle(boolean isPressed) {
            pressed = isPressed;
            if (isPressed) numTimesPressed++; //count the numbers of time a key has been "isPressed"
        }
    }

    //Register all the key-objects for the movement-options
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key enter = new Key();


    @Override
    public void keyPressed(KeyEvent e) {
        //change the keyPressed method to true when a key is pressed
        toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //change the keyPressed method to false when a key is not pressed
        toggleKey(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void toggleKey(int keyCode, boolean isPressed) {
        //assign the key-objects with keyboard-keys which sets the "isPressed" boolean to true if used
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            up.toggle((isPressed));
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            down.toggle((isPressed));
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left.toggle((isPressed));
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right.toggle((isPressed));
        }
        if (keyCode == KeyEvent.VK_ENTER) {
            enter.toggle((isPressed));
        }
    }
}

