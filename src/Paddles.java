import java.awt.*;
import java.awt.event.*;

public class Paddles extends Rectangle{
    int playerNum;
    int yVelocity;
    int speed = 10; //speed that paddles move up or down

    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT); //creates the paddles within the rectangle class with the specified parameters defined in the mainPanel class
        this.playerNum = id;
    }
    public void keyPressed(KeyEvent e){
        switch (playerNum) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed); //Moves the left paddle upwards when W key is pressed
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed); //Moves the paddle left downwards when S key is pressed
                    move();
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed); //Moves the paddle upwards
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed); //Moves the paddle downwards
                    move();
                }
            }
        }
    }
    public void keyReleased(KeyEvent e){
        switch (playerNum) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0); //Stops moving the paddle when the key is released
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);  //Stops moving the paddle when the key is released
                    move();
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);  //Stops moving when the key is released
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0); //Stops moving when the key is released
                    move();
                }
            }
        }
    }
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }
    public void move(){
        y += yVelocity;
    }
    public void draw(Graphics g){
        if (playerNum == 1){
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g.fillRect(x, y, width, height);
    }
}