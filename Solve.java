import java.util.*;

public class Solve extends SolvingMethods {
    // instance variables are present in the parent class
    Solve() {
        this.grid = Sudoku.grid;
        strategy = new Strategy(grid, rows, cols, boxes, map);
    }

    void solve() {
        // testing
        /// *
        valid = isValid();
        // Strategy strategy = new Strategy(grid, rows, cols, boxes, map);
        /*
         * for(int i=0;i<9;i++){
         * System.out.println(" sol of row no="+(i)+" "+strategy.hasLastBlankCell(i));
         * }
         */
        do {
            solvingSteps();
            // System.out.println("result of np strategy="+strategy.nakedPairStrategy());
        } while (valid && gridHasSinglePossibleValue());
        //System.out.println("After removing single poss grid=");
        //Sudoku.printGrid();
        // printMap();
        /* 
        do {
            System.out.println("result of np strategy=" + strategy.nakedPairStrategy());
            placeSingleCells();
            printMap();
        }while (gridHasSinglePossibleValue());
        Sudoku.printGrid();
        */
        // */
        /*
         * do {
         * solvingSteps();
         * } while (valid && gridHasSinglePossibleValue());
         */
    }

    void solvingSteps() {
        refreshInstanceVariable();
        valid = isValid();
        // System.out.println("valid ="+valid);
        if (valid) {
            int box = 0; // = maxBox();
            logic = new Logic(grid, rows, cols, boxes, map);
            List<String> li;

            while (box < 9) {
                li = logic.getEmptyCellsInBox(box);
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

    void placeSingleCells() {
        List<String> l = new ArrayList<>();

        for (Map.Entry<String, HashSet<Integer>> entry : map.entrySet()) {
            if (hasSinglePossibleValue(entry.getValue())) {
                l.add(entry.getKey());
                System.out.println("in s, chosen sing cell="+entry.getKey()+" ele "+entry.getValue().iterator().next());
            }
        }
        for (int i = 0; i < l.size(); i++) {
            String t[] = l.get(i).split(" ");
            int r = Integer.parseInt(t[0]);
            int c = Integer.parseInt(t[1]);
            System.out.println("Testing hset of single pos ="+map.get(l.get(i)).toString());
            System.out.println("r=."+r+" c="+c);
            if(map.get(l.get(i)).size()>0){
                addEleToGridRemovPossibility(map.get(l.get(i)), r, c);
            }
        }
        System.out.println("in s, sing cell completed---");
    }

    // check the size of the hashset is one or not
    public boolean hasSinglePossibleValue(HashSet<Integer> hset) {
        return hset.size() == 1 ? true : false;
    }

    // checks the map and gives true if any empty cell has single possible value
    public boolean gridHasSinglePossibleValue() {
        for (Map.Entry<String, HashSet<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1)
                return true;
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
