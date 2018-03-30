package Game;

/*
       Oberklasse
       Bringt alle Komponenten zusammen

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements KeyListener {

    JFrame frame;
    GamePanel gamePanel;
    StatusPanel statusPanel;
    GameArea gameArea;
    long updateTime = 16;                  // Time for 1 Frame-Update (16 = 60fps)
    Timer gameTimer;

    public Game() {
        gameArea = new GameArea();
        initPanels();
        initFrame();
        initTimer();
    }

    void initPanels(){
        gamePanel = new GamePanel();
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setPreferredSize(new Dimension(800, 600));
        gamePanel.setLocalGameArea(gameArea);

        statusPanel = new StatusPanel();
        statusPanel.setBackground(Color.GRAY);
        statusPanel.setPreferredSize(new Dimension(800, 20));
   }

   void initFrame(){
       frame = new JFrame("RPGUno");

       frame.add(statusPanel, BorderLayout.PAGE_START);
       frame.add(gamePanel, BorderLayout.CENTER);

       frame.addKeyListener(this);

       frame.setResizable(false);
       frame.setSize(800, 620);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setVisible(true);
   }

   void initTimer(){
       gameTimer = new Timer();
       gameTimer.scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() {
               update();
           }
       }, 0, updateTime);
   }

    // Updates (Redraws) the game
    void update() {
            //System.out.println("Update");
            gamePanel.repaint();
            //System.out.println(gameArea.debug_flag);
            statusPanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()) {
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

    @Override
    public void keyReleased(KeyEvent e) {
        gameArea.player.movement.set(0,0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


}
