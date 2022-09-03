package simulation;
import java.awt.*;

public class Attractor {

    private float x;
    private float y;
    private float force;
    private Color color;

    public Attractor( float x, float y, Color color ) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.force = 150;
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

    public Color getcolor() {
        return this.color;
    }

}