public class Animation implements Runnable {

    BlockBreakerPanel blockBreakerPanel;

    Animation(BlockBreakerPanel panel) {
        blockBreakerPanel = panel;
    }

    public void run() {
        while (true) {
            blockBreakerPanel.update();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                System.out.println("Caught InterruptedException. Animation.java - 15");
            }
        }
    }
}
