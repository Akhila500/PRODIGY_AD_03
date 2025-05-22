import java.awt.*;
import javax.swing.*;

public class StopwatchApp extends JFrame {
    private JLabel timeLabel;
    private JButton startButton, pauseButton, resetButton;

    private Timer timer;
    private long startTime;
    private long elapsedTime = 0;
    private boolean isRunning = false;

    public StopwatchApp() {
        setTitle("Stopwatch App");
        setSize(300, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 230, 250));

        
        timeLabel = new JLabel("00:00:000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        timeLabel.setForeground(new Color(128, 0, 128)); 
        timeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(timeLabel, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 230, 250)); 

        startButton = createStyledButton("Start");
        pauseButton = createStyledButton("Pause");
        resetButton = createStyledButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        timer = new Timer(10, e -> updateDisplay());

        
        startButton.addActionListener(e -> start());
        pauseButton.addActionListener(e -> pause());
        resetButton.addActionListener(e -> reset());

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(255, 182, 193)); 
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.PINK));
        button.setPreferredSize(new Dimension(80, 30));
        return button;
    }

    private void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            timer.start();
            isRunning = true;
        }
    }

    private void pause() {
        if (isRunning) {
            timer.stop();
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    private void reset() {
        timer.stop();
        elapsedTime = 0;
        isRunning = false;
        timeLabel.setText("00:00:000");
    }

    private void updateDisplay() {
        long current = System.currentTimeMillis() - startTime;
        int minutes = (int) (current / 60000);
        int seconds = (int) ((current / 1000) % 60);
        int millis = (int) (current % 1000);
        timeLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, millis));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchApp::new);
    }
}
