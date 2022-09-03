import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import sim.Point;
import sim.Attractor;

public class Panel extends JPanel {

    Timer timer;
    ArrayList<Point> points;
    ArrayList<Attractor> attractors;

    int centerx = 0;
    int centery = 0;
    float scale = 1;

    Panel(ArrayList<Point> points, ArrayList<Attractor> attractors ) {
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

        // Render points
        //graphics.setColor(Color.WHITE);
        graphics.setColor(new Color(0,255,0));
        for ( Point point : this.points ) {
            point.render(graphics, this.centerx, this.centery, this.scale );
        }

        // Render canvas

        // Render position and scale
        graphics.setColor(new Color(0,255,0));
        graphics.drawString( "x:" + this.centerx,10,750);
        graphics.drawString( "y:" + this.centery,10,770);
        graphics.drawString( Float.toString(this.scale),10,790);

    }

    public void invokeRender() {
        this.repaint();
    }

    public void moveMouse( float x, float y ) {
        this.centerx += x / this.scale;
        this.centery += y / this.scale;
    }

    public void scrollWheel( int value ) {
        if ( value == 1 ) {
            this.scale *= 0.95;
        }
        else {
            this.scale *= 1.1;
        }
    }

}