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
    private boolean checkmate;
    
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
    
    public Boolean getCheckMate(){
        return checkmate;
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
        
        if(testCheckMate(opponent(currentPlayer))){
            checkmate = true;
        }else{
            nextTurn();
        }
        
        return (ChessPiece)capturedPiece;
    }
    
    private Piece makeMove(Position source, Position target){
        ChessPiece p = (ChessPiece)board.removePiece(source);
        p.increaseMoveCount();
        Piece captured = board.removePiece(target);
        board.placePiece(p, target);
        
        if(captured != null){
            piecesOnBoard.remove(captured);
            piecesCaptured.add(captured);
        }
        return captured;
    }
    
    private void undoMove(Position source, Position target, Piece captured){
        ChessPiece p = (ChessPiece)board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);
        
        if(captured != null){
            board.placePiece(captured, target);
            piecesOnBoard.add(captured);
            piecesCaptured.remove(captured);
        }
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
        //kings
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        
        //queens
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        
        //rooks
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        
        //bishops
        placeNewPiece('b', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Bishop(board, Color.WHITE));
        
        placeNewPiece('b', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Bishop(board, Color.BLACK));
        
        //knights
        placeNewPiece('c', 1, new Knight(board, Color.WHITE));
        placeNewPiece('f', 1, new Knight(board, Color.WHITE));
        
        placeNewPiece('c', 8, new Knight(board, Color.BLACK));
        placeNewPiece('f', 8, new Knight(board, Color.BLACK));
        
        //pawns
        for(int i = 0; i <8; i++){
            placeNewPiece((char)('a'+ i), 7, new Pawn(board, Color.BLACK));
             
            placeNewPiece((char)('a'+ i), 2, new Pawn(board, Color.WHITE)); 
        }
        
        
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
    
    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }
        List<Piece> allies = piecesOnBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : allies){
            boolean[][] mat = p.possibleMoves();
            for (int i=0; i<board.getRows(); i++){
                for (int j=0; j<board.getCols(); j++){
                    if(mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if(!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
