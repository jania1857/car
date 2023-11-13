package pl.jwz.models;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Track {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private BufferedImage trackImage;
    private String trackImagePath = "assets/tracks/track3.png";

    public Track(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.trackImage = loadTrackImage();
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, width, height);

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon trackIcon =
                new ImageIcon(Objects.requireNonNull(classLoader.getResource(trackImagePath)));
        Image trackImage = trackIcon.getImage();
        graphics.drawImage(trackImage, x, y, null);
    }

    private BufferedImage loadTrackImage() {
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon trackIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource(trackImagePath)));
        BufferedImage image = new BufferedImage(trackIcon.getIconWidth(), trackIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        trackIcon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        return image;
    }

    public BufferedImage getTrackImage() {
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon trackIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource(trackImagePath)));
        trackImage = new BufferedImage(trackIcon.getIconWidth(), trackIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = trackImage.createGraphics();
        trackIcon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        return trackImage;
    }
}