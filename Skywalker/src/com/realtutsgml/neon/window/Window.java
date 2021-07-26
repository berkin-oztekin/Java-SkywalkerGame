package com.realtutsgml.neon.window;
import java.awt.Dimension;//We used for determine hight and weight
import javax.swing.JFrame;//We used for creat game window


public class Window {    //The class which will open  the game window

    public Window ( int w  ,int h , String title , Game game ){
        game.setPreferredSize(new Dimension(w, h));
        game.setMaximumSize(new Dimension(w, h));
        game.setMaximumSize(new Dimension(w, h));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setResizable(false);                   // for use the settings of game panel
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

}
