package au.entsia.game;

import au.entsia.robot.GameRobot;
import au.entsia.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameTest {

    private GameRobot gameRobot;
    private InputReader inputReader;

    @BeforeEach
    void init() {
        gameRobot = mock(GameRobot.class);
        inputReader = mock(InputReader.class);
    }

    @Test
    void shouldMoveRobotWhenTriggeredMoveInGame() {
        // given
        when(inputReader.getInput()).thenReturn("PLACE 1,1,NORTH").thenReturn("MOVE").thenReturn("EXIT");
        Game game = new Game(inputReader);
        // when
        when(gameRobot.getHorizontalCoordinate()).thenReturn(1);
        when(gameRobot.getVerticalCoordinate()).thenReturn(1);
        when(gameRobot.getFacing()).thenReturn("NORTH");
        game.setRobot(gameRobot);
        game.moveRobot();
        // then
        verify(gameRobot).move();
    }
}