package pl.jwz;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Window {

    private JFrame frame;
    private String windowTitle = "Car game";
    private int windowWidth = 1500;
    private int windowHeight = 800;
    private boolean windowVisibility = true;

    public Window(Game game) {
        frame = new JFrame(windowTitle);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(windowVisibility);
        frame.add(game);
        game.requestFocus();
    }
}
