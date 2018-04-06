package Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        //debugMouseListener("Mouse clicked (# of clicks: "+ e.getClickCount() + "), X: " + e.getX() + ", Y: " + e.getY(), e);

    }

    @Override
    public void mousePressed(MouseEvent e) {
            //debugMouseListener("Mouse pressed # of clicks: "+ e.getClickCount(), e);
            }

    @Override
    public void mouseReleased(MouseEvent e) {
            debugMouseListener("Mouse released # of clicks: "+ e.getClickCount() + ", X: " + e.getX() + ", Y: " + e.getY() + ", Button: " + e.getButton() + ", Source: " + e.getComponent(), e);
            GameData.mouseX = e.getX();
            GameData.mouseY = e.getY();
            GameData.clickedMouseButton = e.getButton();
            GameData.activePanel = (JPanel)e.getSource();
            }

    @Override
    public void mouseEntered(MouseEvent e) {
            //debugMouseListener("Mouse entered", e);
            }

    @Override
    public void mouseExited(MouseEvent e) {
            //debugMouseListener("Mouse exited", e);
            }

    void debugMouseListener(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                + e.getComponent().getClass().getName());
    }
}