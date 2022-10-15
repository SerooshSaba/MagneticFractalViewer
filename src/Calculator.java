import simulation.Attractor;
import simulation.Point;
import threadclasses.CalculatorThread;
import java.awt.*;
import java.util.Random;

public class Calculator {

    float centerx;
    float centery;
    float scale;

    private Attractor[] attractors;
    private Point[] points;
    private Color[] colors;

    private int[] indeces;

    private CalculatorThread[] threads = new CalculatorThread[6];
    private Random random = new Random();

    private int SIZE = 500;
    private int FULL_SIZE;

    public Calculator( float centerx, float centery, float scale, Attractor[] attractors ) {

        // Instantiate attractors
        this.attractors = attractors;

        // Save resolution
        this.FULL_SIZE = this.SIZE * this.SIZE;

        this.points = new Point[this.FULL_SIZE];
        this.colors = new Color[this.FULL_SIZE];

        this.centerx = centerx;
        this.centery = centery;
        this.scale = scale;

        // Create points
        int step_size = 1000 / this.SIZE;
        int i = 0;

        System.out.println( "step_size: " + step_size );

        for ( int y = 0; y < 1000; y += step_size ) {
            for ( int x = 0; x < 1000; x += step_size ) {
                this.points[i] = new Point( this.centerx + x * this.scale, this.centery + y * this.scale );
                i++;
            }
        }

        System.out.println("i: " + i);

        // Randomly assign points to threads
        this.indeces = new int[this.points.length];
        for ( i = 0; i < this.points.length; i++ ) {
            this.indeces[i] = i;
        }

        // Randomize indeces
        int tmp;
        for ( i = 0; i < this.points.length; i++ ) {

            // Create random number
            int randnum = this.random.nextInt((this.indeces.length - 1) + 0);

            // Swap
            tmp = this.indeces[randnum];
            this.indeces[randnum] = this.indeces[i];
            this.indeces[i] = tmp;
        }

    }


    public void calculate() {

        // Move points to current x, y and scale positions
        int step_size = 1000 / this.SIZE;
        int i = 0;
        for ( int y = 0; y < 1000; y += step_size ) {
            for ( int x = 0; x < 1000; x += step_size ) {
                this.points[i].setxposition( this.centerx + x * this.scale );
                this.points[i].setyposition( this.centery + y * this.scale );
                i++;
            }
        }

        // Create threads & start
        for ( i = 0; i < this.threads.length; i++ ) {
            this.threads[i] = new CalculatorThread( this.points, this.attractors, this.colors, this.threads.length, i+1, this.indeces );
            this.threads[i].start();
        }

    }

    public void render( Graphics2D graphics ) {
        int size = 1000 / this.SIZE;
        for ( int i = 0; i < this.colors.length; i++ ) {
            if ( this.colors[i] != null ) {
                int x = i % this.SIZE;
                int y = (int) ((float)i / (float)this.FULL_SIZE * this.SIZE);
                graphics.setColor(this.colors[i]);
                graphics.fillRect( (x*size), (y*size), size, size);
            }
        }
    }

}