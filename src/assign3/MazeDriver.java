package assign3;
/**
 * This program simulates a robot getting through a maze.
 * Unlike tic-tac-toe, where the user is the player, the robot moves
 * autonomously through the maze.
 * We intentionally share the memory location of the maze with the robot.
 * Otherwise the robot would not know how to plan and make its moves.
 * <p>
 * The maze is stored in a text file that will be entered at runtime.
 * The layout of each maze file will contain:
 * â€¢	In the first line:  two integers (the number of rows and columns, respectively,  in the maze)
 * â€¢	In the second line:  two integers (the row and column locations, respectively, of the Start cell
 * â€¢	In the third line:  two integers (the row and column locations, respectively, of the Exit cell
 * â€¢	Each line thereafter will contain characters appearing in one row of the maze.
 * <p>
 * Created by Sherri Harms
 * built on top of Eddy's UW-Parkside solution
 * <p>
 * Last Updated 3/4/2017
 */

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

public class MazeDriver {

    /**
     * This is the main method to run the maze
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File inputFile = getFile();  //sample: testmaze.txt
        Maze maze = new Maze(inputFile);
        System.out.println(maze);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        Robot bot = new RandomRobot(maze);//this ties the robot to the maze it is in
        char prevSpace = 'S';
        for (int k = 0; k < 1000000 && !bot.solved(); k++)
        //this limits the robot's moves, in case it takes too long to find the exit.
        {
            int[] direction = bot.chooseMoveDirection();
            int[] position = new int[2];
            if (bot.getType() == "random") {
                while (!maze.openCell(direction[0] + bot.getCurrentRow(), direction[1] + bot.getCurrentColumn())) {
                    direction = bot.chooseMoveDirection();
                }

                position[0] = direction[0] + bot.getCurrentRow();
                position[1] = direction[1] + bot.getCurrentColumn();

            } else if (bot.getType() == "direction") {
                for (int i = 0; i < direction.length; i += 2) {
                    //System.out.print("i = " + i);
                    if (maze.openCell(direction[i] + bot.getCurrentRow(), direction[i + 1] + bot.getCurrentColumn())) {
                        if (direction[i + 1] < 0) {
                            System.out.println("Facing North");
                            bot.setFacing(0);
                        } else if (direction[i] > 0) {
                            System.out.println("Facing East");
                            bot.setFacing(1);
                        } else if (direction[i + 1] > 0) {
                            System.out.println("Facing South");
                            bot.setFacing(2);
                        } else if (direction[i] < 0) {
                            System.out.println("Facing West");
                            bot.setFacing(3);
                        }

                        position[0] = direction[i] + bot.getCurrentRow();
                        position[1] = direction[i + 1] + bot.getCurrentColumn();
                        break;
                    }
                }
            }

            maze.setCell(bot.getCurrentRow(), bot.getCurrentColumn(), prevSpace);
            bot.move(position);
            prevSpace = ' ';
            //System.out.println("MOVING TO ---> (" + bot.getCurrentRow() + ", " + bot.getCurrentColumn() + ")");
            maze.setCell(bot.getCurrentRow(), bot.getCurrentColumn(), 'R');
            System.out.println("Move #" + (k + 1));
            System.out.println(maze);
            System.out.println("\n");
        }
        System.out.println("Maze Solved!");
    }

    /**
     * Get the file that has the maze specifications.
     *
     * @return File chosen by user.
     */
    public static File getFile() {
        JFileChooser chooser;
        try {

            // Get the filename.
            chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status != JFileChooser.APPROVE_OPTION) {
                System.out.println("No File Chosen");
                System.exit(0);
            }
            return chooser.getSelectedFile();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            System.exit(0);

        }
        return null; //should never get here, but makes compiler happy
    }
}