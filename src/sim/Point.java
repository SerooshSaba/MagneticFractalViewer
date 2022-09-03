package sim;
import java.awt.*;

public class Point {

    private boolean captured = false;

    private double resistance = 0.999;
    private double position[]  = new double[2];
    private double direction[] = new double[2];

    public Point( double x, double y ) {
        this.position[0] = x;
        this.position[1] = y;
    }

    // GET
    public double getxposition() {
        return this.position[0];
    }
    public double getyposition() {
        return this.position[1];
    }
    public double getxdirection() {
        return this.direction[0];
    }
    public double getydirection() {
        return this.direction[1];
    }

    public void addxdirection( double x ) {this.direction[0] += x;}
    public void addydirection( double y ) {
        this.direction[1] += y;
    }

    public void capture() {
        this.captured = true;
    }

    public boolean isnotcaptured() {
        if ( this.captured == false )
            return true;
        return false;
    }

    public void simulate() {
        this.position[0] += this.getxdirection();
        this.position[1] += this.getydirection();
        this.direction[0] *= resistance;
        this.direction[1] *= resistance;
    }

    public void render( Graphics2D graphics, int x, int y, float scale ) {
        graphics.fillRect( (int) ((this.getxposition() + x) * scale ), (int) ( (this.getyposition() + y) * scale ), 1, 1 );
    }

    @Override
    public String toString() {
        return this.getxposition() + " " + this.getyposition();
    }

}