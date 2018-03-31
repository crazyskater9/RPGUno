package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameKeyListener implements KeyListener {

    GameArea gameArea;
    Set<Character> keysPressed;

    public GameKeyListener(GameArea gameArea) {
        this.gameArea = gameArea;
        keysPressed = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyChar());

        if(keysPressed.size() <= 2) {
            for(char c: keysPressed) {
                switch(c) {
                    case 'w':
                        gameArea.player.movement.y = -10;
                        break;
                    case 'a':
                        gameArea.player.movement.x = -10;
                        break;
                    case 's':
                        gameArea.player.movement.y = 10;
                        break;
                    case 'd':
                        gameArea.player.movement.x = 10;
                        break;

                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        keysPressed.remove(e.getKeyChar());

        switch(e.getKeyChar()) {
            case 'w':
                gameArea.player.movement.y = 0;
                break;
            case 'a':
                gameArea.player.movement.x = 0;
                break;
            case 's':
                gameArea.player.movement.y = 0;
                break;
            case 'd':
                gameArea.player.movement.x = 0;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
