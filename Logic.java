import java.util.*;
public class Logic {
    //char[][] grid = new char[9][9];
    int[] rows  = new int[9];
    int[] cols  = new int[9];
    int[] boxes = new int[9];

    HashMap<String,HashSet<Integer>> map;

    Logic(int[] rows,int[] cols,int[] boxes,HashMap<String,HashSet<Integer>> map){
        //this.grid=grid;;
        this.rows=rows;
        this.cols = cols;
        this.boxes = boxes;

        this.map =map;
    }

    List<String> getEmptyCells(int BoxNumber){
        BoxNumber++;
        List<String> li = new ArrayList<>();
        int row = (BoxNumber/3)*3;
        int col = (BoxNumber%3 -1 )*3;
        if(BoxNumber%3==0){
            row -=3;
            col = 6;
        } 

        System.out.println("r="+row+" c="+col);

        //System.out.println("indexes traversed to find empty cell");
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
            //System.out.println("i="+i+" r="+rows[r]+" "+(1<<i)+" op="+(rows[r]& 1<<i)+"\n"+
            //                   "c="+cols[c]+" "+(1<<i)+" op="+(cols[c]& 1<<i)+"\n"+
            //                    "b="+boxes[BoxNumber]+" "+(1<<i)+" op="+(boxes[BoxNumber]& 1<<i)+"\n"
            //);
            if( (rows[r]& 1<<i)==0 && (cols[c]& 1<<i)==0 && (boxes[BoxNumber]& 1<<i)==0){
                //System.out.println(i+ " is chosen");
                set.add(i);
            }
            i++;
        }

        return set;
    }

    void removeEle(int r,int c,int ele){
        int BoxNumber = (r/3)*3 +c/3;

        rows[r] = rows[r] | 1<<ele;
        cols[c] = cols[c] | 1<<ele;

        boxes[BoxNumber] =  boxes[BoxNumber] | 1<<ele;

        for(int i=0;i<9;i++){
            if(map.containsKey(i+" "+c)){
                map.get(i+" "+c).remove(ele);
            }
            if(map.containsKey(r+" "+i)){
                map.get(r+" "+c).remove(ele);
            }
        }

        int row = getRowStartingForBox(BoxNumber);
        int col = getColStartingForBox(BoxNumber);
        for(int i=row;i<=row+2;i++){
            for(int j=col;j<col+2;j++){
                if(map.containsKey(i+" "+j)){
                    map.get(i+" "+j).remove(ele);
                }
            }
        }
    }

    int getRowStartingForBox(int BoxNumber){
        int row = (BoxNumber/3)*3;
        if(BoxNumber%3==0){
            row -=3;
        } 
        return row;
    }

    int getColStartingForBox(int BoxNumber){
        int col = (BoxNumber%3 -1 )*3;
        if(BoxNumber%3==0){
            col = 6;
        } 
        return col;
    }
}
