package animal;
import java.awt.Graphics;
import java.util.ArrayList;

/*
Polarbear
This program initializes the polarbears location, and moves the polarbear throughout the game.
@author Sarah Hatcher
@version 16.0
*/

public class Polarbear extends Animal {
    private int pengRow;
    private int pengColumn;

    private final int LARGE_INTEGER = Integer.MAX_VALUE;
    private int distanceUp = LARGE_INTEGER;
    private int distanceDown = LARGE_INTEGER;
    private int distanceLeft = LARGE_INTEGER;
    private int distanceRight = LARGE_INTEGER;
    private int bestMove = LARGE_INTEGER;

    private final ArrayList<Direction> bestPathUp = new ArrayList<>();
    private final ArrayList<Direction> bestPathDown = new ArrayList<>();
    private final ArrayList<Direction> bestPathLeft = new ArrayList<>();
    private final ArrayList<Direction> bestPathRight = new ArrayList<>();
    private int bestPathUpSize = LARGE_INTEGER;
    private int bestPathDownSize = LARGE_INTEGER;
    private int bestPathLeftSize = LARGE_INTEGER;
    private int bestPathRightSize = LARGE_INTEGER;

    private int shortestPathSize;

    private int testBearX;
    private int testBearY;
    private int lastBearY;
    private int lastBearX;


    public Polarbear(int x, int y, Direction aDirection, String pathUp, String pathDown, String pathLeft, String pathRight) {
        super(x, y, aDirection, pathUp, pathDown, pathLeft, pathRight);

    }

    /*
movePolarbear will move the polarbear towards the penguin using the shortest path that
This program initializes the polarbears location, and moves the polarbear throughout the game.
@author Sarah
@version 16.0
@param penguin
*/

    public void movePolarbear(Penguin penguin) {
        //Set initial locations for bear and penguins
        testBearX = getXcoord();
        testBearY = getYcoord();
        lastBearX = getXcoord();
        lastBearY = getYcoord();
        pengColumn = penguin.getYcoord();
        pengRow = penguin.getXcoord();
        setPaths(penguin);
        fixPathSizes();
        getShortestPathSize();
        //move polarbear the first move of the shortest path towards the penguin.
        if (shortestPathSize == bestPathUp.size()){
            setYcoord((getYcoord() - 1));
            setDirection(Direction.NORTH);
        }else if (shortestPathSize == bestPathDown.size()){
            setYcoord((getYcoord() + 1));
            setDirection(Direction.SOUTH);
        }else if (shortestPathSize == bestPathLeft.size()){
            setXcoord((getXcoord() - 1));
            setDirection(Direction.WEST);
        }else if(shortestPathSize == bestPathRight.size()){
            setXcoord((getXcoord() + 1));
            setDirection(Direction.EAST);
        }
        //reset the path arraylists
        bestPathUp.clear();
        bestPathDown.clear();
        bestPathLeft.clear();
        bestPathRight.clear();

    }
    /**Set paths adds the first move to the arraylists of paths for the polarbear if moving on that path is both possible and will not cause oscillation.
     * The testBear x and y are reset to the bears location after building a path towards the penguin.
     * The testbear starts at the location of the bear for each path if it has already made the move in the direction towards the penguin.
     * The last bears location is set to the location of the testbear so possible oscillation can be tracked.
     * @param penguin
     *
     */
    private void setPaths(Penguin penguin){
        //check bounds up and safe direction to move.
        if (isUpFree(getXcoord(), getYcoord())&&(getDirection() != Direction.SOUTH)) {
            bestPathUp.add(Direction.NORTH);
            testBearY--;
            lastBearX = testBearX;
            lastBearY = testBearY;
            bestStep(penguin, bestPathUp, testBearY, testBearX);
        }
        //checks bounds right and safe direction to move.
        if (isRightFree(getXcoord(), getYcoord())&&(getDirection() != Direction.WEST)) {
            bestPathRight.add(Direction.EAST);
            testBearX = getXcoord();
            testBearY = getYcoord();
            testBearX++;
            lastBearX = testBearX;
            lastBearY = testBearY;
            bestStep(penguin, bestPathRight, testBearY, testBearX);
        }
        //checks bounds down and safe direction to move.
        testBearX = getXcoord();
        testBearY = getYcoord();
        testBearY--;
        lastBearX = testBearX;
        lastBearY = testBearY;
        if (isDownFree(getXcoord(), getYcoord())&&(getDirection() != Direction.NORTH)) {
            bestPathDown.add(Direction.SOUTH);
            testBearY++;
            bestStep(penguin, bestPathDown, testBearY, testBearX);
            //System.out.println("A best path down: " + bestPathDown);
        }
        //checks bounds left and safe direction to move.
        testBearX = getXcoord();
        testBearY = getYcoord();
        lastBearX = testBearX;
        lastBearY = testBearY;
        if (isLeftFree(getXcoord(), getYcoord())) {
            bestPathLeft.add(Direction.WEST);
            testBearX--;
            bestStep(penguin, bestPathLeft, testBearY, testBearX);
        }

    }
    /**
     * bestStep is a recursive method that builds a path from a polarbear towards the penguin until either it's location is the same as the penguin or it's path is at least 90 steps.
     * The best step will never be the last move the test bear has made.
     * @param penguin
     * @param aPath
     * @param testBearY
     * @param testBearX
     * @return ArrayList<Direction>
     */
    private ArrayList<Direction> bestStep(Penguin penguin, ArrayList<Direction> aPath, int testBearY, int testBearX){
        if (((testBearX == penguin.getXcoord()) && (testBearY == penguin.getYcoord()))||(aPath.size() == 90)) {
            return aPath;
        }else{
            nonOscillatingDistance(testBearX, testBearY);
            getBestMove();
            Direction nextMove = Direction.NORTH;
            if (bestMove == distanceUp) {
                nextMove = Direction.NORTH;
                testBearY--;
            } else if (bestMove == distanceDown) {
                nextMove = Direction.SOUTH;
                testBearY++;
            } else if (bestMove == distanceRight) {
                nextMove = Direction.EAST;
                testBearX++;
            } else if (bestMove == distanceLeft) {
                nextMove = Direction.WEST;
                testBearX--;
            }
            aPath.add(nextMove);
          bestStep(penguin, aPath, testBearY, testBearX);
        }
        return aPath;
    }

    /**
     * Calculates the euclidean distance between the penguin and the x y location passed to the method.
     * @param row
     * @param column
     * @return getDistance
     */
    private int getDistance(int row, int column) {
        int distance = 100000 * (int)((Math.sqrt((Math.abs(( pengRow - row)) * Math.abs(( pengRow -  row))) + (Math.abs((( pengColumn - column)) * Math.abs(( pengColumn - column)))))));
        return distance;

    }

    /**
     * getShortestPathSize will return the minimum value of the pathsizes.
     * @return shortestPathSize
     */
    private int getShortestPathSize() {
        return shortestPathSize = Math.min(bestPathUpSize, (Math.min(bestPathDownSize, Math.min(bestPathLeftSize, bestPathRightSize))));
    }

    /** nonOscillatingDistance checks if the bear's next move will have it oscillating or moving into a boundary and will set it's distance to a maximum integer or if it's a safe move then it will call the getDistance method for the next move.
     * @param aTestBearRow
     * @param aTestBearColumn
     */
    private void nonOscillatingDistance(int aTestBearRow, int aTestBearColumn) {
        //Check up and last column position
        if ((isUpFree(aTestBearRow, aTestBearColumn)) && ((aTestBearColumn - 1) != lastBearY)) {
            distanceUp = getDistance(aTestBearRow, aTestBearColumn - 1);
        } else {
            distanceUp = LARGE_INTEGER;
        }
        //Check right and last row position.
        if ((isRightFree(aTestBearRow, aTestBearColumn)) && ((aTestBearRow + 1) != lastBearX)) {
            distanceRight = getDistance(aTestBearRow + 1, aTestBearColumn);
        } else {
            distanceRight = LARGE_INTEGER;
        }
        //Check Down and last column position
        if ((isDownFree(aTestBearRow, aTestBearColumn)) && (aTestBearColumn + 1) != lastBearY) {
            distanceDown = getDistance(aTestBearRow, aTestBearColumn + 1);
        } else {
            distanceDown = LARGE_INTEGER;
        }
        //Check left and last row position.
        if (((isLeftFree(aTestBearRow, aTestBearColumn)) && (aTestBearRow - 1) != lastBearX)) {
            distanceLeft = getDistance(aTestBearRow - 1, aTestBearColumn);
        } else {
            distanceLeft = LARGE_INTEGER;
        }
    }
    /**
     * getBestMove sets the variable best move equal to the size of the shortest arraylist of steps.
     */
    private void getBestMove() {
         bestMove = Math.abs((Math.min(distanceUp, (Math.min(distanceDown, Math.min(distanceLeft, distanceRight))))));
    }
    /**
     * fixPathSizes will set the size of an empty arraylist to a MAXIMUM integer so the shortest path returned will not be empty.
     */
    private void fixPathSizes() {
        if (bestPathUp.isEmpty()) {
            bestPathUpSize = LARGE_INTEGER;
        }else {
            bestPathUpSize = bestPathUp.size();
        }if (bestPathDown.isEmpty()) {
            bestPathDownSize = LARGE_INTEGER;
        }else {
            bestPathDownSize = bestPathDown.size();
        }if (bestPathRight.isEmpty()) {
            bestPathRightSize = LARGE_INTEGER;
        }else {
            bestPathRightSize = bestPathRight.size();
        }if (bestPathLeft.isEmpty()) {
            bestPathLeftSize = LARGE_INTEGER;
        }else {
            bestPathLeftSize = bestPathLeft.size();
        }
    }
    /* (non-Javadoc)
     * @see Animals.Animal#paint(java.awt.Graphics)
     */
    public void paint(Graphics g){
        super.paint(g);
    }
}