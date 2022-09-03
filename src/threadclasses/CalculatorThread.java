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

    // More point air friction -> faster render times but lower complexity

    public CalculatorThread( ArrayList<Point> points, ArrayList<Attractor> attractors, int dividefactor, int selector ) {

        //this.points = points;
        this.points = new ArrayList<>();

        for ( Point point : points ) {
            this.points.add( point );
        }

        this.attractors = attractors;

        // Calculate area of operation
        this.chuncksize = points.size() / dividefactor;
        this.start = this.chuncksize * ( selector - 1 );
        this.end   = this.chuncksize * ( selector );
    }

    @Override
    public void run() {

        double xdir, ydir, length, attraction_force, additional_friction_force;
        double ATTRACTOR_FORCE;

        while ( true ) {

            for (Attractor attractor : this.attractors) {

                ATTRACTOR_FORCE = attractor.getforce();

                for (int i = this.start; i < this.end; i++) {

                    // Find direction vector
                    xdir = attractor.getxposition() - points.get(i).getxposition();
                    ydir = attractor.getyposition() - points.get(i).getyposition();

                    // Find length between point and attractor
                    length = Math.sqrt(Math.abs(xdir * xdir) + Math.abs(ydir * ydir));

                    // Normalize direction vectors
                    xdir /= length;
                    ydir /= length;

                    if (length > 15) {

                        // Calculate attraction force between point and attractor
                        attraction_force = ATTRACTOR_FORCE / (length*length);

                        // Apply movement to point
                        points.get(i).addxdirection(xdir * attraction_force );
                        points.get(i).addydirection(ydir * attraction_force );
                        points.get(i).simulate();

                    } else {

                        // How friction is applied, determines the complexity of the fractal
                        additional_friction_force = 0.8 + (length/15) * (1-0.8);

                        // Calculate force
                        attraction_force = ATTRACTOR_FORCE / (15*15);

                        // Apply attraction to point
                        points.get(i).addxdirection(xdir * attraction_force );
                        points.get(i).addydirection(ydir * attraction_force );
                        points.get(i).simulate();

                        // Apply additional friction on point
                        points.get(i).deaccelerate(additional_friction_force);

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
