package au.entsia.robot;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    @ParameterizedTest
    @CsvSource({
            "1, 2, NORTH, NORTH",
            "3, 4, SOUTH, SOUTH",
            "5, 6, EAST, EAST",
            "7, 8, WEST, WEST"
    })
    void testPlace(int horizontal, int vertical, String facing, String expectedFacing) {
        // given
        Robot robot = new Robot();
        robot.place(0, 0, facing);

        // when
        robot.place(horizontal, vertical, facing);

        // then
        assertEquals(horizontal, robot.getHorizontalCoordinate());
        assertEquals(vertical, robot.getVerticalCoordinate());
        assertEquals(expectedFacing, robot.getFacing());
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
        Robot robot = new Robot();
        robot.place(initialHorizontal, initialVertical, facing);

        // when
        robot.move();

        // then
        assertEquals(expectedHorizontal, robot.getHorizontalCoordinate());
        assertEquals(expectedVertical, robot.getVerticalCoordinate());
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
        Robot robot = new Robot();
        robot.place(0, 0, initialFacing.toString());

        // when
        robot.rotateRight();

        // then
        assertEquals(expectedFacing, Facing.valueOf(robot.getFacing()));
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
        Robot robot = new Robot();
        robot.place(0, 0, initialFacing.toString());

        // when
        robot.rotateLeft();

        // then
        assertEquals(expectedFacing, Facing.valueOf(robot.getFacing()));
    }
}