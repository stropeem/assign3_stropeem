package assign3;

/**
 * Created by Elisa on 2/24/2017.
 * Last Updated 3/5/2017
 */

public class Robot {

    private int currentRow;
    private int currentColumn;
    private char robotName = 'R';
    private int exitRow;
    private int exitColumn;
    private String type = "none";
    private int facing = 2;

    /**
     * Default constructor
     */
    public Robot() {
    }

    /**
     * Robot constructor
     *
     * @param maze
     */
    public Robot(Maze maze) {
        //create the robot and place it at the start of the maze
        setCurrentRow(maze.getStartRow());
        setCurrentColumn(maze.getStartCol());
        maze.setCell(getCurrentRow(), getCurrentColumn(), getRobotName());
        exitRow = maze.getExitRow();
        exitColumn = maze.getExitCol();

    }

    /**
     * This method sets the current row the robot is in
     *
     * @param currentRow
     */
    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    /**
     * This method sets the current column the robot is in
     *
     * @param currentColumn
     */
    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    /**
     * This method sets the robot's name
     *
     * @param robotName
     */
    public void setRobotName(char robotName) {
        this.robotName = robotName;
    }

    /**
     * This method sets the direction the robot is facing
     *
     * @param facing
     */
    public void setFacing(int facing) {
        this.facing = facing;
    }

    /**
     * This method gets the direction the robot is facing
     *
     * @return facing
     */
    public int getFacing() {
        return this.facing;
    }

    /**
     * This method gets the type of the robot
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * This method gets the current row the robot is in
     *
     * @return currentRow
     */
    public int getCurrentRow() {
        return currentRow;
    }

    /**
     * This method gets the current column the robot is in
     *
     * @return currentColumn
     */
    public int getCurrentColumn() {
        return currentColumn;
    }

    /**
     * This method gets the robot name
     *
     * @return robotName
     */
    public char getRobotName() {
        return robotName;
    }

    /**
     * This method chooses the move direction. It is overridden by the RandomRobot and RightHandRobot classes
     *
     * @return i
     */
    public int[] chooseMoveDirection() {
        int[] i = new int[2];
        return i;
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

    /**
     * This method checks to see if the robot has finished the maze
     *
     * @return solved
     */
    public boolean solved() {
        boolean solved = false;

        if (currentRow == exitRow && currentColumn == exitColumn) {
            solved = true;
        }
        return solved;
    }
}