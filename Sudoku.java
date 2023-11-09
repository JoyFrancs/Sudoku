import java.util.*;

class Sudoku {
    static char[][] grid;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("kindly enter the grid data");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = (char) (sc.nextInt() + '0');
            }
        }
        sc.close();
    }

    static void printGrid(){
        System.out.println("Grid =");
        for(int i=0;i<9;i++){
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] g = {
                { '7', '6', '0', '0', '0', '9', '0', '0', '0' },
                { '8', '0', '9', '1', '0', '2', '0', '0', '6' },
                { '2', '0', '4', '8', '0', '0', '7', '9', '3' },
                { '0', '9', '1', '0', '5', '0', '0', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '0', '0', '8', '0', '1', '6', '0' },
                { '9', '8', '7', '0', '0', '3', '6', '0', '0' },
                { '3', '0', '0', '7', '0', '6', '8', '0', '0' },
                { '0', '0', '0', '2', '0', '0', '0', '7', '9' },
        };

        grid = g;
        
        Scanner sc = new Scanner(System.in);
        int n = 1;
        for (int i = 0; i < n; i++) {
            getInput();
            //grid = g;
                //1 Solve s = new Solve();
            //printGrid();
            Solve s = new Solve();
            s.solve();
            printGrid();
        }
        sc.close();
    }

}