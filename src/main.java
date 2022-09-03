import sim.Attractor;
import sim.Point;
import threadclasses.CalculatorThread;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Attractor> attractors = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();

        for ( int y = 0; y < 800; y += 10 ) {
            for ( int x = 0; x < 800; x += 10 ) {
                Point point = new Point( x, y );
                point.addxdirection(0);
                points.add( point );
            }
        }

        attractors.add( new Attractor(400 - 100,400) );
        attractors.add( new Attractor(400 + 100,400) );
        //attractors.add( new Attractor(400,400 + 100) );

        for ( Attractor attractor : attractors ) {
            for (Point point : points) {
                attractor.lockTo(point);
            }
        }

        Thread panelThread = new Thread(){
            public void run() {
                new MyFrame(points, attractors);
            }
        };
        panelThread.start();

        // Start calculator threads
        CalculatorThread th1 = new CalculatorThread( points, attractors, 5, 1 );
        CalculatorThread th2 = new CalculatorThread( points, attractors, 5, 2 );
        CalculatorThread th3 = new CalculatorThread( points, attractors, 5, 3 );
        CalculatorThread th4 = new CalculatorThread( points, attractors, 5, 4 );
        CalculatorThread th5 = new CalculatorThread( points, attractors, 5, 5 );

        th1.join();
        th2.join();
        th3.join();
        th4.join();
        th5.join();

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();


    }
}