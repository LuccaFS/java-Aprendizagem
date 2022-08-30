package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

public class ChessMatch {
    
    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    
    private List<Piece> piecesOnBoard = new ArrayList<>();
    private List<Piece> piecesCaptured = new ArrayList<>();
    
    public ChessMatch(){
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }
    
    public int getTurn(){
        return turn;
    }
    
    public Color getCurrentPlayer(){
        return currentPlayer;
    }
    
    public void nextTurn(){
        turn ++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
        
    }
    
    public Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    public Boolean getCheck(){
        return check;
    }
    
    private ChessPiece king(Color color){
        List<Piece> list = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " King on the board");
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
        
        check = testCheck(opponent(currentPlayer));
        
        nextTurn();
        return (ChessPiece)capturedPiece;
    }
    
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece captured = board.removePiece(target);
        board.placePiece(p, target);
        
        if(captured != null){
            piecesOnBoard.remove(captured);
            piecesCaptured.add(captured);
        }
        return captured;
    }
    
    private void validateSourcePosition(Position pos){
        if(!board.thereIsAPiece(pos)){
            throw new ChessException("There is no piece on source position.");
        }
        if(currentPlayer != ((ChessPiece)board.piece(pos)).getColor()){
            throw new ChessException("The chosen piece is not yours.");
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
        piecesOnBoard.add(piece);
    }
    
    private void initialSetup() {
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
    }
    
    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for(Piece p : opponentPieces){
            boolean mat[][] = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getCol()]){
                return true;
            }    
        }
        
        return false;
    }
}
