package au.entsia.game;

import au.entsia.input.InputReader;
import au.entsia.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IntegrationTest {

    private InputReader inputReader;
    private Game game;

    @BeforeEach
    void init() {
        inputReader = mock(InputReader.class);
        game = new Game(new Robot(), inputReader);
    }

    @Test
    @DisplayName("Place -> Move -> Report")
    void exampleA() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("MOVE")
                .thenReturn("REPORT")
                .thenReturn("EXIT");

        game.play();

        // then
        String result = game.printOutput();
        assertEquals("Output: 0,1,NORTH", result);
    }

    @Test
    @DisplayName("Place -> Left -> Report")
    void exampleB() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 0,0,NORTH")
                .thenReturn("LEFT")
                .thenReturn("REPORT")
                .thenReturn("EXIT");

        game.play();

        // then
        String result = game.printOutput();
        assertEquals("Output: 0,0,WEST", result);
    }

    @Test
    @DisplayName("Place -> Move -> Move -> Left -> Move -> Report")
    void exampleC() {
        // given
        // when
        when(inputReader.getInput())
                .thenReturn("PLACE 1,2,EAST")
                .thenReturn("MOVE")
                .thenReturn("MOVE")
                .thenReturn("LEFT")
                .thenReturn("MOVE")
                .thenReturn("REPORT")
                .thenReturn("EXIT");

        game.play();

        // then
        String result = game.printOutput();
        assertEquals("Output: 3,3,NORTH", result);
    }
}
