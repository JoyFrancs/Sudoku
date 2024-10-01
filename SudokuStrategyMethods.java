import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;


public class SudokuStrategyMethods implements SudokuStrategy {
    char[][] grid = new char[9][9];
    int[] rows  = new int[9];
    int[] cols  = new int[9];
    int[] boxes = new int[9];
    int result;

    HashMap<String,HashSet<Integer>> map;

    Logic logic;

    //methods of LastBlankCell -3
    public boolean hasRowWithOneBlankCell(int index){
        result = 1023 ^ (rows[index]+1);
        //1023 has the summation of 2^0 to 2^9 which has 10 consistent 1 bits
        // ^ return true only at the 0 bits of(rows[index]+1). Taking the non-present elements 
        if(result>0 && ( result & (result-1))==0){
            //the  above condition checks the resutl is in  power of 2
            // which mean only 1 cell in a row is empty
            return true;
        }
        return false;
    }
    public boolean hasColWithOneBlankCell(int index){   
        result = 1023 ^ (cols[index]+1);
        return (result>0 && (result & (result-1))==0);
    }
    public boolean hasBoxWithOneBlankCell(int index){
        result = 1023 ^ (boxes[index]+1);
        return (result>0 && (result & (result-1))==0);
    }

    //general method to fill the value into the grid
    void fillBlankCellIn(String location,int index){
        if(location.equals("row")){
            logic.fill(logic.getEmptyCellsInRow(index), logic.findEleByTwoPower(result));
        }
        else if(location.equals("col")){
            logic.fill(logic.getEmptyCellsInCol(index), logic.findEleByTwoPower(result));
        }
        else if(location.equals("box")){
            logic.fill(logic.getEmptyCellsInBox(index), logic.findEleByTwoPower(result));
        }
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
