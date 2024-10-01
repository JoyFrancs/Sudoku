import java.util.*;
public class Logic extends SudokuSolvingLogics{

    Logic(char[][] grid,int[] rows,int[] cols,int[] boxes,HashMap<String,HashSet<Integer>> map){
        this.grid=grid;;
        this.rows=rows;
        this.cols = cols;
        this.boxes = boxes;
        this.map =map;
    }

    List<String> getEmptyCellsInBox(int BoxNumber){
        BoxNumber++;
        List<String> li = new ArrayList<>();
        int row = (BoxNumber/3)*3;
        int col = (BoxNumber%3 -1 )*3;
        if(BoxNumber%3==0){
            row -=3;
            col = 6;
        } 

        for(int i=row;i<=row+2;i++){
            for(int j=col;j<=col+2;j++){
                //System.out.println("i="+i+" j="+j);
                if(Sudoku.grid[i][j]=='0'){
                    li.add(i+" "+j);
                }
            }
        }
        return li;
    }

    

    HashSet<Integer> possibleValues(int r,int c){
        int BoxNumber=(r/3)*3+c/3;
        //System.out.println("Box no ="+BoxNumber);
        HashSet<Integer> set = new HashSet<>();
        //System.out.println("In possible values");
        int i=1;
        //System.out.println("Final row="+Arrays.toString(rows));
        while(i<=9){
            
            if( (rows[r]& 1<<i)==0 && (cols[c]& 1<<i)==0 && (boxes[BoxNumber]& 1<<i)==0){
                //System.out.println(i+ " is chosen");
                set.add(i);
            }
            i++;
        }

        return set;
    }

}
