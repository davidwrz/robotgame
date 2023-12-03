package au.entsia;

import au.entsia.game.Game;
import au.entsia.robot.Robot;
import au.entsia.input.InputReader;

public class StartGame {
    public static void main(String[] args) {
        Game game = new Game(new Robot(),new InputReader());
        game.play();
    }
}
