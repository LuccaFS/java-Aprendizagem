package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }
    
    @Override
    public String toString(){
        return "K";
    }
    
    private boolean canMove(Position pos){
        ChessPiece p = (ChessPiece)getBoard().piece(pos);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position pos){
        ChessPiece p = (ChessPiece)getBoard().piece(pos);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
        
        //special move castling
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            //small castling
            Position posR1 = new Position(position.getRow(), position.getCol()+3);
            if(testRookCastling(posR1)){
                Position p1 = new Position(position.getRow(), position.getCol()+1);
                Position p2 = new Position(position.getRow(), position.getCol()+2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getCol()+2] = true;
                }
            }
            //big castling
            Position posR2 = new Position(position.getRow(), position.getCol()-4);
            if(testRookCastling(posR2)){
                Position p1 = new Position(position.getRow(), position.getCol()-1);
                Position p2 = new Position(position.getRow(), position.getCol()-2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getCol()-2] = true;
                }
            }
        }
        
        return mat;
    } 
    
}
