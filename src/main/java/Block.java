import java.awt.*;

public class Block extends Rectangle {

    Image image;
    int dx = 3;
    int dy = -3;
    boolean destroyed = false;

    Block(int a, int b, int w, int h, String imagePath) {
        x = a;
        y = b;
        width = w;
        height = h;
        image = Toolkit.getDefaultToolkit().getImage(imagePath);
    }

    public void draw(Graphics graphics, Component component) {
        if (!destroyed) {
            graphics.drawImage(image, x, y, width, height, component);
        }
    }
}
