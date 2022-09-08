import simulation.Attractor;
import simulation.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {

    private Attractor[] attractors;
    private Point[] points;

    private Timer timer;

    private float centerx = 0;
    private float centery = 0;
    private float scale = 1;

    private Calculator calculator;

    Panel() {
        this.setPreferredSize(new Dimension(800,800));

        this.timer = new Timer(50, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        this.attractors = new Attractor[4];
        this.attractors[0] = new Attractor(400 - 100,400, new Color(72, 56,  56));
        this.attractors[1] = new Attractor(400 + 100,400, new Color(66, 133, 91));
        this.attractors[2] = new Attractor(400 - 100,400 + 100, new Color(210, 215,159));
        this.attractors[3] = new Attractor(400 + 100,400 + 100, new Color(255, 50, 50));

        this.calculator = new Calculator(200, centerx, centery, scale, attractors );
        this.calculator.calculate();
    }

    public void paint( Graphics g ) {
        Graphics2D graphics = (Graphics2D) g;
        this.calculator.render(graphics);
        // Render position and scale
        graphics.setColor(Color.WHITE);
        graphics.drawString( "x:" + this.centerx,10,750);
        graphics.drawString( "y:" + this.centery,10,770);
        graphics.drawString( Float.toString(this.scale),10,790);
    }

    public void invokerecalculation() {
        this.calculator = new Calculator(200, centerx, centery, scale, attractors );
        //this.calculator.releasepoints();
        this.calculator.calculate();
    }

    public void moveMouse( float x, float y ) {
        this.centerx += x * this.scale;
        this.centery += y * this.scale;
    }

    public void scrollWheel( int value ) {
        if ( value == 1 ) {
            this.scale *= 1.5;
        }
        else {
            this.scale *= 0.6;
        }
    }

}