import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SudokuSolvingLogics implements SudokuLogics {

    char[][] grid = new char[9][9];
    int[] rows = new int[9];
    int[] cols = new int[9];
    int[] boxes = new int[9];

    HashMap<String, HashSet<Integer>> map;

    public int findEleByTwoPower(int i) {
        return (int) (Math.log(i) / Math.log(2));
    }

    public List<String> getEmptyCellsInRow(int rowNumber) {
        List<String> li = new ArrayList<>();

        for (int col = 0; col < 9; col++) {
            if (grid[rowNumber][col] == '0') {
                li.add(rowNumber + " " + col);
            }
        }
        return li;
    }

    public List<String> getEmptyCellsInCol(int colNumber) {
        List<String> li = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            if (grid[row][colNumber] == '0') {
                li.add(row + " " + colNumber);
            }
        }
        return li;
    }

    public void fill(List<String> li, int ele) {
        String[] s = li.get(0).split(" ");
        int t1 = Integer.parseInt(s[0]), t2 = Integer.parseInt(s[1]);
        grid[t1][t2] = (char) (ele + '0');
        removeEle(t1, t2, ele);
    }

    public void removeEle(int r, int c, int ele) {

        //for testing !
        //if(r==5 && c==1)
        //System.out.println("Removing element at "+(r+" "+c)+" = "+ele);

        int BoxNumber = (r / 3) * 3 + c / 3;

        rows[r] = rows[r] | 1 << ele;
        cols[c] = cols[c] | 1 << ele;

        boxes[BoxNumber] = boxes[BoxNumber] | 1 << ele;

        for (int i = 0; i < 9; i++) {
            if (map.containsKey(i + " " + c)) {
                // if(i==8 && c==4) System.out.println(ele+" is removed from "+(i+" "+c));
                map.get(i + " " + c).remove(ele);

                //in testing state to eliminate empty keys
                //if(map.get(i + " " + c).size()==0) map.remove(i + " " + c);
            }
            if (map.containsKey(r + " " + i)) {
                // if(r==8 && i==4) System.out.println(ele+" is removed from "+(r+" "+c));
                map.get(r + " " + i).remove(ele);
                //in testing state to eliminate empty keys
                //if(map.get(r + " " + i).size()==0) map.remove(r + " " + i);
            }
            
        }

        int row = getRowStartingForBox(BoxNumber);
        int col = getColStartingForBox(BoxNumber);
        for (int i = row; i <= row + 2; i++) {
            for (int j = col; j < col + 2; j++) {
                if (map.containsKey(i + " " + j)) {
                    // if(i==8 && j==4) System.out.println(ele+" is removed from "+(i+" "+c));
                    map.get(i + " " + j).remove(ele);

                    //in testing state to eliminate empty keys
                    //if(map.get(i + " " + j).size()==0) map.remove(i + " " + j);
                }
            }
        }
    }

    public void removEleFromOtherInRow(int row,int col,int ele){
        //rows[row] = rows[row] | 1 << ele;
         for (int i = 0; i < 9; i++) {
            if (i!=col && map.containsKey(row + " " + i)) {
                // if(i==8 && c==4) System.out.println(ele+" is removed from "+(i+" "+c));
                map.get(row + " " + i).remove(ele);

                //in testing state to eliminate empty keys
                //if(map.get(i + " " + c).size()==0) map.remove(i + " " + c);
            }
        }
    }

    public void removEleFromOtherInCol(int row,int col,int ele){
        //cols[col] = cols[col] | 1 << ele;

        for (int i = 0; i < 9; i++) {
            if (i!=row && map.containsKey(i + " " + col)) {
                 if(i==7 && col==8) System.out.println("row="+row+" "+ele+" is removed from "+(i+" "+col));
                map.get(i + " " + col).remove(ele);

                //in testing state to eliminate empty keys
                //if(map.get(i + " " + c).size()==0) map.remove(i + " " + c);
            }
        }

    }

    public void removeEleFromOtherInBox(int r,int c,int ele){
        int BoxNumber = (r / 3) * 3 + c / 3;
        //boxes[BoxNumber] = boxes[BoxNumber] | 1 << ele;

        int row = getRowStartingForBox(BoxNumber);
        int col = getColStartingForBox(BoxNumber);
        for (int i = row; i <= row + 2; i++) {
            for (int j = col; j < col + 2; j++) {
                if ((i!=r||j!=c) &&map.containsKey(i + " " + j)) {
                    // if(i==8 && j==4) System.out.println(ele+" is removed from "+(i+" "+c));
                    map.get(i + " " + j).remove(ele);

                    //in testing state to eliminate empty keys
                    //if(map.get(i + " " + j).size()==0) map.remove(i + " " + j);
                }
            }
        }
    }

    public void removeEleExcept(List<String> rcb, List<Integer> ele, String location) {

        // remove in the row
        for (int ind = 0; ind < ele.size(); ind++) {
            int r=Integer.parseInt(rcb.get(ind).split(" ")[0]);
            int c=Integer.parseInt(rcb.get(ind).split(" ")[1]);
            int b= getBoxByRowCol(r, c);

            if (location .indexOf("r")>-1) {
                for (int i = 0; i < 9 ; i++) {
                    if (!rcb.contains(i+" "+c) && i != r && map.containsKey(String.valueOf(r) + " " + String.valueOf(i))) {
                        removEleFromOtherInRow(r,c, ele.get(ind));
                        break;
                    }
                }
            } else if (location.indexOf("c")>-1) {
                for (int i = 0; i < 9 ; i++) {
                    if (!rcb.contains(i+" "+c) && i!=c && map.containsKey(String.valueOf(i) + " " + String.valueOf(c))) {
                        //for testing
                        //System.out.println("r="+i+" c ="+c+" ind="+rcb.toString()+"\nele="+ele.get(ind));
                        removEleFromOtherInCol(r,c, ele.get(ind));
                    }
                }
            } else if (location.indexOf("b")>-1) {
                int t1 = getRowStartingForBox(b);
                int t2 = getColStartingForBox(b);
                for (int i = t1; i <= t1 + 2; i++) {
                    for (int j = t2; j <= t2 + 2; j++) {
                        if (!rcb.contains(i+" "+c) && !(i == r && j == c)) {
                            removeEleFromOtherInBox(r, c, ele.get(ind));
                        }
                    }
                }
            }
            if(rcb.contains(7+" "+8)){
            System.out.print("in ssl, After rem exp ele at r=7 c=8 ele="+ele+" loc="+location+" rcb="+rcb);
            printSet(map.get(7+" "+8));
            }
        }
    }

    public int getRowStartingForBox(int BoxNumber) {
        int row = (BoxNumber / 3) * 3;
        if (BoxNumber % 3 == 0) {
            row -= 3;
        }
        return row;
    }

    public int getColStartingForBox(int BoxNumber) {
        int col = (BoxNumber % 3 - 1) * 3;
        if (BoxNumber % 3 == 0) {
            col = 6;
        }
        return col;
    }

    public int getBoxByRowCol(int r, int c) {
        int b = (r / 3) * 3 + c / 3;
        return b;
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
