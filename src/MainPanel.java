import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

@SuppressWarnings("InfiniteLoopStatement")
public class MainPanel extends JPanel implements Runnable { //Runnable means that there is a thread that needs to run
    static final int GAME_WIDTH = 1000; //Makes sure the width of the game is not modifiable
    static final int GAME_HEIGHT = 550; //Ping pong tables are usually more wide than tall
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT); //Creates the dimensions of the screen
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread thread; //Allows multiple lines of code to run at the same time
    Image image;
    Graphics graphics;
    Random random;
    Ball ball;
    Paddles paddle1;
    Paddles paddle2;
    Score score;

    MainPanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true); //Focuses on the JPanel
        this.addKeyListener(new AL()); //Allows key presses to be read
        this.setPreferredSize(SCREEN_SIZE);

        thread = new Thread(this); //Initiates a new thread on the JPanel
        thread.start(); //begins execution of the thread below so all the methods execute concurrently
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH) / 2 - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
        //Takes x position, y position, ball width, and ball height as parameters
    }

    public void newPaddles() {
        paddle1 = new Paddles(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddles(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
        //Takes x position, y position, paddle width, paddle height and id as parameters
    }

    public void paint(@NotNull Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g); //Creates the paddle1 image
        paddle2.draw(g); //Creates the paddle2 image
        ball.draw(g); //Creates the ball
        score.draw(g); //Shows score
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        //bounce ball of the top and bottom window edges
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //Bounces the ball off the paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional: can be incremented by a higher value
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //increases ball velocity after the ball is hit
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //can be incremented by a higher value
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //can be incremented by a higher value
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //stop paddles at window edges, so they don't go outside the window
        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        //gives player point then creates new paddles & ball
        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2:" + score.player2);
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1:" + score.player1);
        }
    }

    public void run() {
        //game loop

        /*
        Game loop source code citation
        Author: Martin Cruz
        Date: 2015
        Availability: http://www.github.com
        */
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        do {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        } while (true);
    }

    public class AL extends KeyAdapter { //KeyAdapter receives keystrokes, such as pressing a key
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
