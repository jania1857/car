package pl.jwz;

import lombok.Getter;
import lombok.Setter;
import pl.jwz.models.Car;
import pl.jwz.models.Sensors;
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

@Getter
@Setter
public class Game extends JPanel implements ActionListener {

    private BufferedImage trackImage;
    private Sensors sensors;
    private final Car car;
    private final Track track;
    private ModifierSensors modifierSensors;
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
        car = new Car();
        car.setX(150);
        car.setY(300);
        track = new Track();
        car.setSpeed(5);
        car.setRotationSpeed(0.04);

        trackImage = track.getTrackImage();
        modifierSensors = new ModifierSensors();
        setFocusable(true);
        addKeyListener(new TAdapter());

        Timer timer = new Timer(1, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;

        sensors = new Sensors(car, modifierSensors);
        track.draw(g2d);
        car.draw(g2d);
        sensors.createSensors(g2d, trackImage);
        modifierSensors.test(sensors.isLeftCollision());

    }

    public void checkCollision() {
        BufferedImage carImage = car.getImage();

        int carX = (int) car.getX();
        int carY = (int) car.getY();

        try {

            for (int x = 0; x < carImage.getWidth(); x++) {
                for (int y = 0; y < carImage.getHeight(); y++) {
                    int carPixel = carImage.getRGB(x, y);
                    int trackPixel = trackImage.getRGB(carX + x, carY + y);

                    if (carPixel == Color.BLACK.getRGB() && trackPixel == Color.BLACK.getRGB()) {
                        resetCarPosition();

                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            resetCarPosition();
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

    private void resetCarPosition() {
        car.setX(100);
        car.setY(300);
        car.setRotation(0);
    }
}