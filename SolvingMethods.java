import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class SolvingMethods implements SudokuSolvingMethods {
        char[][] grid = new char[9][9];
    int[] rows  = new int[9];
    int[] cols  = new int[9];
    int[] boxes = new int[9];

    Logic logic;
    Strategy strategy;

    boolean valid;

    HashMap<String,HashSet<Integer>> map= new HashMap<>();

    public void addEleToGridRemovPossibility(HashSet<Integer> hset,int r,int c){
            //printSet(map.get(li.get(i)));
            int ele=hset.iterator().next();
            //add the unique element to the grid
            grid[r][c] = (char) (ele+'0');

            //testing
            /* 
            System.out.println("In sm, r=7 c = 8");
            if(map.containsKey(7+" "+8)){
                printSet(map.get(7+" "+8));
            }
            */
            
            //removes the filled element from the possibilites in the empty cells of the row,col,box
            this.logic.removeEle(r, c,ele);

            //in testing(npStrategy) to remove element from empty cells mapped in the map
            map.remove(r+" "+c);
    }

    //returns the box which already has the maximum elements
    public int maxBox(){
        int maxBox =0,max=0;
        for(int i=0;i<boxes.length;i++){ 
            int count= Integer.toBinaryString(boxes[i]).replaceAll("0", "").length();
            if(max<count){
                max = count;
                maxBox = i;
            }
        }        
        return maxBox;
    }

    public boolean isValid(){
        int N = 9;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Check if the position is filled with number
                if (grid[r][c] == '0') {
                    continue;
                }
                int val = grid[r][c] - '0';
                
                int pos = 1 << (val);

                // Check the row
                if ((rows[r] & pos) > 0) {
                    return false;
                }
                rows[r] |= pos;

                // Check the column
                if ((cols[c] & pos) > 0) {
                    return false;
                }
                cols[c] |= pos;

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if ((boxes[idx] & pos) > 0) {
                    return false;
                }
                boxes[idx] |= pos;
            }
        }
        return true;
    }

    //for testing
    void printSet(HashSet<Integer> set) {
        Iterator<Integer> iterator = set.iterator();
        // System.out.print("possible val At index="+li.get(i)+" = ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

}
