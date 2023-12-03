package au.entsia.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static au.entsia.game.PositionValidator.isRobotPositionValid;
import static org.junit.jupiter.api.Assertions.*;

class PositionValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",   // On the board
            "6, 5, false",  // Out of bounds in X
            "4, 6, false",  // Out of bounds in Y
            "-1, 3, false", // Negative X
            "3, -1, false"  // Negative Y
    })
    void shouldValidateRobotPosition(int x, int y,  boolean expectedResult) {
        // when
        boolean validationResult = isRobotPositionValid(x, y);
        // then
        assertEquals(expectedResult, validationResult);
    }
}