package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragPanel extends JPanel {

    ImageIcon image = new ImageIcon("src/main/java/smile-icon.png");
    ImageIcon imageConfused = new ImageIcon("src/main/java/confused-color-icon.png");

    Point imageCorner;
    Point previousPoint;

    DragPanel() {

        imageCorner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        image.paintIcon(this, graphics, (int) imageCorner.getX(), (int) imageCorner.getY());
    }

    private class ClickListener extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            // Change the icon to imageConfused when pressed
            image = imageConfused;
            previousPoint = event.getPoint();
            repaint();
        }

        public void mouseReleased(MouseEvent event) {
            // Revert back to the original image when released
            image = new ImageIcon("src/main/java/smile-icon.png");
            repaint();
        }
    }

    private class DragListener extends MouseMotionAdapter {

        public void mouseDragged(MouseEvent event) {
            Point currentPoint = event.getPoint();

            imageCorner.translate(

                    (int) (currentPoint.getX() - previousPoint.getX()),
                    (int) (currentPoint.getY() - previousPoint.getY())
            );
            previousPoint = currentPoint;
            repaint();
        }
    }
}