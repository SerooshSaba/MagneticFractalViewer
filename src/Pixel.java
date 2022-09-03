import java.awt.*;

public class Pixel {

    int size;

    Pixel( int size ) {
        this.size = size;
    }

    void render( Graphics2D graphics ) {
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, this.size, this.size );
    }

}