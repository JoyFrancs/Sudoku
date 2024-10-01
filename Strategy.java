import java.util.*;

public class Strategy extends SudokuStrategyMethods {
    //Strategy 1 LastBlankCell Strategy
    boolean hasLastBlankCell(int index){
        boolean hasLastBlankCell=false;
        if(hasRowWithOneBlankCell(index)){
        System.out.println("single empty cell in row"+index);
            fillBlankCellIn("row", index);
            hasLastBlankCell = true;
        }
        if(hasColWithOneBlankCell(index)){
        System.out.println("single empty cell in col"+index);
            fillBlankCellIn("col", index);
            hasLastBlankCell = true;
        }
        if(hasBoxWithOneBlankCell(index)){
        System.out.println("single empty cell in box"+index);
            fillBlankCellIn("box", index);
            hasLastBlankCell = true;
        }
        return hasLastBlankCell;
    }
    //Strategy 3 SingleCandidate Strategy
    void hasSingleCandidate(int box){

    }

    boolean nakedPairStrategy(){
        boolean res=false;
        
        for(Map.Entry<String,HashSet<Integer>> entry : map.entrySet()){

            List<Integer> li = new ArrayList<>();
            List<String> ind = new ArrayList<>();
            String location="";
            //System.out.println("printed manually2="+map.get(3+" "+5).toString());
            if(entry.getValue().size()==2){
                Iterator<Integer> iterator = entry.getValue().iterator();

                //for testing
                //System.out.print("pair found at "+entry.getKey()+" = ");
                //printSet(entry.getValue());

                li.add(iterator.next());
                ind.add(entry.getKey());
                
                String s[] = entry.getKey().split(" ");
                int r= Integer.parseInt(s[0]);
                int c = Integer.parseInt(s[1]);
                for(int i=0;i<9 ;i++){
                    if(i!=c && map.containsKey(r+" "+i) && map.get(r+" "+i).equals(entry.getValue())){
                        //for testing
                        //System.out.print("pair present in row at "+(r+" "+i)+"=");
                        //printSet(map.get(r+" "+i));

                        li.add(iterator.next());
                        ind.add(r+" "+i);
                        location+="r";
                    }
                    if(i!=r && map.containsKey(i+" "+c) && map.get(i+" "+c).equals(entry.getValue())){
                        li.add(iterator.next());

                        ind.add(i+" "+c);
                        location+="c";

                        //for testing
                        //System.out.print("pair present in col at "+(i+" "+c)+"=");
                        //printSet(map.get(i+" "+c));
                    }
                }
                int b= logic.getBoxByRowCol(r, c);
                int br = logic.getRowStartingForBox(b);
                int bc = logic.getColStartingForBox(b);
                for(int i=br;i<br+3;i++){
                    for(int j=bc;j<bc+3;j++){
                        if((i!=r && j!=c) && map.containsKey(i+" "+j) && map.get(i+" "+j).equals(entry.getValue())){
                        li.add(iterator.next());

                        ind.add(i+" "+j);
                        location+='b';

                        //for testing
                        //System.out.print("pair present in box at "+(i+" "+j)+"=");
                        //printSet(map.get(i+" "+j));
                        }
                    }
                }
                if(!location.isEmpty()){
                    //System.out.println("location ="+location);
;
                    //System.out.println("Testing... ind="+ind.toString()+" \nli="+li.toString());
                 logic.removeEleExcept(ind, li, location);
                 res= true; 
                }
            }
        }
        return res;
    }

    Strategy(char[][] grid,int[] rows,int[] cols,int[] boxes,HashMap<String,HashSet<Integer>> map){
            this.grid=grid;
            this.rows=rows;
            this.cols = cols;
            this.boxes = boxes;
            this.map =map;
            logic = new Logic(grid, rows, cols, boxes, map);
    }
}
