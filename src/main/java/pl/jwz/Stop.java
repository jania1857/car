package pl.jwz;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;




@Getter
@Setter

public class Stop extends JFrame {

    public Stop(Game game) {

        setTitle("Stop panel");
        setSize(200,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300,300);

        setLayout(new FlowLayout());
        setVisible(true);

        JButton stop = new JButton("stop");
        add(stop);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                Start windowStart = new Start(new Game());



            }
        });

    }
}
