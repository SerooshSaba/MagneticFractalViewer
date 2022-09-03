import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import sim.Point;
import sim.Attractor;

public class MyPanel extends JPanel {

    Timer timer;
    ArrayList<Point> points;
    ArrayList<Attractor> attractors;

    int centerx = 0;
    int centery = 0;
    float scale = 1;

    MyPanel( ArrayList<Point> points, ArrayList<Attractor> attractors ) {
        this.setPreferredSize(new Dimension(800,800));
        this.setBackground(Color.black);

        this.points = points;
        this.attractors = attractors;

        this.timer = new Timer(1, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        timer.start();
    }

    public void paint( Graphics g ) {

        Graphics2D graphics = (Graphics2D) g;
        super.paint(graphics);

        /*
        graphics.setColor(Color.RED);
        for ( Attractor attractor : this.attractors ) {
            attractor.render(graphics, this.centerx, this.centery, this.scale );
        }
        */

        graphics.setColor(Color.WHITE);
        for ( Point point : this.points ) {
            point.render(graphics, this.centerx, this.centery, this.scale );
        }
    }

    public void invokeRender() {
        this.repaint();
    }

    public void moveMouse( float x, float y ) {
        this.centerx += x / this.scale;
        this.centery += y / this.scale;
    }

    public void scrollWheel( int value ) {
        if ( value == -1 ) {
            this.scale *= 1.1;
        }
        else if ( value == 1 ) {
            this.scale *= 0.95;
        }
    }

}