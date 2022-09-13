package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch match) {
        super(board, color);
        chessMatch = match;
    }
    
    @Override
    public String toString(){
        return "p";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];
        
        Position p = new Position(0, 0);
        
        if(getColor() == Color.WHITE){
            
            p.setValues(position.getRow()-1, position.getCol());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getCol()] = true;
                if(getMoveCount() == 0){
                    Position p2 = new Position(position.getRow()-2, position.getCol());
                    if(getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)){
                        mat[p2.getRow()][p2.getCol()] = true;   
                    }
                }
            }
            p.setValues(position.getRow()-1, position.getCol()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getCol()] = true;
            }
            p.setValues(position.getRow()-1, position.getCol()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getCol()] = true;
            }
            
            //Special move en passant white
            if(position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getCol()-1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && 
                   getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow()-1][left.getCol()] = true;
                }
                Position right = new Position(position.getRow(), position.getCol()+1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && 
                   getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow()-1][right.getCol()] = true;
                }
            }
            
        }else{
            
            p.setValues(position.getRow()+1, position.getCol());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getCol()] = true;
                if(getMoveCount() == 0){
                    Position p2 = new Position(position.getRow()+2, position.getCol());
                    if(getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)){
                        mat[p2.getRow()][p2.getCol()] = true;   
                    }
                }
            }
            p.setValues(position.getRow()+1, position.getCol()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getCol()] = true;
            }
            p.setValues(position.getRow()+1, position.getCol()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getCol()] = true;
            }
            
            //Special move en passant black
            if(position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getCol()-1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && 
                   getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow()+1][left.getCol()] = true;
                }
                Position right = new Position(position.getRow(), position.getCol()+1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && 
                   getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow()+1][right.getCol()] = true;
                }
            }
            
            
            
        }
        
        return mat;
        
    }
    
}
