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

    private CalculatorThread[] threads = new CalculatorThread[10];

    private int SIZE;
    private int FULL_SIZE;

    public Calculator( int resolution, float centerx, float centery, float scale ) {

        // Instantiate attractors
        this.attractors = new Attractor[3];
        this.attractors[0] = new Attractor(400 - 100,400, new Color(255,39,48));
        this.attractors[1] = new Attractor(400 + 100,400, new Color(227, 197, 21));
        this.attractors[2] = new Attractor(400,400 + 100, new Color(0,30,255));

        // Save resolution
        this.SIZE = resolution;
        this.FULL_SIZE = resolution * resolution;

        this.centerx = centerx;
        this.centery = centery;
        this.scale = scale;
    }

    public void calculate() {

        this.points = new Point[this.FULL_SIZE];
        this.colors = new Color[this.FULL_SIZE];

        // Create points
        int step_size = 800 / this.SIZE;

        int i = 0;
        for ( int y = 0; y < 800; y += step_size ) {
            for ( int x = 0; x < 800; x += step_size ) {
                this.points[i] = new Point( this.centerx + x * this.scale, this.centery + y * this.scale );
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
