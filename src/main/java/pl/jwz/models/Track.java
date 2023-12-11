package pl.jwz.models;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter
@Setter
public class Track {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private BufferedImage trackImage;
    private final String trackImagePath = "assets/tracks/track1.png";

    public Track() {
        this.x = 0;
        this.y = 0;
        this.width = 1500;
        this.height = 800;
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