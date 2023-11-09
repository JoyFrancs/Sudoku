import java.util.HashSet;

public interface SudokuSolvingMethods {
    void addEleToGridRemovPossibility(HashSet<Integer> hset,int r,int c);
    int maxBox();
    boolean isValid();
}
