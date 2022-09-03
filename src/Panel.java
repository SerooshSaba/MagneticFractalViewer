import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {

    Timer timer;

    float centerx = 0;
    float centery = 0;
    float scale = 1;

    Calculator calculator;

    Panel() {
        this.setPreferredSize(new Dimension(800,800));
        this.setBackground(Color.black);

        this.timer = new Timer(100, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();

        this.calculator = new Calculator(400, centerx, centery, scale );
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
        this.calculator = new Calculator(400, centerx, centery, scale );
        this.calculator.calculate();
    }

    public void invokeRender() {
        repaint();
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