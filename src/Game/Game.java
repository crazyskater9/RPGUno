package Game;

/*
       Oberklasse
       Bringt alle Komponenten zusammen

 */

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    JFrame frame;
    GamePanel gamePanel;
    StatusPanel statusPanel;
    long updateTime = 100;                  // Time for 1 Frame-Update (16 = 60fps)
    Timer gameTimer;

    public Game() {
        initPanels();
        initFrame();
        initTimer();
    }

    void initPanels(){
        gamePanel = new GamePanel();
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setPreferredSize(new Dimension(800, 600));

        statusPanel = new StatusPanel();
        statusPanel.setBackground(Color.GRAY);
        statusPanel.setPreferredSize(new Dimension(800, 20));
   }

   void initFrame(){
       frame = new JFrame("RPGUno");

       frame.add(statusPanel, BorderLayout.PAGE_START);
       frame.add(gamePanel, BorderLayout.CENTER);

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
            System.out.println("Update");
            gamePanel.repaint();
            statusPanel.repaint();
    }


}
