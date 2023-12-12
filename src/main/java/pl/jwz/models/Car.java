package pl.jwz.models;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Car {

    private double x;
    private double y;
    private double rotation;
    private double speed;
    private double rotationSpeed;
    private Image image;
    private int carWidth;
    private int carHeight;
    private List<Sensor> sensors;
    private BufferedImage trackImage;
    private double leftSensorLength;
    private double centerSenorLength;
    private double rightSensorLength;

    public Car() {
        this.rotation = 0;

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource("assets/cars/21x21.png")));
        image = imageIcon.getImage();
        this.carWidth = image.getWidth(null);
        this.carHeight = image.getHeight(null);

        sensors = new ArrayList<>();
        this.sensors.add(new Sensor(Math.toRadians(-45), false, carHeight));
        this.sensors.add(new Sensor(Math.toRadians(-90), true, carHeight));
        this.sensors.add(new Sensor(Math.toRadians(-135), false, carHeight));
    }

    public void draw(Graphics2D graphics) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(rotation, x + (double) image.getWidth(null) / 2, y + (double) image.getHeight(null) / 2);

        graphics.setTransform(transform);

        graphics.drawImage(image, (int) x, (int) y, null);

        graphics.setTransform(new AffineTransform());

        for (Sensor sensor : sensors) {
            sensor.draw(graphics, x + (double) carWidth / 2, y + (double) carHeight / 2, carHeight, carWidth);
        }

        for (int i = 0; i < sensors.size(); i++) {
            Sensor sensor = sensors.get(i);

            if (i == 0) {
                rightSensorLength = sensor.getDistance();
            } else if (i == 1) {
                centerSenorLength = sensor.getDistance();
            } else if (i == 2) {
                leftSensorLength = sensor.getDistance();
            }
        }
    }

    public void move() {
        double vRotation = rotation + 3 * (Math.PI / 2);
        double deltaX = Math.cos(vRotation) * speed;
        double deltaY = Math.sin(vRotation) * speed;

        x += deltaX;
        y += deltaY;

        for (Sensor sensor : sensors) {
            sensor.update(x + (double) carWidth / 2, y + (double) carHeight / 2, trackImage, rotation);
        }
    }

    public void rotateLeft() {
        rotation -= rotationSpeed;
        if (rotation <= 0)
            rotation += 2 * Math.PI;
    }

    public void rotateRight() {
        rotation += rotationSpeed;
        if (rotation >= 2 * Math.PI)
            rotation -= 2 * Math.PI;
    }

    public BufferedImage getImage() {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }


}
