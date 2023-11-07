package pl.jwz;

import javax.swing.*;

public class Window {

    private JFrame frame;
    private String windowTitle = "Car game";
    private int windowWidth = 1500;
    private int windowHeight = 800;
    private boolean windowVisibility = true;

    public Window(Game game) {
        frame = new JFrame(windowTitle);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(windowVisibility);
        frame.add(game);
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public boolean isWindowVisibility() {
        return windowVisibility;
    }

    public void setWindowVisibility(boolean windowVisibility) {
        this.windowVisibility = windowVisibility;
    }

    public JFrame getFrame() {
        return frame;
    }
}
