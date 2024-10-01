import java.util.List;

public interface SudokuLogics {
    public int findEleByTwoPower(int i);
    public List<String> getEmptyCellsInRow(int rowNumber);
    public List<String> getEmptyCellsInCol(int colNumber);
    public void fill(List<String>  li,int ele);
    public void removeEle(int r,int c,int ele);
    public int getRowStartingForBox(int BoxNumber);
    public int getColStartingForBox(int BoxNumber);  
}
