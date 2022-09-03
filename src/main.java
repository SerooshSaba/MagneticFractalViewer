import sim.Attractor;
import sim.Point;
import threadclasses.CalculatorThread;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        ArrayList<Attractor> attractors = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();

        for ( int y = 0; y < 800; y += 4 ) {
            for ( int x = 0; x < 800; x += 4 ) {
                Point point = new Point( x, y );
                points.add( point );
            }
        }

        attractors.add( new Attractor(400 - 100,400, new int[]{ 255, 0, 0 }) );
        attractors.add( new Attractor(400 + 100,400, new int[]{ 0, 255, 0 }) );
        attractors.add( new Attractor(400,400 - 100, new int[]{ 0, 255, 0 }) );
        attractors.add( new Attractor(400,400 + 100, new int[]{ 0, 255, 0 }) );

        Thread panelThread = new Thread(){
            public void run() {
                new Frame(points, attractors);
            }
        };
        panelThread.start();

        // Start calculator threads
        new CalculatorThread( points, attractors, 8, 1 ).start();
        new CalculatorThread( points, attractors, 8, 2 ).start();
        new CalculatorThread( points, attractors, 8, 3 ).start();
        new CalculatorThread( points, attractors, 8, 4 ).start();

        new CalculatorThread( points, attractors, 8, 5 ).start();
        new CalculatorThread( points, attractors, 8, 6 ).start();
        new CalculatorThread( points, attractors, 8, 7 ).start();
        new CalculatorThread( points, attractors, 8, 8 ).start();

    }
}