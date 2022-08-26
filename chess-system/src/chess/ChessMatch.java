package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

public class ChessMatch {
    
    private Board board;
    
    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
    }
    
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat =  new ChessPiece[board.getRows()][board.getCols()];
        for(int i=0; i<board.getRows(); i++){
            for(int j=0; j<board.getCols(); j++){
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        
        return mat;        
    }
    
    public boolean[][] possibleMoves(ChessPosition source){
        Position pos = source.toPosition();
        validateSourcePosition(pos);
        return board.piece(pos).possibleMoves();  
    } 
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece)capturedPiece;
    }
    
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece captured = board.removePiece(target);
        board.placePiece(p, target);
        return captured;
    }
    
    private void validateSourcePosition(Position pos){
        if(!board.thereIsAPiece(pos)){
            throw new ChessException("There is no piece on source position.");
        }
        if(!board.piece(pos).isThereAnyMove()){
            throw new ChessException("There is no possible movements for the chosen piece.");
        }
    }
    
    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece can't move to target position.");
        }
    }
    
    private void placeNewPiece(char col, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(col, row).toPosition());
    }
    
    private void initialSetup() {
        placeNewPiece('b', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
    }
}
