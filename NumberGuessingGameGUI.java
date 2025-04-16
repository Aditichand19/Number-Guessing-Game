import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {
    private int targetNumber;
    private int attempts;
    private final int maxAttempts = 100;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel scoreLabel;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game 🎯");
        setSize(500, 300);
        setLayout(new GridLayout(6, 1, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(230, 243, 255)); // Soft blue background

        Font titleFont = new Font("Arial", Font.BOLD, 55);
        Font normalFont = new Font("Arial", Font.PLAIN, 28);

        JLabel welcomeLabel = new JLabel("🎉 Welcome to the Number Guessing Game!", SwingConstants.CENTER);
        welcomeLabel.setFont(titleFont);
        add(welcomeLabel);

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:", SwingConstants.CENTER);
        instructionLabel.setFont(normalFont);
        add(instructionLabel);

        guessField = new JTextField("Enter your Number here");
        guessField.setFont(normalFont);
        guessField.setForeground(new Color(50, 50, 50)); // Much darker gray for better visibility
        guessField.setBackground(Color.WHITE);
        guessField.setHorizontalAlignment(JTextField.CENTER); // Center align text
        guessField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237)), // Cornflower blue border
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        guessField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (guessField.getText().equals("Enter your Number here")) {
                    guessField.setText("");
                    guessField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (guessField.getText().isEmpty()) {
                    guessField.setText("Enter your Number here");
                    guessField.setForeground(new Color(50, 50, 50));
                }
            }
        });
        add(guessField);

        guessButton = new JButton("Submit Guess");
        guessButton.setFont(normalFont);
        guessButton.addActionListener(this);
        guessButton.setBackground(new Color(76, 175, 80)); // Green button
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        add(guessButton);

        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        feedbackLabel.setFont(normalFont);
        feedbackLabel.setOpaque(true);
        add(feedbackLabel);

        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(normalFont);
        add(scoreLabel);

        resetGame();
        setVisible(true);
    }

    private void resetGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
        scoreLabel.setText("");
        guessButton.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess < targetNumber) {
                feedbackLabel.setText("⬇️ Too Low... Try Again!");
                feedbackLabel.setBackground(new Color(187, 222, 251)); // Light blue
            } else if (userGuess > targetNumber) {
                feedbackLabel.setText("⬆️ Too High... Try Again!");
                feedbackLabel.setBackground(new Color(255, 205, 210)); // Light red
            } else {
                feedbackLabel.setText("✅ Correct! You guessed it!");
                feedbackLabel.setBackground(new Color(200, 230, 201)); // Light green
                int score = maxAttempts - attempts;
                scoreLabel.setText("🎯 Your score: " + score + " (in " + attempts + " attempts)");
                guessButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("⚠️ Please enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}
