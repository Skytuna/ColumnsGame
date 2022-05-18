import enigma.console.TextAttributes;
import enigma.core.Enigma;
import enigma.event.TextMouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class EnigmaExtended {
    public enigma.console.Console console;
    public TextMouseListener tmlis;
    public KeyListener klis;

    // ------ Standard variables for mouse and keyboard ------
    public int mousepr; // mouse pressed?
    public int mousex, mousey; // mouse text coords.
    public int keypr; // key pressed?
    public int rkey; // key (for press/release)
    // ----------------------------------------------------

    public EnigmaExtended() {
        console = Enigma.getConsole("Columns Game", 80, 25, 25);

        klis = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (keypr == 0) {
                    keypr = 1;
                    rkey = e.getKeyCode();
                }
            }

            public void keyReleased(KeyEvent e) {
                if (keypr == 1) {
                    keypr = 0;
                }
            }
        };
        console.getTextWindow().addKeyListener(klis);
    }

    public void clearConsole() {
        console.getTextWindow().setCursorPosition(0, 0);
        for (int i = 0; i < 100; i++) {
            System.out.println(
                    "                                                                                                    ");
        }
        console.getTextWindow().setCursorPosition(0, 0);
    }

    public void setCursor(int x, int y) {
        console.getTextWindow().setCursorPosition(x, y);
    }

    public void print(String text) {
        console.getTextWindow().output(text);
    }

    public void print(int x, int y, char text) {
        console.getTextWindow().output(x, y, text);
    }

    public void print(String text, TextAttributes color) {
        console.getTextWindow().output(text, color);
    }

    public int isKeyPressed() {
        if (keypr == 1)
            return rkey;

        return -1;
    }
}