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
    GameArea gameArea;
    GameKeyListener gameKeyListener;
    GameMouseListener gameMouseListener, statusMouseListener;
    long updateTime;                  // Time for 1 Frame-Update (16 = 60fps)
    Timer gameTimer;


    public Game() {
        gameKeyListener = new GameKeyListener();
        gameArea = new GameArea(gameKeyListener);
        initPanels();
        initFrame();
        initTimer();
    }

    void initPanels(){
        gamePanel = new GamePanel(new FlowLayout(FlowLayout.CENTER,0,0));
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setPreferredSize(new Dimension(GameData.WIDTH, GameData.HEIGHT));
        gamePanel.setLocalGameArea(gameArea);
        gameMouseListener = new GameMouseListener();
        gamePanel.addMouseListener(gameMouseListener);

        statusPanel = new StatusPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        statusPanel.setBackground(Color.GRAY);
        statusPanel.setPreferredSize(new Dimension(GameData.WIDTH, 20));
        statusMouseListener = new GameMouseListener();
        statusPanel.addMouseListener(statusMouseListener);
   }

   void initFrame(){
       frame = new JFrame("RPGUno");

       frame.add(statusPanel, BorderLayout.PAGE_START);
       frame.add(gamePanel, BorderLayout.CENTER);

       frame.addKeyListener(gameKeyListener);

       frame.setResizable(false);
       frame.pack();
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
   }

   void initTimer(){
        updateTime = 16;
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
            if(gameKeyListener.keysPressed.contains((char) 27)) System.exit(0);
    }

}
