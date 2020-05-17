import java.awt.*;

public class Block extends Rectangle {

    Image image;
    int dx = 3;
    int dy = -5;
    Rectangle left, right;
    boolean isDestroyed = false;
    boolean isPowerUp = false;

    Block(int a, int b, int w, int h, String imagePath) {
        x = a;
        y = b;
        width = w;
        height = h;
        left = new Rectangle(a - 1, b, 1, h);
        right = new Rectangle(a + w + 1, b, 1, h);
        image = Toolkit.getDefaultToolkit().getImage(imagePath);
    }

    public void draw(Graphics graphics, Component component) {
        if (!isDestroyed) {
            graphics.drawImage(image, x, y, width, height, component);
        }
    }
}
