package au.entsia.game;

import au.entsia.robot.Robot;
import au.entsia.util.InputReader;

import java.util.logging.Logger;

import static au.entsia.game.PositionValidator.isRobotPositionValid;

public class Game {

    private static final String PLACE_COMMAND = "PLACE";
    private final InputReader inputReader;
    private final Logger logger;
    private Robot robot;
    private boolean gameRunning = true;

    public Game(InputReader inputReader) {
        this.inputReader = inputReader;
        logger = Logger.getLogger(Game.class.getName());
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void play() {
        triggerPlacingRobot();

        while (gameRunning) {
            String command = inputReader.getInput();
            if (command.startsWith("PLACE ")) {
                placeRobot(command);
            } else {
                switch (command) {
                    case "MOVE" -> moveRobot();
                    case "RIGHT" -> rotateRobot(RotateOrder.RIGHT);
                    case "LEFT" -> rotateRobot(RotateOrder.LEFT);
                    case "REPORT" -> printOutput();
                    case "EXIT" -> exitGame();
                    default -> logger.info(String.format("Unknown command: %s", command));
                }
            }
        }
    }

    private void triggerPlacingRobot() {
        String input;
        while (true) {
            input = inputReader.getInput();
            if (input.contains(PLACE_COMMAND)) {
                String[] coordinates = getCoordinates(input);
                int horizontalCoordinate = Integer.parseInt(coordinates[0]);
                int verticalCoordinate = Integer.parseInt(coordinates[1]);
                String facing = coordinates[2];
                if (isRobotPositionValid(horizontalCoordinate, verticalCoordinate)) {
                    createRobot(horizontalCoordinate, verticalCoordinate, facing);
                    logger.info("Robot initialized");
                    return;
                }
            }
        }
    }

    void placeRobot(String input) {
        String[] coordinates = getCoordinates(input);
        int horizontalCoordinate = Integer.parseInt(coordinates[0]);
        int verticalCoordinate = Integer.parseInt(coordinates[1]);
        String facing = coordinates[2];
        robot.place(horizontalCoordinate, verticalCoordinate, facing);
        logger.info(String.format("Robot placed: %d,%d,%s",
                robot.getHorizontalCoordinate(),
                robot.getVerticalCoordinate(),
                robot.getFacing()));
    }

    void moveRobot() {
        int horizontalCoordinate = robot.getHorizontalCoordinate();
        int verticalCoordinate = robot.getVerticalCoordinate();
        String facing = robot.getFacing();
        switch (facing) {
            case "NORTH" -> verticalCoordinate++;
            case "SOUTH" -> verticalCoordinate--;
            case "EAST" -> horizontalCoordinate++;
            case "WEST" -> horizontalCoordinate--;
        }
        if (isRobotPositionValid(horizontalCoordinate, verticalCoordinate)) {
            robot.move();
            logger.info(String.format("Robot moved: %s", facing));
        }
    }

    void rotateRobot(RotateOrder direction) {
        if (direction.equals(RotateOrder.RIGHT)) {
            robot.rotateRight();
        } else {
            robot.rotateLeft();
        }
        logger.info(String.format("Robot rotated: %s", direction));
    }

    void printOutput() {
        String result = String.format("Output: %d,%d,%s",
                robot.getHorizontalCoordinate(),
                robot.getVerticalCoordinate(),
                robot.getFacing());
        System.out.println(result);
    }

    void exitGame() {
        gameRunning = false;
        inputReader.close();
    }

    private void createRobot(int horizontalCoordinate, int verticalCoordinate, String facing) {
        robot = new Robot(horizontalCoordinate, verticalCoordinate, facing);
    }

    private String[] getCoordinates(String place) {
        return place
                .split(" ")[1]
                .split(",");
    }
}
