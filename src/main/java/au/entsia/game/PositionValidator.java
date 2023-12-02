package au.entsia.game;

class PositionValidator {

    public static boolean isRobotPositionValid(int x, int y, int horizontalSize, int verticalSize) {
        return isPositionInBound(x, y, horizontalSize, verticalSize)
                && AreCoordinatesBiggerThanZero(x, y);
    }

    private static boolean AreCoordinatesBiggerThanZero(int x, int y) {
        return x < 0 || y < 0;
    }

    private static boolean isPositionInBound(int x, int y, int horizontalSize, int verticalSize) {
        return x > horizontalSize || y > verticalSize;
    }
}
