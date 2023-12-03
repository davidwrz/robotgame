package au.entsia.game;

class PositionValidator {

    public static boolean isRobotPositionValid(int x, int y, int horizontalSize, int verticalSize) {
        if (x <0 || y < 0) {
            return false;
        } if (x > horizontalSize || y > verticalSize) {
            return false;
        }
        return true;
//        return isPositionInBound(x, y, horizontalSize, verticalSize) && areCoordinatesBiggerOrEqualThanZero(x, y);
    }

    private static boolean areCoordinatesBiggerOrEqualThanZero(int x, int y) {
        return x < 0 || y < 0;
    }

    private static boolean isPositionInBound(int x, int y, int horizontalSize, int verticalSize) {
        return x > horizontalSize || y > verticalSize;
    }
}
