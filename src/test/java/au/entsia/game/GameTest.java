package au.entsia.game;

import au.entsia.robot.Robot;
import au.entsia.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameTest {

    private Robot robot;
    private InputReader inputReader;

    @BeforeEach
    void init() {
        robot = mock(Robot.class);
        inputReader = mock(InputReader.class);
    }

    @Test
    void shouldMoveRobotWhenTriggeredMoveInGame() {
        // given
        when(inputReader.getInput()).thenReturn("PLACE 1,1,NORTH").thenReturn("MOVE").thenReturn("EXIT");
        Game game = new Game(inputReader);
        // when
        when(robot.getHorizontalCoordinate()).thenReturn(1);
        when(robot.getVerticalCoordinate()).thenReturn(1);
        when(robot.getFacing()).thenReturn("NORTH");
        game.setRobot(robot);
        game.moveRobot();
        // then
        verify(robot).move();
    }

    @Test
    void shouldNotMoveRobotWhenPositionNotValid() {
        // given
        when(inputReader.getInput()).thenReturn("PLACE 0,0,SOUTH").thenReturn("MOVE").thenReturn("EXIT");
        Game game = new Game(inputReader);
        // when
        when(robot.getHorizontalCoordinate()).thenReturn(0);
        when(robot.getVerticalCoordinate()).thenReturn(0);
        when(robot.getFacing()).thenReturn("SOUTH");
        game.setRobot(robot);
        game.moveRobot();
        // then
        verify(robot, never()).move();
    }
}