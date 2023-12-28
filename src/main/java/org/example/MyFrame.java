package org.example;

import javax.swing.*;

public class MyFrame extends JFrame {

    DragPanel dragPanel = new DragPanel();

    MyFrame() {

        this.add(dragPanel);
        this.setTitle("Drag and drop");
        this.setSize(2600, 2600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
