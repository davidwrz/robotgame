package au.entsia.game;

import au.entsia.robot.Robot;
import au.entsia.input.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameTest {

    private Robot robot;
    private InputReader inputReader;
    private Game game;

    @BeforeEach
    void init() {
        robot = mock(Robot.class);
        inputReader = mock(InputReader.class);
        game = new Game(robot,inputReader);
    }

    @Test
    void shouldPlaceRobotWhenTriggeredPlaceInGame() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 1,1,NORTH")
                .thenReturn("EXIT");

        when(robot.getHorizontalCoordinate()).thenReturn(1);
        when(robot.getVerticalCoordinate()).thenReturn(1);
        when(robot.getFacing()).thenReturn("NORTH");
        game.placeRobot("PLACE 1,1,NORTH");

        // then
        verify(robot).place(1, 1, "NORTH");
    }

    @Test
    void shouldNotPlaceRobotWhenPlaceInvalid() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE -1,1,NORTH")
                .thenReturn("EXIT");

        when(robot.getHorizontalCoordinate()).thenReturn(-1);
        when(robot.getVerticalCoordinate()).thenReturn(1);
        when(robot.getFacing()).thenReturn("NORTH");
        game.placeRobot("PLACE -1,1,NORTH");

        // then
        verify(robot,never()).place(anyInt(), anyInt(), anyString());
    }

    @Test
    void shouldMoveRobotWhenTriggeredMoveInGame() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 1,1,NORTH")
                .thenReturn("MOVE")
                .thenReturn("EXIT");

        when(robot.getHorizontalCoordinate()).thenReturn(1);
        when(robot.getVerticalCoordinate()).thenReturn(1);
        when(robot.getFacing()).thenReturn("NORTH");
        game.moveRobot();

        // then
        verify(robot).move();
    }

    @Test
    void shouldNotMoveRobotWhenPlaceInvalid() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 0,0,SOUTH")
                .thenReturn("MOVE")
                .thenReturn("EXIT");

        when(robot.getHorizontalCoordinate()).thenReturn(0);
        when(robot.getVerticalCoordinate()).thenReturn(0);
        when(robot.getFacing()).thenReturn("SOUTH");
        game.moveRobot();

        // then
        verify(robot, never()).move();
    }

    @Test
    void shouldRotateLeftRobotWhenTriggeredRotateInGame() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 1,1,NORTH")
                .thenReturn("LEFT")
                .thenReturn("EXIT");

        when(robot.getHorizontalCoordinate()).thenReturn(1);
        when(robot.getVerticalCoordinate()).thenReturn(1);
        when(robot.getFacing()).thenReturn("NORTH");
        game.rotateRobot(RotateOrder.LEFT);

        // then
        verify(robot).rotateLeft();
    }

    @Test
    void shouldRotateRightRobotWhenTriggeredRotateInGame() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 1,1,NORTH")
                .thenReturn("RIGHT")
                .thenReturn("EXIT");

        when(robot.getHorizontalCoordinate()).thenReturn(1);
        when(robot.getVerticalCoordinate()).thenReturn(1);
        when(robot.getFacing()).thenReturn("NORTH");
        game.rotateRobot(RotateOrder.RIGHT);

        // then
        verify(robot).rotateRight();
    }
}