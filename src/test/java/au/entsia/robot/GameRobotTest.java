package au.entsia.robot;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameRobotTest {

    @ParameterizedTest
    @CsvSource({
            "1, 2, NORTH, NORTH",
            "3, 4, SOUTH, SOUTH",
            "5, 6, EAST, EAST",
            "7, 8, WEST, WEST"
    })
    void testPlace(int horizontal, int vertical, String facing, String expectedFacing) {
        // given
        GameRobot gameRobot = new GameRobot(0, 0, Facing.NORTH.toString());

        // when
        gameRobot.place(horizontal, vertical, facing);

        // then
        assertEquals(horizontal, gameRobot.getHorizontalCoordinate());
        assertEquals(vertical, gameRobot.getVerticalCoordinate());
        assertEquals(expectedFacing, gameRobot.getFacing());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, NORTH, 1, 3",
            "3, 4, SOUTH, 3, 3",
            "5, 6, EAST, 6, 6",
            "7, 8, WEST, 6, 8"
    })
    void testMove(int initialHorizontal, int initialVertical, String facing,
                  int expectedHorizontal, int expectedVertical) {
        // given
        GameRobot gameRobot = new GameRobot(initialHorizontal, initialVertical, facing);

        // when
        gameRobot.move();

        // then
        assertEquals(expectedHorizontal, gameRobot.getHorizontalCoordinate());
        assertEquals(expectedVertical, gameRobot.getVerticalCoordinate());
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, EAST",
            "SOUTH, WEST",
            "EAST, SOUTH",
            "WEST, NORTH"
    })
    void testRotateRight(Facing initialFacing, Facing expectedFacing) {
        // given
        GameRobot gameRobot = new GameRobot(0, 0, initialFacing.toString());

        // when
        gameRobot.rotateRight();

        // then
        assertEquals(expectedFacing, Facing.valueOf(gameRobot.getFacing()));
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, WEST",
            "SOUTH, EAST",
            "EAST, NORTH",
            "WEST, SOUTH"
    })
    void testRotateLeft(Facing initialFacing, Facing expectedFacing) {
        // given
        GameRobot gameRobot = new GameRobot(0, 0, initialFacing.toString());

        // when
        gameRobot.rotateLeft();

        // then
        assertEquals(expectedFacing, Facing.valueOf(gameRobot.getFacing()));
    }
}