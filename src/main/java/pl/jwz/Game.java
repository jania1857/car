package pl.jwz;

import lombok.Getter;
import lombok.Setter;
import pl.jwz.models.Car;
import pl.jwz.models.FuzzyLogic;
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
    private final Car car;
    private final Track track;
    private final Set<Integer> keysPressed = new HashSet<>();
    private FuzzyLogic fuzzyLogic;

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            keysPressed.add(e.getKeyCode());
        }

        public void keyReleased(KeyEvent e) {
            keysPressed.remove(e.getKeyCode());
        }
    }

    public Game() {
        track = new Track();
        trackImage = track.getTrackImage();

        car = new Car();
        car.setTrackImage(trackImage);
        car.setX(150);
        car.setY(350);

        car.setSpeed(3d);
        car.setRotationSpeed(0.09d);

        setFocusable(true);
        addKeyListener(new TAdapter());

        Timer timer = new Timer(1, this);
        timer.start();
        fuzzyLogic = new FuzzyLogic(50);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;

        track.draw(g2d);
        car.draw(g2d);

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
        checkCollision();
        repaint();
        car.move();
        fuzzyLogic.ChangeCarRotation((int) car.getLeftSensorLength(), (int) car.getCenterSenorLength(), (int) car.getRightSensorLength(), car);
    }

    private void resetCarPosition() {
        car.setX(100);
        car.setY(380);
        car.setRotation(0);
    }
}