package assign3;

/**
 * Created by Elisa on 2/24/2017.
 * Last Updated 3/5/2017
 */

public class RightHandRobot extends FacingRobot {

    private String type = "direction";
    private int north = 0;
    private int east = 1;
    private int south = 2;
    private int west = 3;

    /**
     * This method allows the RightHandRobot to see the maze class
     *
     * @param maze
     */
    public RightHandRobot(Maze maze) {
        super(maze);
    }

    /**
     * This method returns the type of robot
     *
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * This method chooses the direction the robot will move
     *
     * @return movement
     */
    @Override
    public int[] chooseMoveDirection() {
        int[] movement = new int[8];

        if (getFacing() == north) {
            movement[0] = -1;
            movement[1] = 0;
            movement[2] = 0;
            movement[3] = -1;
            movement[4] = 1;
            movement[5] = 0;
            movement[6] = 0;
            movement[7] = 1;
        } else if (getFacing() == east) {
            movement[0] = 0;
            movement[1] = -1;
            movement[2] = 1;
            movement[3] = 0;
            movement[4] = 0;
            movement[5] = 1;
            movement[6] = -1;
            movement[7] = 0;
        } else if (getFacing() == south) {
            movement[0] = 1;
            movement[1] = 0;
            movement[2] = 0;
            movement[3] = 1;
            movement[4] = -1;
            movement[5] = 0;
            movement[6] = 0;
            movement[7] = -1;
        } else if (getFacing() == west) {
            movement[0] = 0;
            movement[1] = 1;
            movement[2] = -1;
            movement[3] = 0;
            movement[4] = 0;
            movement[5] = -1;
            movement[6] = 1;
            movement[7] = 0;
        }

        return movement;
    }

    /**
     * This method moves the robot. It is overridden in the RandomRobot and RightHandRobot classes
     *
     * @param position
     * @return validMove
     */
    public boolean move(int[] position) {
        boolean validMove = true;

        try {
            this.setCurrentRow(position[0]);
            this.setCurrentColumn(position[1]);
        } catch (IndexOutOfBoundsException e) {
            validMove = false;
        }

        return validMove;
    }
}
