package pl.jwz;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class Window {

    private JFrame frame;
    private JFrame controlFrame;
    private String windowTitle = "Car game";
    private int windowWidth = 1500;
    private int windowHeight = 800;
    private boolean windowVisibility = true;

    public Window(Game game) {
        frame = new JFrame(windowTitle);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed to DISPOSE_ON_CLOSE
        frame.setVisible(windowVisibility);
        frame.add(game);
        game.requestFocus();


        controlFrame = new JFrame("Control Frame");
        controlFrame.setSize(300, 100);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                controlFrame.dispose();
                Start windowStart = new Start();
                windowStart.stopGame();
            }
        });

        controlFrame.add(stopButton);

        controlFrame.setLayout(new FlowLayout());

        controlFrame.setVisible(true);


    }

}
