
package seashellfinaalgame;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.Timer ;
import java.awt.*;
//import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.awt.event.ActionListener ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


//down its perfection
public class FlipMatchCardGame extends JFrame {
    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 150;
    private static final int NUM_CARDS = 20;
    private static final int MAX_MATCHES = 20;
    private static final int MAX_TIME_SECONDS = 80;

    private List<ImageIcon> cardImages;
    private Stack<ImageIcon> cardStack;
    private List<JButton> cardButtons;
    private JButton selectedButton;
    private int numMatches;
    private Timer timer;
    private int elapsedTime;
    private JLabel timerLabel;
    private JLabel scoreLabel;
    private boolean isMatching;

    public FlipMatchCardGame() {
        setTitle("Flip-Match Card Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(4, 5));

        cardImages = new ArrayList<>();
        cardStack = new Stack<>();
        cardButtons = new ArrayList<>();
        selectedButton = null;
        numMatches = 0;
        elapsedTime = 0;

        for (int i = 1; i <= NUM_CARDS / 2; i++) {
            ImageIcon image = new ImageIcon("pic" + i + ".png");
            cardImages.add(image);
            cardStack.push(image);
            cardImages.add(image);
            cardStack.push(image);
        }

        Collections.shuffle(cardImages);

        for (int i = 0; i < NUM_CARDS; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            button.addActionListener(new CardButtonListener());
            cardButtons.add(button);
            cardsPanel.add(button);
        }

        timerLabel = new JLabel("Time: 0", SwingConstants.CENTER);
        timerLabel.setFont(new Font(timerLabel.getFont().getName(), Font.PLAIN, 24));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(timerLabel, BorderLayout.CENTER);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.PLAIN, 24));

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.add(scoreLabel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(cardsPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);

    setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
        
        resetGame();

        isMatching = false;
    }

    private class CardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();



            int index = cardButtons.indexOf(button);
            ImageIcon image = cardImages.get(index);

            button.setIcon(image);
            button.setEnabled(true);

            if (selectedButton == null) {
                selectedButton = button;
            } else {
                isMatching = true;
                setEnabledAllButtons(true);

                int selectedIndex = cardButtons.indexOf(selectedButton);
                ImageIcon selectedImage = cardImages.get(selectedIndex);

                if (image == selectedImage) {
                    numMatches++;
                    
                    if (numMatches == MAX_MATCHES) {
                        timer.stop();
                        showGameOver();
                    }
                    selectedButton = null;
                    setEnabledAllButtons(true);
                    updateScore(10);} 
                    else {
                    Timer delayTimer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selectedButton.setIcon(null);
                            selectedButton.setEnabled(true);
                            button.setIcon(null);
                            button.setEnabled(true);
                            selectedButton = null;
                            setEnabledAllButtons(true);
                            
                            isMatching = false;
                        }
                    });

                    delayTimer.setRepeats(false);
                    delayTimer.start();
                }
            }
        }

        private void exitGame() {
            int score = Integer.parseInt(scoreLabel.getText().substring(7));
          //int score = Integer.parseInt(scoreLabel.getText().substring(7));
        String message = "Congratulations! You completed the game in " + elapsedTime + " seconds.\n";
        message += "Your score is: " + score;

        int option = JOptionPane.showConfirmDialog(null, message + "\n\nDo you want to play again?", "GameOver", JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }   
        }
    }

    private void setEnabledAllButtons(boolean enabled) {
        for (JButton button : cardButtons) {
            if (button.isEnabled()) {
                button.setEnabled(enabled);
            }
        }
    }

    private void showGameOver() {
        int score = Integer.parseInt(scoreLabel.getText().substring(7));
        String message = "Congratulations! You completed the game in " + elapsedTime + " seconds.\n";
        message += "Your score is: " + score;

        int option = JOptionPane.showConfirmDialog(null, message + "\n\nDo you want to play again?", "GameOver", JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }


private void updateScore(int amount) {
    int currentScore = Integer.parseInt(scoreLabel.getText().substring(7));
    int newScore = currentScore + amount;
    scoreLabel.setText("Score: " + newScore);

    if (newScore >= 100) {
        showGameOver();
    }
}
    private void updateTimer() {
        elapsedTime++;
        timerLabel.setText("Time: " + elapsedTime);

        if (elapsedTime >= MAX_TIME_SECONDS) {
            timer.stop();
            showGameOver();
        }
    }

    private void resetGame() {
        numMatches = 0;
        elapsedTime = 0;
        selectedButton = null;
        isMatching = false;
        setEnabledAllButtons(false);
        timerLabel.setText("Time: 0");
        scoreLabel.setText("Score: 0");

        Collections.shuffle(cardImages);

        for (int i = 0; i < NUM_CARDS; i++) {
            JButton button = cardButtons.get(i);
            button.setIcon(null);
            button.setEnabled(true);
        }

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlipMatchCardGame();
            }
        });
    }
}