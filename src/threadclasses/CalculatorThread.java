package threadclasses;
import sim.Attractor;
import sim.Point;
import java.util.ArrayList;

public class CalculatorThread extends Thread {

    private ArrayList<Point> points;
    private ArrayList<Attractor> attractors;
    private int chuncksize;
    private int start;
    private int end;

    public CalculatorThread( ArrayList<Point> points, ArrayList<Attractor> attractors, int dividefactor, int selector ) {

        this.attractors = attractors;

        this.points = points;

        // Calculate area of operation
        this.chuncksize = points.size() / dividefactor;
        this.start = this.chuncksize * ( selector - 1 );
        this.end   = this.chuncksize * ( selector );

    }

    @Override
    public void run() {

        double xdir, ydir, length, attraction_force;
        double ATTRACTOR_FORCE;

        while ( true ) {

            for (Attractor attractor : this.attractors) {

                ATTRACTOR_FORCE = attractor.getforce();

                for (int i = this.start; i < this.end; i++) {

                    Point point = points.get(i);

                    // Find direction vector
                    xdir = attractor.getxposition() - point.getxposition();
                    ydir = attractor.getyposition() - point.getyposition();

                    // Find length between point and attractor
                    length = Math.sqrt(Math.abs(xdir * xdir) + Math.abs(ydir * ydir));

                    // Normalize direction vectors
                    xdir /= length;
                    ydir /= length;

                    if ( length > 8 && point.isnotcaptured()) {

                        // Calculate attraction force between point and attractor
                        attraction_force = ATTRACTOR_FORCE / (length*length);

                        // Apply movement to point
                        point.addxdirection(xdir * attraction_force );
                        point.addydirection(ydir * attraction_force );
                        point.simulate();

                    }
                    else if ( length <= 8 && point.isnotcaptured() ) {
                        point.capture();
                        // get color of attactor
                    }


                }
            }

            // REMOVE LATER
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }

}
