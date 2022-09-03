import javax.swing.*;
import sim.Attractor;
import sim.Point;
import java.awt.event.*;
import java.util.ArrayList;

public class Frame extends JFrame implements MouseListener, MouseWheelListener, KeyListener {

    public Panel panel;
    private int mousex = 0;
    private int mousey = 0;

    public Frame(ArrayList<Point> points, ArrayList<Attractor> attractors ) {

        panel = new Panel( points, attractors );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addMouseListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {
        this.mousex = event.getX();
        this.mousey = event.getY();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        int xmovement = event.getX() - this.mousex;
        int ymovement = event.getY() - this.mousey;
        this.panel.moveMouse( xmovement, ymovement );
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        this.panel.scrollWheel(event.getWheelRotation());
    }

    // KEYBOARD

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.panel.invokeRender();
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

}