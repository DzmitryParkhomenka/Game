import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("BlockBreaker");
        BlockBreakerPanel panel = new BlockBreakerPanel();

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(499, 600);
        frame.setResizable(false);

    }
}
