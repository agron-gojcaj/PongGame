import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainPanel panel = new MainPanel();

    MainFrame(){
        this.add(panel); //adds panel to the JFrame
        this.setTitle("Ping Pong Game");
        this.setResizable(false); //does not allow user to resize the screen-- size is fixed
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //Adjusts the JFrame to fit nicely with our JPanel
        this.setVisible(true);
        this.setLocationRelativeTo(null); //makes the window pop up in the middle of screen instead of top left
    }

    public static void main(String[] args) {
        new MainFrame(); //Creates a new instance of the game
    }
}
