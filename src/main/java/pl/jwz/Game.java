package pl.jwz;

import pl.jwz.models.Car;
import pl.jwz.models.Track;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class Game extends JPanel implements ActionListener {

    private final Car car;
    private final Track track;
    private final Set<Integer> keysPressed = new HashSet<>();

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            keysPressed.add(e.getKeyCode());
        }

        public void keyReleased(KeyEvent e) {
            keysPressed.remove(e.getKeyCode());
        }
    }

    public Game() {
        car = Car.getInstance();
        car.setX(150);
        car.setY(300);
        track = new Track(0, 0, 1500, 800);

        car.setSpeed(5);
        car.setRotationSpeed(0.04);
        setFocusable(true);
        addKeyListener(new TAdapter());

        Timer timer = new Timer(1, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;

        track.draw(g2d);
        car.draw(g2d);
    }

    public void checkCollision() {
        BufferedImage carImage = car.getImage();
        BufferedImage trackImage = track.getTrackImage();

        int carX = (int) car.getX();
        int carY = (int) car.getY();

        for (int x = 0; x < carImage.getWidth(); x++) {
            for (int y = 0; y < carImage.getHeight(); y++) {
                int carPixel = carImage.getRGB(x, y);
                int trackPixel = trackImage.getRGB(carX + x, carY + y);

                if (carPixel == Color.BLACK.getRGB() && trackPixel == Color.BLACK.getRGB()) {
                    car.setX(100);
                    car.setY(300);
                    car.setRotation(0);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (keysPressed.contains(KeyEvent.VK_W)) {
            car.move();
            if (keysPressed.contains(KeyEvent.VK_A))
                car.rotateLeft();
            if (keysPressed.contains(KeyEvent.VK_D))
                car.rotateRight();
        }
        checkCollision();
        repaint();
    }
}
