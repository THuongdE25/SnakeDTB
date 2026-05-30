package SnakeGame;

import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame  {
    WindowPanel wp=new WindowPanel();
    public Window(){
        this.add(wp);
        this.pack();
        this.setTitle("Snake Game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                wp.Save();
                wp.CloseDatabase();
                System.exit(0);
            }
        });
    }
}
