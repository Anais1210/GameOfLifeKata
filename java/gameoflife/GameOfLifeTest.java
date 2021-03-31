package gameoflife;

import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeTest {

    private Grid generateCells(char[][] repMap) {
        int sizeGrid = repMap.length;
        Cell[][] c = new Cell[sizeGrid][sizeGrid];

        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                c[i][j] = repMap[i][j] == 'X' ? new Cell(true) : new Cell(false);
            }
        }

        return new Grid(sizeGrid, c);
    }

    @Test
    public void testCellStaysOrBecomeDead() {
        Cell c = new Cell();
        Assert.assertFalse(c.isAlive());

        /// Cell alive before processing
        c.setIsAlive(true);
        Assert.assertFalse(Cell.processState(c.isAlive(), 0));

        c.setIsAlive(true);
        Assert.assertFalse(Cell.processState(c.isAlive(), 1));

        for (int i = 4; i < 9; i++) {
            c.setIsAlive(true);
            Assert.assertFalse(Cell.processState(c.isAlive(), i));
        }

        /// Cell dead before processing
        c.setIsAlive(false);
        Assert.assertFalse(Cell.processState(c.isAlive(), 0));

        c.setIsAlive(false);
        Assert.assertFalse(Cell.processState(c.isAlive(), 1));

        c.setIsAlive(false);
        Assert.assertFalse(Cell.processState(c.isAlive(), 2));

        for (int i = 4; i < 9; i++) {
            c.setIsAlive(false);
            Assert.assertFalse(Cell.processState(c.isAlive(), i));
        }
    }

    @Test
    public void testCellStaysOrBecomeAlive() {
        Cell c = new Cell();
        Assert.assertFalse(c.isAlive());

        c.setIsAlive(false);
        Assert.assertTrue(Cell.processState(c.isAlive(), 3));

        c.setIsAlive(true);
        Assert.assertTrue(Cell.processState(c.isAlive(), 2));

        c.setIsAlive(true);
        Assert.assertTrue(Cell.processState(c.isAlive(), 3));
    }

    @Test
    public void testGridAllDead() {

        char[][] repMap = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . .\n" +
                         ". . .\n" +
                         ". . .", g.toString());
    }

    @Test
    public void testGridAllDeadButMiddle() {
        char[][] repMap = {
                {'.', '.', '.'},
                {'.', 'X', '.'},
                {'.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . .\n" +
                         ". . .\n" +
                         ". . .", g.toString());
    }

    @Test
    public void testGridDeathCaseOne() {
        char[][] repMap = {
                {'.', '.', '.'},
                {'.', 'X', 'X'},
                {'.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . .\n" +
                         ". . .\n" +
                         ". . .", g.toString());
    }

    @Test
    public void testGridDeathCaseTwo() {
        char[][] repMap = {
                {'.', '.', '.'},
                {'.', '.', 'X'},
                {'X', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . .\n" +
                         ". . .\n" +
                         ". . .", g.toString());
    }

    @Test
    public void testOscillatorCaseOne() {
        char[][] repMap = {
                {'.', '.', '.'},
                {'X', 'X', 'X'},
                {'.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". X .\n" +
                         ". X .\n" +
                         ". X .", g.toString());
    }

    @Test
    public void testOscillatorCaseTwo() {
        char[][] repMap = {
                {'.', 'X', '.'},
                {'.', 'X', '.'},
                {'.', 'X', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . .\n" +
                         "X X X\n" +
                         ". . .", g.toString());
    }

    @Test
    public void testStillLifeCaseOne() {
        char[][] repMap = {
                {'.', '.', '.', '.'},
                {'.', 'X', 'X', '.'},
                {'.', 'X', 'X', '.'},
                {'.', '.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(

                ". . . .\n" +
                         ". X X .\n" +
                         ". X X .\n" +
                         ". . . .", g.toString());
    }

    @Test
    public void testStillLifeCaseTwo() {
        char[][] repMap = {
                {'.', '.', '.', '.', '.'},
                {'.', 'X', 'X', '.', '.'},
                {'.', 'X', '.', 'X', '.'},
                {'.', '.', 'X', '.', '.'},
                {'.', '.', '.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . . . .\n" +
                         ". X X . .\n" +
                         ". X . X .\n" +
                         ". . X . .\n" +
                         ". . . . .", g.toString());
    }

    @Test
    public void testOscillatorsCaseThree() {
        char[][] repMap = {
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'X', '.', '.'},
                {'.', 'X', '.', '.', 'X', '.'},
                {'.', 'X', '.', '.', 'X', '.'},
                {'.', '.', 'X', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . . . . .\n" +
                         ". . . . . .\n" +
                         ". . X X X .\n" +
                         ". X X X . .\n" +
                         ". . . . . .\n" +
                         ". . . . . .", g.toString());
    }


    @Test
    public void testOscillatorsCaseFour() {
        char[][] repMap = {
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', 'X', 'X', 'X', '.'},
                {'.', 'X', 'X', 'X', '.', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . . . . .\n" +
                         ". . . X . .\n" +
                         ". X . . X .\n" +
                         ". X . . X .\n" +
                         ". . X . . .\n" +
                         ". . . . . .", g.toString());
    }

    // Add this test because there was no spaceShip test (this is the smaller one that i found : glider)

    @Test
    public void testGliderOnly(){
        char[][] repMap = {
                {'.','.','.','.','.'},
                {'.','.','X','.','.'},
                {'.','.','.','X','.'},
                {'.','X','X','X','.'},
                {'.','.','.','.','.'}
        };

        Grid g = generateCells(repMap);
        g.generateNextState();
        Assert.assertEquals(
                ". . . . .\n" +
                        ". . . . .\n" +
                        ". X . X .\n" +
                        ". . X X .\n" +
                        ". . X . .", g.toString());

    }

    @Test
    public void testGliderFlying(){
        char[][] repMap = {
                {'.','.','.','.','.'},
                {'.','.','X','.','.'},
                {'.','.','.','X','.'},
                {'.','X','X','X','.'},
                {'.','.','.','.','.'}
        };

        Grid g = generateCells(repMap);

        for(int i = 0; i < 4;i++){
            System.out.flush();
            g.generateNextState();
            System.out.println(g.toString());
        }

        Assert.assertEquals(
                ". . . . .\n" +
                        ". . . . .\n" +
                        ". . . X .\n" +
                        ". . . . X\n" +
                        ". . X X X", g.toString());

    }

}
