package application;

import chess.ChessPiece;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		// Estrutura de repetição for que forma a impressão do tabuleiro no console
		
		for(int i=0; i<pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {
		// Se não tiver peça no local, marcar com "-" que representa a ausência da mesma
		
		if(piece == null) {
			System.out.print("-");
		}
		
		// Senão, se existir peça no local, imprima a peça
		
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
