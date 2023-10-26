package pl.jwz.models;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Track {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Track(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, width, height);

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon trackIcon =
                new ImageIcon(Objects.requireNonNull(classLoader.getResource("assets/tracks/track1.png")));
        Image trackImage = trackIcon.getImage();
        graphics.drawImage(trackImage, x, y, null);
    }



}