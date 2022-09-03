package sim;

public class Attractor {

    private float x;
    private float y;
    private float force;
    private int[] color;

    public Attractor( float x, float y, int[] color ) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public int[] getcolor() {
        return this.color;
    }

}