import java.util.*;

public class Solve {
    char[][] grid = new char[9][9];
    int[] rows  = new int[9];
    int[] cols  = new int[9];
    int[] boxes = new int[9];

    HashMap<String,HashSet<Integer>> map= new HashMap<>();
     
    Solve(){
        this.grid = Sudoku.grid;
    }

    void solve(){
        boolean valid=isValid();
        System.out.println("valid ="+valid);
            if(valid){
                int box=maxBox();
                int count=0;
                    System.out.println("out starting box="+box);
                Logic l= new Logic(rows,cols,boxes,map);
                List<String> li;
                
                do{
                    //if(count>0) 
                    box=count;
                li = l.getEmptyCells(box);
                    System.out.println("starting box="+box);
                    System.out.println("Empty boxes are at indices");
                for(int i=0;i<li.size();i++){
                    //System.out.print(li.get(i));

                    String s[]= li.get(i).split(" ");
                    int t1=Integer.parseInt(s[0]);
                    int t2=Integer.parseInt(s[1]);

                    //System.out.println( " compare t1="+t1+" t2="+t2);

                    map.put(li.get(i),l.possibleValues(t1,t2));

                    

                    //System.out.print("possible val At index="+li.get(i)+" = ");

                    //printSet(map.get(li.get(i)));

                    if(map.get(li.get(i)).size()==1){
                        System.out.print("possible val At index="+li.get(i)+" = ");
                        printSet(map.get(li.get(i)));
                        l.removeEle(t1, t2,map.get(li.get(i)).iterator().next());
                    }
/*block for testing remove element */
                    //System.out.println("Enter element to remove");
                    
                    //int n=6;
                    //System.out.println("row already has "+n+" = "+((rows[t1]&1<<n)>0));
                    //l.removeEle(t1, t2, n);
                    //printSet(map.get(li.get(i)));
                    //System.out.println("row has "+n+" = "+((rows[t1]&1<<n)>0));

                }
                ++count;
                if(count==box) count++;
            }while(count<9);

            System.out.println("Solution----");
            printMap();    
            }
            else{
                System.out.println("The entered sudoku is invalid");
            }
    }

    //for testing
    void printSet(HashSet<Integer> set){
        Iterator<Integer> iterator = set.iterator();
        //System.out.print("possible val At index="+li.get(i)+" = ");
                    while(iterator.hasNext()){
                        System.out.print(iterator.next()+" ");
                    }
                    System.out.println();
    }

    //forTesting
    void printMap(){
        for(Map.Entry<String,HashSet<Integer>> entry: map.entrySet()){
                System.out.print("possible val At index="+entry.getKey()+" = ");
                printSet(entry.getValue());
        }
    }

    int maxBox(){
        int maxBox =0,max=0;

        for(int i=0;i<boxes.length;i++){
            
            int count= Integer.toBinaryString(boxes[i]).replaceAll("0", "").length();
            if(max<count){
                max = count;
                maxBox = i;
                //System.out.println("playing i="+i+" count="+count+" val="+boxes[i]);
            }
        }        
        return maxBox;
    }

    boolean isValid(){
        //best space complexcity for valid sudoku problem in lc
        int N = 9;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Check if the position is filled with number
                if (grid[r][c] == '0') {
                    continue;
                }
                int val = grid[r][c] - '0';

                //System.out.println("char ="+grid[r][c] +"val ="+val+" pow"+(1<<(val)));
                
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

            //System.out.println("Initial row="+Arrays.toString(rows));
        }
        return true;
    }
}
