import java.util.*;

public class Solve extends SolvingMethods {
    // instance variables are present in the parent class
    Solve() {
        this.grid = Sudoku.grid;
    }

    void solve() {
        do {
            solvingSteps();
        } while (valid && gridHasSinglePossibleValue());
    }

    void solvingSteps() {
        refreshInstanceVariable();
        valid = isValid();
        // System.out.println("valid ="+valid);
        if (valid) {
            int box = 0; // = maxBox();
            logic = new Logic(rows, cols, boxes, map);
            List<String> li;

            while(box<9){
                li = logic.getEmptyCells(box);
                for (int i = 0; i < li.size(); i++) {
                    String s[] = li.get(i).split(" ");
                    int t1 = Integer.parseInt(s[0]);
                    int t2 = Integer.parseInt(s[1]);

                    // hash set of possible values at row=t1, col=t2
                    var tmpSet = logic.possibleValues(t1, t2);
                    // checking if there is only one possibility in a cell
                    if (hasSinglePossibleValue(tmpSet)) {
                        addEleToGridRemovPossibility(tmpSet, t1, t2);
                    } else {
                        // maping the row,col with its possible values
                        map.put(li.get(i), tmpSet);
                    }
                }
                ++box;
            }
            // printMap();
        } else {
            System.out.println("The entered sudoku is invalid");
        }
    }

    //check the size of the hashset is one or not
    public boolean hasSinglePossibleValue(HashSet<Integer> hset){
        return hset.size()==1? true:false;
    }

    //checks the map and gives true if any empty cell has single possible value
    public boolean gridHasSinglePossibleValue(){
        for(Map.Entry<String,HashSet<Integer>> entry: map.entrySet()){
            if(entry.getValue().size()==1) return true;
        }
        return false;
    }
    
    void refreshInstanceVariable() {
        rows = new int[9];
        cols = new int[9];
        boxes = new int[9];
    }


    // for testing
    void printSet(HashSet<Integer> set) {
        Iterator<Integer> iterator = set.iterator();
        // System.out.print("possible val At index="+li.get(i)+" = ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    // forTesting
    void printMap() {
        for (Map.Entry<String, HashSet<Integer>> entry : map.entrySet()) {
            // if(entry.getKey().split(" ")[0].equals("8")){
            System.out.print("possible val At index=" + entry.getKey() + " = ");
            printSet(entry.getValue());
            // }
        }
    }
}
