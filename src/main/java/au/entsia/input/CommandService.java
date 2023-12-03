package au.entsia.input;

import static au.entsia.input.Command.*;

public class CommandService {
    public static Command getCommand(String input) {
        if (input.startsWith(PLACE.toString())) {
            return PLACE;
        } else {
            try {
                return valueOf(input);
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }
}
