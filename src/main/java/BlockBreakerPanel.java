import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<>();
    ArrayList<Block> ball = new ArrayList<>();
    ArrayList<Block> superBall = new ArrayList<>();
    ArrayList<Block> gameOver = new ArrayList<>();
    ArrayList<Block> powerUp = new ArrayList<>();
    ArrayList<String> powerUps = new ArrayList<>();
    Random random = new Random();
    Block paddle;
    Thread thread;
    Animation animation;
    int size = 25;
    boolean isGameOver = false;

    BlockBreakerPanel() {
        paddle = new Block(175, 480, 150, 25, "src\\main\\resources\\black.png");
        ball.add(new Block(237, 437, 20, 20, "src\\main\\resources\\ball.png"));
        gameOver.add(new Block(115, 300, 258, 67, "src\\main\\resources\\clothesOff.png"));

        powerUps.add("anal");
        powerUps.add("sushi");
        powerUps.add("lick");

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

        for (int i = 0; i < 32; i ++) {
            blocks.get(random.nextInt(32)).isPowerUp = true;
        }

        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Block block : blocks) {
            block.draw(graphics, this);
        }

        for (Block block : powerUp) {
            block.draw(graphics, this);
        }

        for (Block block : superBall) {
            block.draw(graphics, this);
        }

        for (Block block : ball) {
            block.draw(graphics, this);
            try {
                if (block.y > getHeight()) {
                    isGameOver = true;
                    for (Block gameOverBlock : gameOver) {
                        gameOverBlock.draw(graphics, this);
                    }
                }

            } catch (Exception exception) {
                System.out.println("Interrupted exception - BlockBreakerPanel.java - 82");
            }
        }

        paddle.draw(graphics, this);
    }

    public void update() {
        for (Block power : powerUp) {
            power.y += 1;
            if (!isGameOver && power.intersects(paddle) && !power.isDestroyed) {
                power.isDestroyed = true;
                superBall.add(new Block(paddle.getLocation().x + 55, paddle.getLocation().y - 25, 20, 20, "src\\main\\resources\\superBall.png"));
            }
        }

        for (Block ball : ball) {
            ball.x += ball.dx;

            if (ball.x > (getWidth() - size) && ball.dx > 0 || ball.x < 0) {
                ball.dx *= -1;
            }

            if (ball.y < 0 || ball.intersects(paddle)) {
                ball.dy *= -1;
                ball.dx *= 1.2;
            }

            ball.y += ball.dy;

            for (Block block : blocks) {
                if ((block.left.intersects(ball) || block.right.intersects(ball)) && !block.isDestroyed) {
                    ball.dx *= -1.1;
                    block.isDestroyed = true;
                    if (block.isPowerUp) {
                        powerUp.add(new Block(block.x, block.y, 200, 150, "src\\main\\resources\\" + powerUps.get(random.nextInt(3)) + ".png"));
                    }
                } else if (ball.intersects(block) && !block.isDestroyed) {
                    block.isDestroyed = true;
                    ball.dy *= -1.1;
                    if (block.isPowerUp) {
                        powerUp.add(new Block(block.x, block.y, 200, 150, "src\\main\\resources\\" + powerUps.get(random.nextInt(3)) + ".png"));
                    }
                }
            }
        }

        for (Block ball : superBall) {
            ball.x += ball.dx;

            if (ball.x > (getWidth() - size) && ball.dx > 0 || ball.x < 0) {
                ball.dx *= -1;
            }

            if (ball.y < 0 || ball.intersects(paddle)) {
                ball.dy *= -1;
                ball.dx *= 1.2;
            }

            ball.y += ball.dy;

            for (Block block : blocks) {
                if ((block.left.intersects(ball) || block.right.intersects(ball)) && !block.isDestroyed) {
                    ball.dx *= -1;
                    block.isDestroyed = true;
                    if (block.isPowerUp) {
                        powerUp.add(new Block(block.x, block.y, 200, 150, "src\\main\\resources\\" + powerUps.get(random.nextInt(3)) + ".png"));
                    }
                } else if (ball.intersects(block) && !block.isDestroyed) {
                    block.isDestroyed = true;
                    ball.dy *= -1;
                    if (block.isPowerUp) {
                        powerUp.add(new Block(block.x, block.y, 200, 150, "src\\main\\resources\\" + powerUps.get(random.nextInt(3)) + ".png"));
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (thread == null && e.getKeyCode() == KeyEvent.VK_ENTER) {
            animation = new Animation(this);
            thread = new Thread(animation);
            thread.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(-1);
        }

        if (!isGameOver && e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 20;
        }

        if (!isGameOver && e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            paddle.x += 20;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
