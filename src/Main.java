import java.awt.*;

/**
 * Created by anton.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainPanel panel = new MainPanel();
            panel.setVisible(true);
        });
    }
}
