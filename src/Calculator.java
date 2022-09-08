import simulation.Attractor;
import simulation.Point;
import threadclasses.CalculatorThread;
import java.awt.*;

public class Calculator {

    float centerx;
    float centery;
    float scale;

    private Attractor[] attractors;
    private Point[] points;
    private Color[] colors;

    private CalculatorThread[] threads = new CalculatorThread[6];

    private int SIZE;
    private int FULL_SIZE;

    public Calculator( int resolution, float centerx, float centery, float scale, Attractor[] attractors ) {

        System.out.println("Calculator init");

        // Instantiate attractors
        this.attractors = attractors;

        // Save resolution
        this.SIZE = resolution;
        this.FULL_SIZE = resolution * resolution;

        this.points = new Point[this.FULL_SIZE];
        this.colors = new Color[this.FULL_SIZE];

        this.centerx = centerx;
        this.centery = centery;
        this.scale = scale;

        // Create points
        int step_size = 800 / this.SIZE;
        int i = 0;
        for ( int y = 0; y < 800; y += step_size ) {
            for ( int x = 0; x < 800; x += step_size ) {
                this.points[i] = new Point( this.centerx + x * this.scale, this.centery + y * this.scale );
                i++;
            }
        }

    }

    public void releasepoints() {
        for ( Point point : this.points ) {
            point.release();
        }
    }

    public void calculate() {

        System.out.println( this.centerx + " " + this.centery + " " + this.scale );

        // Move points to current x, y and scale position
        int step_size = 800 / this.SIZE;

        int i = 0;
        for ( int y = 0; y < 800; y += step_size ) {
            for ( int x = 0; x < 800; x += step_size ) {
                this.points[i].setxposition( this.centerx + x * this.scale );
                this.points[i].setyposition( this.centery + y * this.scale );
                i++;
            }
        }

        // Create threads & start
        for ( i = 0; i < this.threads.length; i++ ) {
            this.threads[i] = new CalculatorThread( this.points, this.attractors, this.colors, this.threads.length, i+1);
            this.threads[i].start();
        }

    }

    public void render( Graphics2D graphics ) {
        int size = 800 / this.SIZE;
        for ( int i = 0; i < this.colors.length; i++ ) {
            if ( this.colors[i] != null ) {
                int x = i % this.SIZE;
                int y = (int) ((float)i / (float)this.FULL_SIZE * this.SIZE);

                graphics.setColor(this.colors[i]);
                graphics.fillRect( (int) (x * size), (int) (y * size), size, size);
            }
        }
    }

}