package au.entsia.game;

import org.junit.jupiter.api.Test;

import static au.entsia.game.PositionValidator.isRobotPositionValid;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionValidatorTest {

    @Test
    void shouldReturnTrueWhenPositionIsOnBoard() {
        //given
        int x = 0;
        int y = 0;
        int horizontalSize = 5;
        int verticalSize = 5;
        //when
        boolean validationResult = isRobotPositionValid(x, y, horizontalSize, verticalSize);
        //then
        assertTrue(validationResult);
    }

    @Test
    void shouldReturnFalseWhenPositionIsOutOfBoard() {
        //given
        int x = 6;
        int y = 5;
        int horizontalSize = 5;
        int verticalSize = 5;
        //when
        boolean validationResult = isRobotPositionValid(x, y, horizontalSize, verticalSize);
        //then
        assertTrue(validationResult);
    }
}