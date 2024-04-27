package seashellfinaalgame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.awt.BorderLayout;

public class ShellGame extends JFrame {
    //an array of icons for the shells
    private ArrayList<ImageIcon> shells = new ArrayList<>(Arrays.asList(
            new ImageIcon(getClass().getResource("1.png")),
            new ImageIcon(getClass().getResource("2.png")),
            new ImageIcon(getClass().getResource("3.png")),
            new ImageIcon(getClass().getResource("4.png")),
            new ImageIcon(getClass().getResource("5.png")),
            new ImageIcon(getClass().getResource("6.png")),
            new ImageIcon(getClass().getResource("7.png")),
            new ImageIcon(getClass().getResource("8.png")),
            new ImageIcon(getClass().getResource("9.png")),
            new ImageIcon(getClass().getResource("10.png")),
            new ImageIcon(getClass().getResource("11.png")),
            new ImageIcon(getClass().getResource("12.png")),
            new ImageIcon(getClass().getResource("13.png")),
            new ImageIcon(getClass().getResource("14.png")),
            new ImageIcon(getClass().getResource("15.png")),
            new ImageIcon(getClass().getResource("16.png")),
            new ImageIcon(getClass().getResource("17.png")),
            new ImageIcon(getClass().getResource("18.png")),
            new ImageIcon(getClass().getResource("19.png")),
            new ImageIcon(getClass().getResource("20.png")),
            new ImageIcon(getClass().getResource("21.png")),
            new ImageIcon(getClass().getResource("22.png")),
            new ImageIcon(getClass().getResource("23.png"))
    ));



    private stack selctedshells = new stack();
    public JPanel panel ; //container for components

    public ShellGame(){
        super("sea shell memory game ");
        panel = new JPanel(new GridLayout(3, 3, 10, 10)); //change if needed
        add(panel);
        displayrandom();
    }
    public void displayrandom(){
        //clear prev buttons
        panel.removeAll();
        Collections.shuffle(shells); //shuffle -> pick randomly
        //since i want to randomly display 5 icons
        for(int i = 0 ; i < 9 ; i++){
            ImageIcon shell = shells.get(i);
            JButton button = new JButton(shell);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButton(shell);
                }//end method
            });//end add event & anonymous class
            panel.add(button);
        }//end for-loop
        //bc we are dynamically modifying the component
        panel.revalidate(); //trigger container to redo layout
        panel.repaint(); //to update appearance to fit changes
    }//end display method



    private void handleButton(ImageIcon shell) {
        displayrandom();
        if (selctedshells.playerLose(shell)) {
            JOptionPane.showMessageDialog(this, "GAME OVER you loser ", "GAME OVER", JOptionPane.ERROR_MESSAGE);
            //restartGame();
        }
        else if (!selctedshells.playerLose(shell)) {
            selctedshells.push(shell);
            //JOptionPane.showMessageDialog(this, "GAME OVER you loser loser", "GAME OVER", JOptionPane.ERROR_MESSAGE);
            //restartGame();
        }
    }

}

class stack extends Component {

    private class node {
        ImageIcon shell;
        node next;

        node(ImageIcon shell) {
            this.shell = shell;
            this.next = null;
        }
    }
    private node top ;

    public stack (){
        this.top = null;

    }

    public void push(ImageIcon shell) {
        node newNode = new node(shell);
        newNode.next = top;
        top = newNode;
    }

    public boolean playerLose(ImageIcon shell) {
        node newNode = new node(shell);
        newNode.next = top;
        node current = top;
        while (current != null) {
            if (current.shell.equals(shell)) {
                return true;  // Found a duplicate, game over
            }
            current = current.next;
        }
        top = newNode; // No duplicate found, add to stack
        return false; // Continue the game
    }


}


