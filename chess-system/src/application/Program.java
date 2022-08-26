package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessException;
import chess.ChessPosition;

public class Program {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        
        while(true){
            try
            {
                UI.clearScreen();
                UI.printBoard(match.getPieces());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                
                boolean[][] possibilities = match.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(match.getPieces(), possibilities);
                
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);
                
                ChessPiece capturedPiece = match.performChessMove(source, target);
            }
            catch(ChessException e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        //System.out.println(pos);
    }
}
