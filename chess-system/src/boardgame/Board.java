package boardgame;

public class Board {
    
    private int rows;
    private int cols;
    private Piece[][] pieces;
    
    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        pieces = new Piece[rows][cols];
    }
    
    public Integer getRows(){
        return this.rows;
    }
    
    public void setRow(int rows){
        this.rows = rows;
    }
    
    public Integer getCols(){
        return this.cols;
    }
    
    public void setCol(int cols){
        this.cols = cols;
    }
    
    public Piece piece(int row, int col){
        return pieces[row][col];
    }
    
    public Piece piece(Position pos){
        return pieces[pos.getRow()][pos.getCol()];
    }
    
}
