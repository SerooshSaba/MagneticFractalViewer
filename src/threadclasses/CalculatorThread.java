package threadclasses;
import simulation.Attractor;
import simulation.Point;

import java.awt.*;

public class CalculatorThread extends Thread {

    private Point[] points;
    private Attractor[] attractors;
    private Color[] colors;

    private int chuncksize;
    private int start;
    private int end;

    public CalculatorThread(Point[] points, Attractor[] attractors, Color[] colors, int dividefactor, int selector ) {
        this.points = points;
        this.attractors = attractors;
        this.colors = colors;

        // Calculate area of operation
        this.chuncksize = points.length / dividefactor;
        this.start = this.chuncksize * ( selector - 1 );
        this.end   = this.chuncksize * ( selector );
    }

    @Override
    public void run() {

        double xdir, ydir, length, attraction_force;

        for (int i = this.start; i < this.end; i++) {

            Point point = points[i];

            while ( point.isnotcaptured() ) {
                for (Attractor attractor : this.attractors) {

                    // Find direction vector
                    xdir = attractor.getxposition() - point.getxposition();
                    ydir = attractor.getyposition() - point.getyposition();

                    // Find length between point and attractor
                    length = Math.sqrt(Math.abs(xdir * xdir) + Math.abs(ydir * ydir));

                    // Normalize direction vectors
                    xdir /= length;
                    ydir /= length;

                    if ( length > 8 ) {

                        // Calculate attraction force between point and attractor
                        attraction_force = attractor.getforce() / (length*length);

                        // Apply movement to point
                        point.addxdirection(xdir * attraction_force );
                        point.addydirection(ydir * attraction_force );
                        point.simulate();

                    }
                    else if ( length <= 8 ) {
                        point.capture();
                        this.colors[i] = attractor.getcolor();
                    }

                }
            }

        }

    }

    /*
    @Override
    public void run() {

        double xdir, ydir, length, attraction_force;
        double ATTRACTOR_FORCE;

        while ( true ) {

            for (Attractor attractor : this.attractors) {

                ATTRACTOR_FORCE = attractor.getforce();

                for (int i = this.start; i < this.end; i++) {

                    Point point = points[i];

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
    */

}
