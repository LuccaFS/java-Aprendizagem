package boardgame;

public class Position {
    private int row;
    private int col;
    
    public Position(){ }
    
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public Integer getRow(){
        return this.row;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public Integer getCol(){
        return this.col;
    }
    
    public void setCol(int col){
        this.col = col;
    }
    
    @Override
    public String toString(){
        return row + ", " + col;
    }
    
}
