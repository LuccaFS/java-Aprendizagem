package application;

import chess.ChessMatch;

public class Program {
    public static void main(String[] args) throws Exception {
        
        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieces());
        //System.out.println(pos);
    }
}
