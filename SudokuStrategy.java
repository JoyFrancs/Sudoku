interface SudokuStrategy{
    public boolean hasRowWithOneBlankCell(int index);
    public boolean hasColWithOneBlankCell(int index);
    public boolean hasBoxWithOneBlankCell(int index);
}