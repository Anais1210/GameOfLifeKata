import gameoflife.Grid;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Grid g = new Grid(20);
        int gen = 1;
        while (true) {
            System.out.println("Generation : " + gen);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\n");
            g.generateNextState();
            System.out.println(g.toString());
            gen++;
        }
    }
}
