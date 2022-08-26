package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }
    
    @Override
    public String toString(){
        return "K";
    }
    
    private boolean canMove(Position pos){
        ChessPiece p = (ChessPiece)getBoard().piece(pos);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];  
        
        Position p = new Position(0, 0);
        
        for(int i=(position.getRow()-1); i<=(position.getRow()+1); i++){
            for(int j=(position.getCol()-1); j<=(position.getCol()+1); j++){
                p.setValues(i, j);
                if(p != position){
                    if(getBoard().positionExists(p) && canMove(p)){
                        mat[i][j] = true;
                    }
                }
            }
        }
        return mat;
    } 
    
}
