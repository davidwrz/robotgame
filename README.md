# Toy Robot

## Description

The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units. There are
no other obstructions on the table surface.
The robot is free to roam around the surface of the table but is be prevented from falling to destruction. Any movement
that would result in the robot falling from the table is prevented, however further valid movement commands is still
allowed.

## Building the Application

Use the following command to build the application:

```mvn clean install```

## Running the Application in an IDE

To run the application in your IDE, simply locate the class with the `main` method and execute it by right-clicking and
choosing "Run" or "Debug."

### [StartGame.java](src/main/java/au/entsia/StartGame.java)

```java
public class StartGame {
    public static void main(String[] args) {
        Game game = new Game(new Robot(), new InputReader());
        game.play();
    }
}
```

## Running Tests

Execute the following command to run the tests:

```mvn test```

