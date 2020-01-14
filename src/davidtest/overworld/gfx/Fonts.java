package davidtest.overworld.gfx;

public class Fonts {

    public static void render (String msg, Screen screen, int x, int y, int colour, int scale) {
        msg = msg.toUpperCase();

        //if char is within the msg.length: render it
        for (int i = 0; i < msg.length(); i++) {
            String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "0123456789.,:;'\"!?$%()-=+/      ";
            int charIndex = chars.indexOf(msg.charAt(i));
            if (charIndex >= 0)
                screen.render(x + (i*8), y, charIndex + 30 * 32, colour, 0x00,  scale);
        }
    }
}
