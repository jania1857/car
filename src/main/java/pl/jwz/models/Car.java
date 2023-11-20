package pl.jwz.models;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Car {

    private static Car instance;
    private double x;
    private double y;
    private double rotation;
    private int speed;
    private double rotationSpeed;
    private Image image;
    private int carWidth;
    private int carHeight;

    private Car() {
        this.x = x;
        this.y = y;
        this.rotation = 0;

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource("assets/cars/atrapa.png")));
        image = imageIcon.getImage();
        this.carWidth = image.getWidth(null);
        this.carHeight = image.getHeight(null);
    }

    public static synchronized Car getInstance(){
        if (instance == null){
            instance = new Car();
        }
        return instance;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public void draw(Graphics2D graphics) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(rotation, x + (double) image.getWidth(null) / 2, y + (double) image.getHeight(null) / 2);

        graphics.setTransform(transform);

        graphics.drawImage(image, (int) x, (int) y, null);

        graphics.setTransform(new AffineTransform());
        Sensors sensors = new Sensors();
        sensors.createSensors(graphics);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getCarWidth() {
        return carWidth;
    }

    public int getCarHeight() {
        return carHeight;
    }

    public void move() {
        double vRotation = rotation + 3 * (Math.PI / 2);
        double deltaX = Math.cos(vRotation) * speed;
        double deltaY = Math.sin(vRotation) * speed;

        x += deltaX;
        y += deltaY;
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