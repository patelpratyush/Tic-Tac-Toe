import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tic_Tac_Toe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;
    private int xScore = 0;
    private int oScore = 0;
    private JLabel scoreLabel;

    public Tic_Tac_Toe() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        panel.add(resetButton);

        scoreLabel = new JLabel("Score: X - " + xScore + " O - " + oScore);
        panel.add(scoreLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (xTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        button.setEnabled(false);
        xTurn = !xTurn;

        checkForWinner();
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                updateScore(buttons[i].getText());
                resetGame();
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                updateScore(buttons[i].getText());
                resetGame();
                return;
            }
        }

        // Check diagonals
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].isEnabled()) {
            JOptionPane.showMessageDialog(frame, buttons[0].getText() + " wins!");
            updateScore(buttons[0].getText());
            resetGame();
            return;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].isEnabled()) {
            JOptionPane.showMessageDialog(frame, buttons[2].getText() + " wins!");
            updateScore(buttons[2].getText());
            resetGame();
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].isEnabled()) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(frame, "Tie game!");
            resetGame();
        }
    }

    public void updateScore(String winner) {
        if (winner.equals("X")) {
            xScore++;
        } else {
            oScore++;
        }
        scoreLabel.setText("Score: X - " + xScore + " O - " + oScore);
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        new Tic_Tac_Toe();
    }
}
