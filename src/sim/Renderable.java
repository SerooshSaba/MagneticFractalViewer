package sim;
import java.awt.*;

public interface Renderable {

    public void render( Graphics2D graphics, int x, int y, float scale );

    public void simulate();

}
