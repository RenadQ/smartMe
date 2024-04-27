package seashellfinaalgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class HomePage {
   public JFrame frame;
    public JTextField nameField;
    public JComboBox<String> levelComboBox;
    public JFrame flipGameFrame;
    public JFrame seashellFrame;
    public LinkedList<String> nameStack;



    public HomePage() {
        frame = new JFrame("Home Page");
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create an ImageIcon with the SmartMe Logo image
        ImageIcon logoIcon = new ImageIcon("smartme_logo.png");

        // Create a JLabel to display the image
        JLabel logoLabel = new JLabel(logoIcon);
        frame.add(logoLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 1));

        JLabel welcomeLabel = new JLabel("Welcome to SmartMe");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.BLUE);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(welcomeLabel);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(10);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        centerPanel.add(namePanel);

        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new FlowLayout());
        JLabel levelLabel = new JLabel("Choose a level:");
        levelComboBox = new JComboBox<>(new String[]{"Level 1", "Level 2"});
        levelPanel.add(levelLabel);
        levelPanel.add(levelComboBox);
        centerPanel.add(levelPanel);

        JPanel levelInfoPanel = new JPanel();
        levelInfoPanel.setLayout(new FlowLayout());
        JLabel levelInfoLabel = new JLabel();
        levelInfoPanel.add(levelInfoLabel);
        centerPanel.add(levelInfoPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton nextButton = new JButton("Start");
        JButton aboutButton = new JButton("About Us");
        buttonPanel.add(nextButton);
        buttonPanel.add(aboutButton);
        centerPanel.add(buttonPanel);

        frame.add(centerPanel, BorderLayout.CENTER);
        nextButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String selectedLevel = (String) levelComboBox.getSelectedItem();
        if (selectedLevel != null) {
            frame.dispose(); // Close the current frame

            // Check if the name exists in the stack
            String name = nameField.getText().trim();
            if (!name.isEmpty() && nameStack.contains(name)) {
                JOptionPane.showMessageDialog(frame, "Welcome back, " + name + "!", "Welcome", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("star_icon.png"));
            }

            // Delay in milliseconds (adjust as needed)
            int delay = 100; // 2 seconds

            // Create a timer with the specified delay
            Timer timer = new Timer(delay, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (selectedLevel.equals("Level 1")) {
                        showFlipGameFrame();
                    } else if (selectedLevel.equals("Level 2")) {
                        showSeashellFrame();
                    }
                }
            });

            // Start the timer
            timer.setRepeats(false); // Only execute once
            timer.start();
        }
    }
});

aboutButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Check if the name exists in the stack
        String name = nameField.getText().trim();
        if (!name.isEmpty() && nameStack.contains(name)) {
            JOptionPane.showMessageDialog(frame, "Welcome back, " + name + "!", "Welcome", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("star_icon.png"));
        }

        // Delay in milliseconds (adjust as needed)
        int delay = 100; // 2 seconds

        // Create a timer with the specified delay
        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAboutUsPage();
            }
        });

        // Start the timer
        timer.setRepeats(false); // Only execute once
        timer.start();
    }
});

// Set the level information based on the selected level
levelComboBox.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String selectedLevel = (String) levelComboBox.getSelectedItem();
        if (selectedLevel != null) {
            if (selectedLevel.equals("Level 1")) {
                levelInfoLabel.setText("Level 1: Flip matching card");
            } else if (selectedLevel.equals("Level 2")) {
                levelInfoLabel.setText("Level 2: Sea shell memory game");
            }
        }
    }
});


        // Initialize the name stack
        nameStack = new LinkedList<>();
        nameStack.push("waad");
        nameStack.push("maid");
        nameStack.push("zahar");
        nameStack.push("lama");
        nameStack.push("amjad");
        nameStack.push("enas");
        
        // Add ActionListener to store the name in the stack
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    nameStack.push(name);
                }
            }
        });
       
       

        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }
 


    public void showFlipGameFrame() {
    FlipMatchCardGame gameComponent = new FlipMatchCardGame();
    gameComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gameComponent.setSize(500, 500);
    gameComponent.setLocationRelativeTo(null);

    gameComponent.setVisible(true);
}


public void showSeashellFrame() {
    Frame seashellFrame = new Frame("Seashell");
    seashellFrame.setSize(600, 650);
    seashellFrame.setResizable(false);
    seashellFrame.setLocationRelativeTo(null);
    seashellFrame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    });

    ShellGame game = new ShellGame(); // Assuming ShellGame extends Panel

    seashellFrame.add(game.panel);
     seashellFrame.setLocationRelativeTo(null); // Center the window on the screen
    seashellFrame.setVisible(true);
}


private void showAboutUsPage() {

   frame.getContentPane().removeAll();
    frame.repaint();

    ImageIcon logoIcon = new ImageIcon("smartme_logo.png");

    // Create a JLabel to display the image
    JLabel logoLabel = new JLabel(logoIcon);
    frame.add(logoLabel, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(7, 1));
    JPanel aboutPanel = new JPanel();
    aboutPanel.setLayout(new BorderLayout());

    JTextArea aboutText = new JTextArea("We are SmartUr: Application Made By Many Students,\n Made To Help People Improving Their Memory Abilities\n our team : \n Renad \nZahraa \n Lama \n Amjad \n hope you like it :) ");
    aboutText.setEditable(false);
    aboutText.setBackground(frame.getBackground());
    aboutText.setForeground(Color.BLUE);
    aboutText.setLineWrap(true);
    aboutText.setWrapStyleWord(true);
    aboutText.setFont(new Font("Arial", Font.PLAIN, 16));
    aboutText.setAlignmentY(Component.CENTER_ALIGNMENT);  // Align text vertically in the center of the JTextArea
    aboutText.setAlignmentX(Component.CENTER_ALIGNMENT);  // Align text horizontally in the center of the JTextArea
    JScrollPane scrollPane = new JScrollPane(aboutText);
    aboutPanel.add(scrollPane, BorderLayout.CENTER);

    JButton backButton = new JButton("Back");
    backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            showHomePage(); // Add the components of the home page directly to the existing frame
        }
    });
    aboutPanel.add(backButton, BorderLayout.WEST);
    
    // Remove the existing components from the frame
    frame.getContentPane().removeAll();

    // Add the components of the about page to the frame
    frame.add(logoLabel, BorderLayout.NORTH);
    frame.add(aboutPanel, BorderLayout.CENTER);
    
    frame.revalidate();
    frame.repaint();
    frame.setVisible(true);

}

private void showHomePage() {
    frame.getContentPane().removeAll();

    HomePage homePage = new HomePage();
    JPanel mainPanel = homePage.getMainPanel(frame);

    frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
    frame.revalidate();
}
//
//    public JPanel getMainPanel() {
//        return (JPanel) frame.getContentPane();
//    }
public JPanel getMainPanel(JFrame frame) {
    JPanel mainPanel = new JPanel(new BorderLayout());

    // Add your home page components to the mainPanel
    // For example:
    JLabel welcomeLabel = new JLabel("Welcome to SmartMe");
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
    welcomeLabel.setForeground(Color.BLUE);
    welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
    mainPanel.add(welcomeLabel, BorderLayout.CENTER);

    return mainPanel;
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage();
            }
        });
    }
}