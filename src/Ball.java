import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle{
    Random random;
    int xVelocity; //How fast the ball moves horizontally
    int yVelocity; //How fast the ball moves vertically
    int initialSpeed = 2;

    Ball(int x, int y, int width, int height){
        super(x, y, width, height); //Gets values from MainPanel
        random = new Random();
        int randomXDirection = random.nextInt(2); //is either 0 or 1, so ball moves either left or right
        if (randomXDirection == 0){
            randomXDirection--;
        }
        setXDirection(randomXDirection * initialSpeed);
        int randomYDirection = random.nextInt(2); //Either 0 or 1, so ball moves either up or down
        if (randomYDirection == 0){
            randomYDirection--;
        }
        setYDirection(randomYDirection * initialSpeed);
    }
    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        //creates the ball image
        g.setColor(Color.BLUE);
        g.fillOval(x, y, width, height); //Makes the ball a circle instead of rectangle
    }
}
