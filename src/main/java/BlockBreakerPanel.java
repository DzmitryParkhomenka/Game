import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<>();
    ArrayList<Block> ball = new ArrayList<>();
    Block paddle;
    Thread thread;
    Animation animation;
    int size = 25;

    BlockBreakerPanel() {
        paddle = new Block(175, 480, 150, 25, "src\\main\\resources\\black.png");

        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 0, 60, 25, "src\\main\\resources\\purple.png"));
        }

        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 25, 60, 25, "src\\main\\resources\\orange.png"));
        }

        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 50, 60, 25, "src\\main\\resources\\green.png"));
        }

        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 75, 60, 25, "src\\main\\resources\\blue.png"));
        }

        ball.add(new Block(237, 456, 25, 25, "src\\main\\resources\\ball.png"));

        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Block block : blocks) {
            block.draw(graphics, this);
        }

        for (Block block : ball) {
            block.draw(graphics, this);
        }

        paddle.draw(graphics, this);
    }

    public void update() {
        for (Block block : ball) {
            block.x += block.dx;
            if (block.x > (getWidth() - size) && block.dx > 0 || block.x < 0) {
                block.dx *= -1;
            }
            if (block.y < 0 || block.intersects(paddle)) {
                block.dy *= -1;
            }
            block.y += block.dy;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            animation = new Animation(this);
            thread = new Thread(animation);
            thread.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(-1);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 15;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            paddle.x += 15;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
