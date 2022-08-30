package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }
    
    @Override
    public String toString(){
        return "N";
    }
    
    private boolean canMove(Position pos){
        ChessPiece p = (ChessPiece)getBoard().piece(pos);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];
        
        Position p = new Position(0, 0);
        
        //above
        p.setValues(position.getRow() - 2, position.getCol() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
        
        
        p.setValues(position.getRow() - 2, position.getCol() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
        
        
        //below
        p.setValues(position.getRow() + 2, position.getCol() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
        
        
        p.setValues(position.getRow() + 2, position.getCol() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
    
        
        //left
        p.setValues(position.getRow() - 1, position.getCol() - 2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
        
        
        p.setValues(position.getRow() + 1, position.getCol() - 2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
        
        
        //right
        p.setValues(position.getRow() - 1, position.getCol() + 2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
        
        
        p.setValues(position.getRow() + 1, position.getCol() + 2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCol()] = true;
            p.setRow(p.getRow() - 1);
        }
        
            
        return mat;
    }
    
}
