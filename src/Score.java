import java.awt.*;

public class Score extends Rectangle {
    int player1; //Keeps track of the score of player 1
    int player2; //Keeps track of the score of player 2
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(@NotNull Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 60)); //Sets font of the numbers
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
        g.drawString(String.valueOf(player1 / 10) + player1 % 10, (GAME_WIDTH / 2) - 85, 50);
        g.drawString(String.valueOf(player2 / 10) + player2 % 10, (GAME_WIDTH / 2) + 20, 50);
        g.drawString("Player 1", 100, 100);
        g.drawString("Player 2", 750, 100);
        //shows a 2 digit scoreboard and displays this at the top of the screen
    }
}
