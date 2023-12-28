package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragPanel extends JPanel implements ActionListener {

    ImageIcon image = new ImageIcon("src/main/java/smile-icon.png");
    ImageIcon imageConfused = new ImageIcon("src/main/java/confused-color-icon.png");
    Image backgroundImage;
    Image rocket;
    Timer timer;
    int xVelocity = 3;
    int yVelocity = 1;
    int x = 0;
    int y = 0;
    final int WIDTH = 1135;
    final int HEIGTH = 1280;

    Point imageCorner;
    Point previousPoint;

    DragPanel() {

        imageCorner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
        //this.setBackground(Color.LIGHT_GRAY);
        backgroundImage = new ImageIcon("src/main/java/sky.jpg").getImage();
        rocket = new ImageIcon("src/main/java/img.png").getImage();
        timer = new Timer(8, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {

        Graphics2D graphics2D = (Graphics2D) graphics;
        super.paintComponent(graphics2D);
        graphics2D.drawImage(backgroundImage, 0, 0, null);
        graphics2D.drawImage(rocket, x, y, null);
        image.paintIcon(this, graphics2D, (int) imageCorner.getX(), (int) imageCorner.getY());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (x >= WIDTH - rocket.getWidth(null) || x < 0){
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;

        if (y >= HEIGTH - rocket.getHeight(null) || y < 0){
            yVelocity = yVelocity * -1;
        }
        y = y + yVelocity;

        repaint();
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