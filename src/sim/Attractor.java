package sim;
import java.awt.*;
import java.util.ArrayList;

public class Attractor {

    private float x;
    private float y;
    private float force;

    private ArrayList<Point> points = new ArrayList<>();

    public Attractor( float x, float y ) {
        this.x = x;
        this.y = y;
        this.force = 100;
    }

    public float getxposition() {
        return this.x;
    }

    public float getyposition() {
        return this.y;
    }

    public float getforce() {
        return this.force;
    }

    public void lockTo( Point point ) {
        this.points.add(point);
    }

    public void render( Graphics2D graphics, int x, int y, float scale ) {
        graphics.fillOval( (int) ( (this.x + x) * scale ), (int) ( (this.y + y) * scale ), 10, 10 );
    }

}