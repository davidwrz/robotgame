package au.entsia.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static au.entsia.game.PositionValidator.isRobotPositionValid;
import static org.junit.jupiter.api.Assertions.*;

class PositionValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0, 5, 5, true",   // On the board
            "6, 5, 5, 5, false",  // Out of bounds in X
            "4, 6, 5, 5, false",  // Out of bounds in Y
            "-1, 3, 5, 5, false", // Negative X
            "3, -1, 5, 5, false"  // Negative Y
    })
    void shouldValidateRobotPosition(int x, int y, int horizontalSize, int verticalSize, boolean expectedResult) {
        // when
        boolean validationResult = isRobotPositionValid(x, y, horizontalSize, verticalSize);
        // then
        assertEquals(expectedResult, validationResult);
    }
}