import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CountdownButton is a customizable button that disables itself during a countdown.
 * It shows the remaining time on the button text and re-enables itself when the countdown is over.
 */
public class CountdownButton extends JButton {
    private int countdownDuration; // Duration in seconds
    private String originalText; // Store the original button text
    private Color originalBackgroundColor; // Store the original background color
    private Timer countdownTimer; // Timer for countdown

    /**
     * Constructor to initialize the button with customizable text, countdown duration, and style.
     * 
     * @param text      The initial text to display on the button.
     * @param duration  The countdown duration in seconds.
     * @param bgColor   The background color of the button.
     * @param fgColor   The text color of the button.
     * @param font      The font of the button text.
     */
    public CountdownButton(String text, int duration, Color bgColor, Color fgColor, Font font) {
        super(text); // Set the button text
        this.originalText = text; // Store the original text
        this.countdownDuration = duration; // Set the countdown duration
        this.originalBackgroundColor = bgColor; // Set and store original background color

        // Set button properties (style)
        setButtonStyle(bgColor, fgColor, font);

        // Add action listener to handle button clicks
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCountdown();
            }
        });
    }

    /**
     * Constructor with default button style if customization is not needed.
     * 
     * @param text     The initial text to display on the button.
     * @param duration The countdown duration in seconds.
     */
    public CountdownButton(String text, int duration) {
        this(text, duration, Color.BLACK, Color.WHITE, new Font("Arial", Font.BOLD, 60));
    }

    // Method to set button style
    private void setButtonStyle(Color bgColor, Color fgColor, Font font) {
        this.setBackground(bgColor); // Set background color
        this.setForeground(fgColor); // Set text color
        this.setFont(font); // Set font
        this.setFocusPainted(false); // Remove focus border
        this.setBorderPainted(false); // Remove border
        this.setOpaque(true); // Make background opaque

        // Add hover effect using mouse listener
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.DARK_GRAY); // Change color on hover
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(originalBackgroundColor); // Reset to original color
            }
        });
    }

    // Method to start the countdown
    private void startCountdown() {
        this.setEnabled(false); // Disable the button during the countdown

        // Timer to handle countdown
        countdownTimer = new Timer(1000, new ActionListener() {
            int timeLeft = countdownDuration;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    CountdownButton.this.setText("Retry in " + timeLeft + " seconds...");
                    timeLeft--;
                } else {
                    countdownTimer.stop();
                    CountdownButton.this.setText(originalText);
                    CountdownButton.this.setEnabled(true); // Re-enable the button
                }
            }
        });
        countdownTimer.start(); // Start the countdown
    }
}
