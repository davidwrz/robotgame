package au.entsia.game;

class PositionValidator {

    public static boolean isRobotPositionValid(int x, int y) {
        return x >= 0 && y >= 0 && x <= GameRules.HORIZONTAL_SIZE && y <= GameRules.VERTICAL_SIZE;
    }
}
