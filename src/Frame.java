import javax.swing.*;
import java.awt.event.*;

public class Frame extends JFrame implements MouseListener, MouseWheelListener, KeyListener {

    public Panel panel;
    private float mousex = 0;
    private float mousey = 0;

    public Frame() {
        panel = new Panel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addMouseListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
    }

    // MOUSE

    @Override
    public void mousePressed(MouseEvent event) {
        this.mousex = event.getX();
        this.mousey = event.getY();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        float xmovement = this.mousex - event.getX();
        float ymovement = this.mousey - event.getY();
        this.panel.moveMouse( xmovement, ymovement );
        this.panel.invokerecalculation();
        this.panel.invokeRender();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        this.panel.scrollWheel(event.getWheelRotation());
        this.panel.invokerecalculation();
        this.panel.invokeRender();
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mouseClicked(MouseEvent event) {}


    // KEYBOARD

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {}

}