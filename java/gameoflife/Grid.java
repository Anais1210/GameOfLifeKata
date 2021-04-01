package gameoflife;

import java.util.Random;

public class Grid {

    // Global declaration

    private Cell[][] cells;
    private int sizeGrid;
    private Random rd;

    // Constructor

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
    }

    // Methods

    private void generateRandomInitialState() {
        // Create another grid
        Cell[][] randomCells = new Cell[this.sizeGrid][this.sizeGrid];
        for (int n = 0; n < this.sizeGrid; n++) {
            for (int m = 0; m < this.sizeGrid; m++) {
                // Take a random number (float) between 0 and 1 if random < 0.5 -> cell is dead else cell is alive
                if (this.rd.nextFloat() < 0.5) {
                    randomCells[n][m] = new Cell(false);
                } else {
                    randomCells[n][m] = new Cell(true);
                }
            }
        }
        //Affect randomCells to this.cells for generateNextState()
        this.cells = randomCells;
    }

    // Desc : Generate the state n+1 of the evolution (represent by cells[][])
    public void generateNextState() {
        int stateCheck = 0;
        // Create a new grid to stock the state of the new cell (define the n+1 grid) without modifying the existing gird
        Cell[][] cpCells = new Cell[this.sizeGrid][this.sizeGrid];
        for(int i = 0; i < this.sizeGrid; i++){
            for(int j = 0; j < this.sizeGrid; j++){
                stateCheck = testingEveryPossibility(i,j);
                cpCells[i][j] = new Cell(false);
                // Attribute a new state to the cells based on the pre-existing state of this.cells[i][j] and statecheck (nb of neighbour)
                // Stock this value in the new grid
                cpCells[i][j].setIsAlive(cpCells[i][j].processState(this.cells[i][j].isAlive(),stateCheck));
            }
        }
        // Rename  cpCells -> this.cells
        this.cells = cpCells;
    }

    /*################################################################################################################*/
    /*                       Dedicated part to testing cells positions and evolutions possibility                     */
    /*################################################################################################################*/

    /*
    Desc : Method use to check every position for the cells
    @param {int} i
    @param {int} j
    @return {int} statecheck
    */

    private int testingEveryPossibility(int i, int j){
        int statecheck = 0;
        // Interval (i) : 0
        if(i == 0){
            statecheck += leftSide(i,j);
        }
        // Interval (i) : this.sizeGrid - 1
        if(i == this.sizeGrid -1){
            statecheck += rightSide(i,j);
        }
        // Interval (i) : ]0 - (this.sizeGrid -1)[
        if(i > 0 && i < this.sizeGrid -1){
            statecheck += middle(i,j);
        }
        return statecheck;
    }

    /*
    Desc : calculate every possibility for a cells situate in the first row of the grid : with i = 0 and j in [0 - this.sizegrid[
    @param {int} i
    @param {int} j
    @return {int} statecheck
    */

    private int leftSide(int i , int j){
        int statecheck = 0;
        // Interval (j) : 0
        if(j == 0){
            // column on the right
            statecheck += (this.cells[i][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            // Down one line
            statecheck += (this.cells[i+1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        // Interval (j) : this.sizeGrid -1
        if(j == this.sizeGrid -1){
            // Column on the left
            statecheck += (this.cells[i][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            // Down one line
            statecheck += (this.cells[i+1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j-1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        // Interval (j) : ]0 - (this.sizeGrid-1)[
        if(j > 0 && j < this.sizeGrid -1){
            // Down one line
            statecheck += (this.cells[i+1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            // Same line | column aside
            statecheck += (this.cells[i][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i][j+1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        return statecheck;
    }

    /*
    Desc : calculate every possibility for a cells situate in the last row of the grid : with i = this.sizeGrid - 1 and j in [0 - this.sizegrid[
    @param {int} i
    @param {int} j
    @return {int} statecheck
    */

    private int rightSide(int i , int j){
        int statecheck = 0;
        // Interval (j) : 0
        if(j == 0){
            // column on the right
            statecheck += (this.cells[i][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            // Up one line
            statecheck += (this.cells[i-1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        // Interval (j) : this.sizeGrid -1
        if(j == this.sizeGrid -1){
            // Column on the left
            statecheck += (this.cells[i][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            // Up one line
            statecheck += (this.cells[i-1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j-1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        // Interval (j) : ]0 - (this.sizeGrid-1)[
        if(j > 0 && j < this.sizeGrid -1){
            // Up one line
            statecheck += (this.cells[i-1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            // Same line | column aside
            statecheck += (this.cells[i][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i][j+1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        return statecheck;
    }

    /*
    Desc : calculate every possibility for a cells situate between the first row and the last row of the grid : with i = ]0 - (this.sizeGrid - 1)[ and j in [0 - this.sizegrid[
    @param {int} i
    @param {int} j
    @return {int} statecheck
    */

    private int middle(int i , int j){
        int statecheck = 0;
        // Interval (j) : 0
        if(j == 0){
            // Up one line
            statecheck += (this.cells[i-1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            // Aside
            statecheck += (this.cells[i][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            //Down one line
            statecheck += (this.cells[i+1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        // Interval (j) : this.sizeGrid -1
        if(j == this.sizeGrid -1){
            // Up one line
            statecheck += (this.cells[i-1][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j].toString().compareTo("X") == 0) ? 1 : 0;
            // Aside
            statecheck += (this.cells[i][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            // Down one line
            statecheck += (this.cells[i+1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j-1].toString().compareTo("X") == 0) ? 1 : 0;
        }
        // Interval (j) : ]0 - (this.sizeGrid-1)[
        if(j > 0 && j < this.sizeGrid -1){
            // Up one line
            statecheck += (this.cells[i-1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i-1][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            // Aside
            statecheck += (this.cells[i][j-1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            // Down one line
            statecheck += (this.cells[i+1][j].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j+1].toString().compareTo("X") == 0) ? 1 : 0;
            statecheck += (this.cells[i+1][j-1].toString().compareTo("X") == 0) ? 1 : 0;

        }
        return statecheck;
    }

    /*################################################################################################################*/

    // Redefine toString() -> can now print a complete grid
    public String toString() {
        String theBestString = "";
        for (int i= 0; i<this.sizeGrid; i++){
            for (int j = 0; j<this.sizeGrid;j++){
                if(j == this.sizeGrid -1 ){
                    theBestString += this.cells[i][j];
                }else{
                    theBestString += this.cells[i][j] + " ";
                }
            }
            if(i == this.sizeGrid - 1){
                theBestString += "";
            }else{
                theBestString += "\n";
            }
        }
        return theBestString;
    }
}
