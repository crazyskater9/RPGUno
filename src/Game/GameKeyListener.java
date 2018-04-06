package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameKeyListener implements KeyListener {

    Set<Character> keysPressed;

    public GameKeyListener() {
        keysPressed = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
